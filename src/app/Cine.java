package app;

import model.*;
import service.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cine {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        CinemaHallManager cinemaHallManager = new CinemaHallManager();
        ScreenManager screenManager = new ScreenManager();
        SeatManager seatManager = new SeatManager();
        ShowManager showManager = new ShowManager();
        MovieManager movieManager = new MovieManager();
        BookingManager bookingManager = new BookingManager();
        UserManager userManager = new UserManager();
        loadSampleData(
                cinemaHallManager,
                screenManager,
                seatManager,
                showManager,
                movieManager
        );

        while(true)
        {
            System.out.println("========== Cinema Ticket Booking System ==========");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice)
            {
                case 1:
                    System.out.println("========== User ==========");
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.println("3. Exit");
                    System.out.println("Enter your choice: ");
                    int x = sc.nextInt();
                    sc.nextLine();
                    if(x==1)
                    {
                        System.out.println("Enter Your Name: ");
                        String name = sc.nextLine();
                        System.out.println("Enter Your Phone No: ");
                        String phone = sc.nextLine();

                        User user = new User(name,phone);
                        if(userManager.registerUser(user))
                        {
                            System.out.println("Successfully Registered");
                            customerMenu(sc,cinemaHallManager,
                                    bookingManager,user);
                        }
                        else {
                            System.out.println("Phone No already present");
                        }
                    } else if (x == 2) {
                        System.out.println("Enter your phone no: ");
                        String phone = sc.nextLine();
                        User selectedUser = userManager.loginUser(phone);
                        if(selectedUser == null)
                        {
                            System.out.println("User not found");
                        }
                        else {
                            System.out.println("Login Successful");
                            customerMenu(
                                    sc,cinemaHallManager,
                                    bookingManager,
                                    selectedUser
                            );
                        }
                    }
                    else {
                        System.out.println("Invalid Choice");
                    }
                    break;
                case 2: System.out.println("Staff Selected");
                    break;
                case 3: System.out.println("Thank You");
                    sc.close();
                    return;
                default: System.out.println("Invalid Choice");
            }
        }
    }
    private static void loadSampleData(
            CinemaHallManager cinemaHallManager,
            ScreenManager screenManager,
            SeatManager seatManager,
            ShowManager showManager,
            MovieManager movieManager)
    {
        CinemaHall inoxCuttack = new CinemaHall("Inox","Cuttack");
        CinemaHall pvrCuttack = new CinemaHall("PVR","Cuttack");

        cinemaHallManager.addCinemaHall(inoxCuttack);
        cinemaHallManager.addCinemaHall(pvrCuttack);

        Screen inoxScreen1 = new Screen(1);
        Screen inoxScreen2 = new Screen(2);
        Screen pvrScreen1 = new Screen(1);

        screenManager.addScreen(inoxCuttack,inoxScreen1);
        screenManager.addScreen(inoxCuttack,inoxScreen2);
        screenManager.addScreen(pvrCuttack,pvrScreen1);

        for (int i=1;i<=10;i++)
        {
            seatManager.addSeat(inoxScreen1,new Seat("Standard","A",i));
        }
        for (int i=1;i<=10;i++)
        {
            seatManager.addSeat(inoxScreen1,new Seat("Premium","B",i));
        }
        Movie movie = new Movie("Interstellar",
                "Paramount Pictures",
                "Ema Thomas",
                "Christopher Nolan","English",
                Duration.ofMinutes(169),
                "Sci-fi");
        movieManager.addMovie(movie);

        Show show = new Show(movie.getTitle(),LocalDate.of(2026,9,10),
                LocalTime.of(18,0));
        showManager.addShow(inoxScreen1,show);
    }
    private static void customerMenu(
            Scanner sc,
            CinemaHallManager cinemaHallManager,
            BookingManager bookingManager,
            User selectedUser)
    {
        CinemaHall selectedHall = null;
        Screen selectedScreen = null;
        Show selectedShow = null;
        while(true)
        {
            System.out.println("========== Customer System ==========");
            System.out.println("1. Search Cinema");
            System.out.println("2. View Booking");
            System.out.println("3. Back");
            System.out.println("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch)
            {
                case 1:
                    System.out.println("Enter city: ");
                    String city = sc.nextLine();
                    List<CinemaHall> searchedHall = cinemaHallManager.searchByLocation(city);
                    for(int i=0;i< searchedHall.size();i++)
                    {
                        CinemaHall hall = searchedHall.get(i);
                        System.out.println((i+1)+" ."+" "+hall.getCinemaHallName()+" "+hall.getLocation());
                    }
                    System.out.println("Enter your choice: ");
                    int x = sc.nextInt();
                    sc.nextLine();
                    if(x<1 || x > searchedHall.size())
                    {
                        System.out.println("Invalid cinema hall selection");
                        break;
                    }
                    selectedHall = searchedHall.get(x-1);
                    System.out.println("Selected cinema hall: "+selectedHall.getCinemaHallName()+
                            " | "+selectedHall.getLocation());
                    System.out.println("Screens: ");
                    for(Screen screen : selectedHall.getScreen())
                    {
                        System.out.println("Screen"+screen.getScreenNo());
                    }
                    System.out.println("Enter your choice: ");
                    int y = sc.nextInt();
                    sc.nextLine();
                    if(y<1 || y > selectedHall.getScreen().size())
                    {
                        System.out.println("Invalid cinema hall selection");
                        break;
                    }
                    selectedScreen = selectedHall.getScreen().get(y-1);
                    System.out.println("You Selected:");
                    System.out.println("Screen"+selectedScreen.getScreenNo());
                    for(int i=0;i<selectedScreen.getShows().size();i++)
                    {
                        Show show = selectedScreen.getShows().get(i);
                        System.out.println((i+1)+"."+show.getMovie()+" | "+show.getDate()
                        +" | "+show.getStartTime());
                    }
                    System.out.println("Enter your choice: ");
                    int z = sc.nextInt();
                    sc.nextLine();
                    if(z<1 || z > selectedScreen.getShows().size())
                    {
                        System.out.println("Invalid cinema hall selection");
                        break;
                    }
                    selectedShow = selectedScreen.getShows().get(z-1);
                    System.out.println("Selected Seats: ");
                    List<Seat> bookedSeats = bookingManager.getBookedSeats(selectedShow);
                    System.out.println("Seats: ");
                    for(Seat seats: selectedScreen.getSeats())
                    {
                        boolean isBooked = false;
                        for(Seat seat : bookedSeats)
                        {
                            if(seats.getRow().equalsIgnoreCase(seat.getRow())&&
                            seats.getSeatNo()==seat.getSeatNo())
                            {
                                isBooked=true;
                                break;
                            }
                        }
                        if(isBooked)
                        {
                            System.out.println(" "+ seats.getRow() + seats.getSeatNo()
                                    + " - " + seats.getType() + " - Booked");
                        }
                        else {
                            System.out.println(" "+ seats.getRow() + seats.getSeatNo()
                                    + " - " + seats.getType() + " - Not Booked");
                        }
                    }
                    List<Seat> selectedSeats = new ArrayList<>();
                    System.out.println("Enter the no. of seats: ");
                    int numberOfSeats = sc.nextInt();
                    sc.nextLine();
                    for(int i = 0;i<numberOfSeats;i++)
                    {
                        System.out.println("Enter Seat "+(i+1)+":");
                        String seatInput = sc.nextLine();
                        String row = seatInput.substring(0,1);
                        int seatNo = Integer.parseInt(seatInput.substring(1));
                        boolean seatFound = false;
                        for (Seat seat : selectedScreen.getSeats())
                        {
                            if(seat.getRow().equalsIgnoreCase(row) &&
                            seat.getSeatNo()==seatNo)
                            {
                                boolean alreadyFound = false;
                                for(Seat selectedSeat:selectedSeats)
                                {
                                    if(selectedSeat.getRow().equalsIgnoreCase(row)&&
                                    selectedSeat.getSeatNo()==seatNo)
                                    {
                                        alreadyFound=true;
                                        break;
                                    }
                                }
                                if(alreadyFound){
                                    System.out.println("Seat Already Selected");
                                }
                                else {
                                    selectedSeats.add(seat);
                                }
                                seatFound = true;
                                break;
                            }
                        }
                        if(!seatFound)
                        {
                            System.out.println("Invalid Seat");
                        }
                    }
                    Booking booking = new Booking(selectedUser,selectedShow,selectedScreen,selectedSeats);
                    boolean bookingResult = bookingManager.addBooking(booking);
                    if(bookingResult)
                    {
                        System.out.println("Booking Successful");
                    }
                    else{
                        System.out.println("Booking Failed!");
                    }
                    break;
                case 2:
                    List<Booking> userBooking = bookingManager.getBookings(selectedUser);
                    if(userBooking.isEmpty())
                    {
                        System.out.println("No Bookings Found");
                    }
                    else{
                        for(Booking booking1: userBooking)
                        {
                            System.out.println("======= Your Booking =======");
                            System.out.println("Movie: "+booking1.getShow().getMovie());
                            System.out.println("Date: "+booking1.getShow().getDate());
                            System.out.println("Time: "+booking1.getShow().getStartTime());
                            System.out.println("Screen: "+booking1.getScreen().getScreenNo());
                            System.out.println("Seats: ");
                            for(Seat seat : booking1.getSeats())
                            {
                                System.out.println(seat.getRow()+seat.getSeatNo()+" ");
                            }
                            System.out.println(" ");
                            System.out.println("============================");
                        }
                    }
                    break;
                case 3:
                    return;
                default: System.out.println("Invalid Choice");
            }
        }
    }
}
