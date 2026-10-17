package app;

import model.*;
import service.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
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
        AdminManager adminManager = new AdminManager();

        while(true)
        {
            System.out.println("========== Cinema Ticket Booking System ==========");
            System.out.println("1. Customer");
            System.out.println("2. Admin");
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
                            customerMenu(sc,cinemaHallManager,screenManager,showManager,seatManager,
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
                        else{
                            System.out.println("Successfully Registered");
                            customerMenu(sc,cinemaHallManager,screenManager,showManager,seatManager,
                                    bookingManager,selectedUser);
                        }
                    }
                    else {
                        System.out.println("Invalid Choice");
                    }
                    break;
                case 2: System.out.println("========== Admin ==========");
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.println("3. Exit");
                    System.out.println("Enter your choice: ");
                    int y = sc.nextInt();
                    sc.nextLine();
                    if(y==1)
                    {
                        List<CinemaHall> choiceHall = cinemaHallManager.getAllCinemaHalls();
                        if(choiceHall == null || choiceHall.isEmpty())
                        {
                            System.out.println("No cinema hall available");
                            break;
                        }
                        for(int i=0;i<choiceHall.size();i++)
                        {
                            CinemaHall hall = choiceHall.get(i);
                            System.out.println((i+1)+". "+hall.getCinemaHallName()+" - "+hall.getLocation());
                        }
                        System.out.println("Select Cinema Hall: ");
                        int num = sc.nextInt();
                        sc.nextLine();
                        if(num<1 || num>choiceHall.size())
                        {
                            System.out.println("Invalid number");
                            break;
                        }
                        CinemaHall selectedHall = choiceHall.get(num-1);
                        System.out.println("Enter user Name: ");
                        String name = sc.nextLine();
                        System.out.println("Enter Password: ");
                        String password = sc.nextLine();
                        Admin admin = new Admin(name,password);
                        if(adminManager.addAdmin(selectedHall,admin))
                        {
                            System.out.println("Registered Successfully");
                        }
                        else{
                            System.out.println("Already present");
                        }
                    } else if(y==2) {
                        System.out.println("Enter user name: ");
                        String adminName = sc.nextLine();
                        System.out.println("Enter Password: ");
                        String password = sc.nextLine();
                        Admin admin = new Admin(adminName,password);
                        if(adminManager.loginAdmin(admin))
                        {
                            System.out.println("Successfully Login");
                            adminMenu(sc,admin,cinemaHallManager,movieManager,screenManager,
                                    showManager,seatManager);
                        }
                        else{
                            System.out.println("Admin not found");
                        }
                    }
                    else{
                        System.out.println("Invalid choice");
                    }
                    break;
                case 3: System.out.println("Thank You");
                    sc.close();
                    return;
                default: System.out.println("Invalid Choice");
            }
        }
    }
    private static void customerMenu(
            Scanner sc,
            CinemaHallManager cinemaHallManager,
            ScreenManager screenManager,
            ShowManager showManager,
            SeatManager seatManager,
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
                    List<Screen> screens = screenManager.getAllScreenOfCinemaHall(selectedHall);
                    if(screens.isEmpty())
                    {
                        System.out.println("No screens are available in this cinema hall");
                        break;
                    }
                    System.out.println("Screens: ");
                    for(int i=0;i<screens.size();i++)
                    {
                        System.out.println((i+1)+". Screen"+screens.get(i).getScreenNo());
                    }
                    System.out.println("Enter your choice: ");
                    int y = sc.nextInt();
                    sc.nextLine();
                    if(y<1 || y > screens.size())
                    {
                        System.out.println("Invalid cinema hall selection");
                        break;
                    }
                    selectedScreen = screens.get(y-1);
                    System.out.println("You Selected:");
                    System.out.println("Screen"+selectedScreen.getScreenNo());
                    List<Show> shows = showManager.getAllShowsByScreen(selectedScreen);
                    if(shows.isEmpty())
                    {
                        System.out.println("No shows are available in this screen");
                        break;
                    }
                    for(int i=0;i<shows.size();i++)
                    {
                        Show show = shows.get(i);
                        System.out.println((i+1)+"."+show.getMovieId()+" | "+show.getDate()
                        +" | "+show.getStartTime());
                    }
                    System.out.println("Enter your choice: ");
                    int z = sc.nextInt();
                    sc.nextLine();
                    if(z<1 || z > shows.size())
                    {
                        System.out.println("Invalid number selection");
                        break;
                    }
                    selectedShow = shows.get(z-1);
                    System.out.println("Selected Seats: ");
                    Set<Integer> bookedSeats = bookingManager.getBookedSeatIdsByShow(selectedShow.getShowId());
                    System.out.println("Seats: ");
                    List<Seat> seats1 = seatManager.getAllSeat(selectedScreen);
                    for(Seat seats: seats1)
                    {
                        boolean isBooked = bookedSeats.contains(seats.getSeatId());
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
                    if(numberOfSeats<=0)
                    {
                        System.out.println("Please select at least one seat");
                        break;
                    }
                    for(int i = 0;i<numberOfSeats;i++)
                    {
                        System.out.println("Enter Seat "+(i+1)+":");
                        String seatInput = sc.nextLine();
                        if(!seatInput.matches("[A-Za-z]\\d+"))
                        {
                            System.out.println("Invalid seat format. Ex- A1");
                            continue;
                        }
                        String row = seatInput.substring(0,1);
                        int seatNo = Integer.parseInt(seatInput.substring(1));
                        boolean seatFound = false;
                        for (Seat seat : seats1)
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
                                if(bookedSeats.contains(seat.getSeatId()))
                                {
                                    System.out.println("This seat is already booked");
                                }
                                else if(alreadyFound){
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
                    if(selectedSeats.isEmpty())
                    {
                        System.out.println("No valid seats are selected. Booking cancelled");
                        break;
                    }
                    Booking booking = new Booking(selectedUser,selectedShow,selectedSeats);
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
                    List<Booking> userBooking = bookingManager.getBookingsByUser(selectedUser);
                    if(userBooking.isEmpty())
                    {
                        System.out.println("No Bookings Found");
                    }
                    else{
                        for(Booking booking1: userBooking)
                        {
                            System.out.println("======= Your Booking =======");
                            System.out.println("Movie: "+booking1.getShow().getMovieId());
                            System.out.println("Date: "+booking1.getShow().getDate());
                            System.out.println("Time: "+booking1.getShow().getStartTime());
                            System.out.println("Screen: "+booking1.getShow().getScreenId());
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
    private static void adminMenu(
            Scanner sc,
            Admin admin,
            CinemaHallManager cinemaHallManager,
            MovieManager movieManager,
            ScreenManager screenManager,
            ShowManager showManager,
            SeatManager seatManager
    )
    {
        while(true)
        {
            System.out.println("======= Admin Menu =======");
            System.out.println("1. Movie");
            System.out.println("2. Screen");
            System.out.println("3. Seat");
            System.out.println("4. Show");
            System.out.println("5. Back");
            System.out.println("Enter Your Choice: ");
            int x = sc.nextInt();
            sc.nextLine();
            if(x==5)
            {
                break;
            }
            List<CinemaHall> hall1 = cinemaHallManager.getAllCinemaHalls();
            CinemaHall hall = null;
            CinemaHall selectedHall = null;
            if(hall1 == null || hall1.isEmpty())
            {
                System.out.println("Cinema Hall Not Found");
                break;
            }
            for(int i=0;i<hall1.size();i++)
            {
                hall = hall1.get(i);
                System.out.println("Cinema Hall ID: " + hall.getHallId());
                System.out.println("Admin Hall ID: " + admin.getHallId());
                if(hall.getHallId() == admin.getHallId())
                {
                    selectedHall=hall;
                    break;
                }
            }
            if(selectedHall==null)
            {
                System.out.println("Cinema Hall not found");
                break;
            }
            if(x == 1)
            {
                System.out.println("1. Add Movie");
                System.out.println("2. Delete Movie");
                System.out.println("3. Update Movie");
                System.out.println("4. Back");
                int choice = sc.nextInt();
                sc.nextLine();
                switch(choice)
                {
                    case 1: System.out.println("Enter Movie Title: ");
                        String movieName = sc.nextLine();
                        System.out.println("Enter Production House: ");
                        String prodHse = sc.nextLine();
                        System.out.println("Enter Producer: ");
                        String prod = sc.nextLine();
                        System.out.println("Enter Director: ");
                        String director = sc.nextLine();
                        System.out.println("Enter Language: ");
                        String lang = sc.nextLine();
                        System.out.println("Enter Duration(hours): ");
                        long hour = sc.nextLong();
                        System.out.println("Enter Duration(minutes): ");
                        long minutes = sc.nextLong();
                        sc.nextLine();
                        Duration duration = Duration.ofHours(hour).plusMinutes(minutes);
                        System.out.println("Enter Genre: ");
                        String genre = sc.nextLine();
                        Movie movie = new Movie(movieName,prodHse,prod,director,lang,duration,genre);
                        if(movieManager.addMovie(movie))
                        {
                            System.out.println("Added Successfully");
                        }
                        else{
                            System.out.println("Not Added");
                        }
                        break;
                    case 2: System.out.println("Enter Movie Title: ");
                        String title = sc.nextLine();
                        ArrayList<Movie> foundMovie = movieManager.searchMovieByTitle(title);
                        if (foundMovie.isEmpty())
                        {
                            System.out.println("Movie not found");
                        }
                        else if(foundMovie.size()>1)
                        {
                            for(Movie movie1: foundMovie)
                            {
                                System.out.println("MovieId: "+movie1.getMovieId()+" | "+movie1.getTitle()+
                                        " | "+movie1.getProductionHouse()+" | "+movie1.getProducer()+
                                        " | "+movie1.getDirector()+" | "+movie1.getLanguage()+
                                        " | "+movie1.getDuration()+" | "+movie1.getGenre());
                            }
                            System.out.println("Give the Id of the movie you want to delete: ");
                            int n = sc.nextInt();
                            sc.nextLine();
                            boolean movieFound = false;
                            for(Movie movie1:foundMovie)
                            {
                                if(movie1.getMovieId()==n)
                                {
                                    movieFound = true;
                                    break;
                                }
                            }
                            if(!movieFound)
                            {
                                System.out.println("Invalid Movie Id");
                                break;
                            }
                            if(movieManager.deleteMovieById(n))
                            {
                                System.out.println("Deleted Successfully");
                            }
                            else{
                                System.out.println("Not Found");
                            }
                        }
                        else {
                            Movie movie1 = foundMovie.get(0);
                            if(movieManager.deleteMovieById(movie1.getMovieId()))
                            {
                                System.out.println("Deleted Successfully");
                            }
                            else{
                                System.out.println("Not Found");
                            }
                        }
                        break;
                    case 3:System.out.println("1. Update Movie Title");
                        System.out.println("2. Update Movie Production House");
                        System.out.println("3. Update Movie Producer");
                        System.out.println("4. Update Movie Director");
                        System.out.println("5. Update Movie Language");
                        System.out.println("6. Update Movie Duration");
                        System.out.println("7. Update Movie Genre");
                        System.out.println("8. Back");
                        System.out.println("Enter Your Choice:");
                        int select = sc.nextInt();
                        sc.nextLine();
                        if(select == 1)
                        {
                            System.out.println("Enter Movie Title: ");
                            String oldTitle = sc.nextLine();
                            System.out.println("Enter New Movie Title: ");
                            String newTitle = sc.nextLine();
                            ArrayList<Movie> foundMovie1 = movieManager.searchMovieByTitle(oldTitle);
                            if (foundMovie1.isEmpty())
                            {
                                System.out.println("Movie not found");
                            }
                            else if(foundMovie1.size()==1)
                            {
                                Movie movie1 = foundMovie1.getFirst();
                                if(movieManager.updateMovieTitle(movie1.getMovieId(),newTitle,oldTitle))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not updated");
                                }
                            }
                            else {
                                for(Movie movie1: foundMovie1)
                                {
                                    System.out.println("MovieId: "+movie1.getMovieId()+" | "+movie1.getTitle()+
                                            " | "+movie1.getProductionHouse()+" | "+movie1.getProducer()+
                                            " | "+movie1.getDirector()+" | "+movie1.getLanguage()+
                                            " | "+movie1.getDuration()+" | "+movie1.getGenre());
                                }
                                System.out.println("Multiple films found");
                                System.out.println("Give the Id of the movie you want to update: ");
                                int n = sc.nextInt();
                                sc.nextLine();
                                boolean movieFound = false;
                                for(Movie movie1:foundMovie1)
                                {
                                    if(movie1.getMovieId()==n)
                                    {
                                        movieFound = true;
                                        break;
                                    }
                                }
                                if(!movieFound)
                                {
                                    System.out.println("Invalid Movie Id");
                                    break;
                                }
                                if(movieManager.updateMovieTitle(n,newTitle,oldTitle))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not Updated");
                                }
                            }
                        }
                        else if(select == 2)
                        {
                            System.out.println("Enter Movie Title: ");
                            String title1 = sc.nextLine();
                            System.out.println("Enter New Production House: ");
                            String newProdHsc = sc.nextLine();
                            System.out.println("Enter old Production House: ");
                            String oldProdHsc = sc.nextLine();
                            ArrayList<Movie> foundMovie1 = movieManager.searchMovieByTitle(title1);
                            if (foundMovie1.isEmpty())
                            {
                                System.out.println("Movie not found");
                            }
                            else if(foundMovie1.size()==1)
                            {
                                Movie movie1 = foundMovie1.getFirst();
                                if(movieManager.updateMovieProductionHouse(movie1.getMovieId(),newProdHsc,oldProdHsc))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not updated");
                                }
                            }
                            else {
                                for(Movie movie1: foundMovie1)
                                {
                                    System.out.println("MovieId: "+movie1.getMovieId()+" | "+movie1.getTitle()+
                                            " | "+movie1.getProductionHouse()+" | "+movie1.getProducer()+
                                            " | "+movie1.getDirector()+" | "+movie1.getLanguage()+
                                            " | "+movie1.getDuration()+" | "+movie1.getGenre());
                                }
                                System.out.println("Multiple films found");
                                System.out.println("Give the Id of the movie you want to update: ");
                                int n = sc.nextInt();
                                sc.nextLine();
                                boolean movieFound = false;
                                for(Movie movie1:foundMovie1)
                                {
                                    if(movie1.getMovieId()==n)
                                    {
                                        movieFound = true;
                                        break;
                                    }
                                }
                                if(!movieFound)
                                {
                                    System.out.println("Invalid Movie Id");
                                    break;
                                }
                                if(movieManager.updateMovieProductionHouse(n,newProdHsc,oldProdHsc))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not Updated");
                                }
                            }
                        }
                        else if(select == 3)
                        {
                            System.out.println("Enter Movie Title: ");
                            String title2 = sc.nextLine();
                            System.out.println("Enter New Producer: ");
                            String newProd = sc.nextLine();
                            System.out.println("Enter old Producer: ");
                            String oldProd = sc.nextLine();
                            ArrayList<Movie> foundMovie1 = movieManager.searchMovieByTitle(title2);
                            if (foundMovie1.isEmpty())
                            {
                                System.out.println("Movie not found");
                            }
                            else if(foundMovie1.size()==1)
                            {
                                Movie movie1 = foundMovie1.getFirst();
                                if(movieManager.updateMovieProducer(movie1.getMovieId(),newProd,oldProd))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not updated");
                                }
                            }
                            else {
                                for(Movie movie1: foundMovie1)
                                {
                                    System.out.println("MovieId: "+movie1.getMovieId()+" | "+movie1.getTitle()+
                                            " | "+movie1.getProductionHouse()+" | "+movie1.getProducer()+
                                            " | "+movie1.getDirector()+" | "+movie1.getLanguage()+
                                            " | "+movie1.getDuration()+" | "+movie1.getGenre());
                                }
                                System.out.println("Multiple films found");
                                System.out.println("Give the Id of the movie you want to update: ");
                                int n = sc.nextInt();
                                sc.nextLine();
                                boolean movieFound = false;
                                for(Movie movie1:foundMovie1)
                                {
                                    if(movie1.getMovieId()==n)
                                    {
                                        movieFound = true;
                                        break;
                                    }
                                }
                                if(!movieFound)
                                {
                                    System.out.println("Invalid Movie Id");
                                    break;
                                }
                                if(movieManager.updateMovieProducer(n,newProd,oldProd))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not Updated");
                                }
                            }
                        }
                        else if(select == 4)
                        {
                            System.out.println("Enter Movie Title: ");
                            String title3 = sc.nextLine();
                            System.out.println("Enter New director: ");
                            String newDirector = sc.nextLine();
                            System.out.println("Enter old director: ");
                            String oldDirector = sc.nextLine();
                            ArrayList<Movie> foundMovie1 = movieManager.searchMovieByTitle(title3);
                            if (foundMovie1.isEmpty())
                            {
                                System.out.println("Movie not found");
                            }
                            else if(foundMovie1.size()==1)
                            {
                                Movie movie1 = foundMovie1.getFirst();
                                if(movieManager.updateMovieDirector(movie1.getMovieId(),newDirector,oldDirector))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not updated");
                                }
                            }
                            else {
                                for(Movie movie1: foundMovie1)
                                {
                                    System.out.println("MovieId: "+movie1.getMovieId()+" | "+movie1.getTitle()+
                                            " | "+movie1.getProductionHouse()+" | "+movie1.getProducer()+
                                            " | "+movie1.getDirector()+" | "+movie1.getLanguage()+
                                            " | "+movie1.getDuration()+" | "+movie1.getGenre());
                                }
                                System.out.println("Multiple films found");
                                System.out.println("Give the Id of the movie you want to update: ");
                                int n = sc.nextInt();
                                sc.nextLine();
                                boolean movieFound = false;
                                for(Movie movie1:foundMovie1)
                                {
                                    if(movie1.getMovieId()==n)
                                    {
                                        movieFound = true;
                                        break;
                                    }
                                }
                                if(!movieFound)
                                {
                                    System.out.println("Invalid Movie Id");
                                    break;
                                }
                                if(movieManager.updateMovieDirector(n,newDirector,oldDirector))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not Updated");
                                }
                            }
                        }
                        else if(select == 5)
                        {
                            System.out.println("Enter Movie Title: ");
                            String title4 = sc.nextLine();
                            System.out.println("Enter New Language: ");
                            String newLang = sc.nextLine();
                            System.out.println("Enter old Language: ");
                            String oldLang = sc.nextLine();
                            ArrayList<Movie> foundMovie1 = movieManager.searchMovieByTitle(title4);
                            if (foundMovie1.isEmpty())
                            {
                                System.out.println("Movie not found");
                            }
                            else if(foundMovie1.size()==1)
                            {
                                Movie movie1 = foundMovie1.getFirst();
                                if(movieManager.updateMovieLanguage(movie1.getMovieId(),newLang,oldLang))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not updated");
                                }
                            }
                            else {
                                for(Movie movie1: foundMovie1)
                                {
                                    System.out.println("MovieId: "+movie1.getMovieId()+" | "+movie1.getTitle()+
                                            " | "+movie1.getProductionHouse()+" | "+movie1.getProducer()+
                                            " | "+movie1.getDirector()+" | "+movie1.getLanguage()+
                                            " | "+movie1.getDuration()+" | "+movie1.getGenre());
                                }
                                System.out.println("Multiple films found");
                                System.out.println("Give the Id of the movie you want to update: ");
                                int n = sc.nextInt();
                                sc.nextLine();
                                boolean movieFound = false;
                                for(Movie movie1:foundMovie1)
                                {
                                    if(movie1.getMovieId()==n)
                                    {
                                        movieFound = true;
                                        break;
                                    }
                                }
                                if(!movieFound)
                                {
                                    System.out.println("Invalid Movie Id");
                                    break;
                                }
                                if(movieManager.updateMovieLanguage(n,newLang,oldLang))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not Updated");
                                }
                            }
                        }
                        else if(select == 6)
                        {
                            System.out.println("Enter Movie Title: ");
                            String title5 = sc.nextLine();

                            System.out.println("Enter old Duration(hours): ");
                            long hours = sc.nextLong();
                            System.out.println("Enter old Duration(minutes): ");
                            long minute = sc.nextLong();
                            sc.nextLine();
                            Duration duration1 = Duration.ofHours(hours).plusMinutes(minute);
                            System.out.println("Enter new Duration(hours): ");
                            long hours1 = sc.nextLong();
                            System.out.println("Enter new Duration(minutes): ");
                            long minutes1 = sc.nextLong();
                            sc.nextLine();
                            Duration duration2 = Duration.ofHours(hours1).plusMinutes(minutes1);
                            ArrayList<Movie> foundMovie1 = movieManager.searchMovieByTitle(title5);
                            if (foundMovie1.isEmpty())
                            {
                                System.out.println("Movie not found");
                            }
                            else if(foundMovie1.size()==1)
                            {
                                Movie movie1 = foundMovie1.getFirst();
                                if(movieManager.updateMovieDuration(movie1.getMovieId(),duration2,duration1))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not updated");
                                }
                            }
                            else {
                                for(Movie movie1: foundMovie1)
                                {
                                    System.out.println("MovieId: "+movie1.getMovieId()+" | "+movie1.getTitle()+
                                            " | "+movie1.getProductionHouse()+" | "+movie1.getProducer()+
                                            " | "+movie1.getDirector()+" | "+movie1.getLanguage()+
                                            " | "+movie1.getDuration()+" | "+movie1.getGenre());
                                }
                                System.out.println("Multiple films found");
                                System.out.println("Give the Id of the movie you want to update: ");
                                int n = sc.nextInt();
                                sc.nextLine();
                                boolean movieFound = false;
                                for(Movie movie1:foundMovie1)
                                {
                                    if(movie1.getMovieId()==n)
                                    {
                                        movieFound = true;
                                        break;
                                    }
                                }
                                if(!movieFound)
                                {
                                    System.out.println("Invalid Movie Id");
                                    break;
                                }
                                if(movieManager.updateMovieDuration(n,duration2,duration1))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not Updated");
                                }
                            }
                        }
                        else if(select == 7)
                        {
                            System.out.println("Enter Movie Title: ");
                            String title6 = sc.nextLine();
                            System.out.println("Enter New Genre: ");
                            String newGenre = sc.nextLine();
                            System.out.println("Enter old Genre: ");
                            String oldGenre = sc.nextLine();
                            ArrayList<Movie> foundMovie1 = movieManager.searchMovieByTitle(title6);
                            if (foundMovie1.isEmpty())
                            {
                                System.out.println("Movie not found");
                            }
                            else if(foundMovie1.size()==1)
                            {
                                Movie movie1 = foundMovie1.getFirst();
                                if(movieManager.updateMovieGenre(movie1.getMovieId(),newGenre,oldGenre))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not updated");
                                }
                            }
                            else {
                                for(Movie movie1: foundMovie1)
                                {
                                    System.out.println("MovieId: "+movie1.getMovieId()+" | "+movie1.getTitle()+
                                            " | "+movie1.getProductionHouse()+" | "+movie1.getProducer()+
                                            " | "+movie1.getDirector()+" | "+movie1.getLanguage()+
                                            " | "+movie1.getDuration()+" | "+movie1.getGenre());
                                }
                                System.out.println("Multiple films found");
                                System.out.println("Give the Id of the movie you want to update: ");
                                int n = sc.nextInt();
                                sc.nextLine();
                                boolean movieFound = false;
                                for(Movie movie1:foundMovie1)
                                {
                                    if(movie1.getMovieId()==n)
                                    {
                                        movieFound = true;
                                        break;
                                    }
                                }
                                if(!movieFound)
                                {
                                    System.out.println("Invalid Movie Id");
                                    break;
                                }
                                if(movieManager.updateMovieGenre(n,newGenre,oldGenre))
                                {
                                    System.out.println("Updated Successfully");
                                }
                                else{
                                    System.out.println("Not Updated");
                                }
                            }
                        }
                        else if(select == 8)
                        {
                            break;
                        }
                        else {
                            System.out.println("Invalid Choice");
                        }
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }
            }
            else if(x==2)
            {
                Screen selectedScreen = null;
                List<Screen> screens = screenManager.getAllScreenOfCinemaHall(selectedHall);
                if(screens == null || screens.isEmpty())
                {
                    System.out.println("No Screens are present");
                }
                else {
                    for(int j=0;j< screens.size();j++)
                    {
                        Screen screen = screens.get(j);
                        System.out.println((j+1)+" . Screen "+screen.getScreenNo());
                    }
                }
                System.out.println("1. Add Screen");
                System.out.println("2. Delete / Update Screen");
                System.out.println("3. Back");
                System.out.println("Enter number of which operation you want to perform: ");
                int opt = sc.nextInt();
                sc.nextLine();
                if(opt==1)
                {
                    System.out.println("Enter Screen no: ");
                    int screenNo = sc.nextInt();
                    sc.nextLine();
                    Screen screen = new Screen(screenNo);
                    System.out.println("Enter the no. of rows: ");
                    int rowNo = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the no. of seats each row contains: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    for(int i=0;i<rowNo;i++)
                    {
                        String row = String.valueOf((char)('A'+i));
                        for(int j=1;j<=n;j++)
                        {
                            Seat seat = new Seat(row,j,"Regular");
                            screen.getSeats().add(seat);
                        }
                    }
                    if(screenManager.addScreen(hall,screen))
                    {
                        System.out.println("Added Successfully");
                    }
                    else{
                        System.out.println("Not Successful");
                    }
                    break;
                }
                if (opt == 2 && (screens == null || screens.isEmpty())) {
                    System.out.println("No screens are available to delete or update.");
                    break;
                }
                else if(opt==2)
                {
                    System.out.println("Enter Screen serial no: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    if(n<1 || n> screens.size())
                    {
                        System.out.println("Invalid Number selection");
                        break;
                    }
                    selectedScreen = screens.get(n-1);
                    if(selectedScreen == null)
                    {
                        System.out.println("Screen not found");
                        break;
                    }
                    System.out.println("1. Delete Screen");
                    System.out.println("2. Update Screen");
                    System.out.println("3. Back");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    switch (choice)
                    {
                        case 1:
                            if(screenManager.deleteScreen(hall,selectedScreen))
                            {
                                System.out.println("Deleted Successfully");
                            }
                            else{
                                System.out.println("Not deleted");
                            }
                            break;
                        case 2:
                            System.out.println("Enter new Screen No: ");
                            int newNum = sc.nextInt();
                            sc.nextLine();
                            if(screenManager.updateScreen(hall,selectedScreen,newNum))
                            {
                                System.out.println("Updated Successfully");
                            }
                            else{
                                System.out.println("Not updated");
                            }
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid Choice");
                    }
                }
                else if(opt==3)
                {
                    break;
                }
                else {
                    System.out.println("Invalid choice");
                }
            }
            else if(x==3)
            {
                Screen selectedScreen = null;
                List<Screen> screens = screenManager.getAllScreenOfCinemaHall(selectedHall);
                if(screens == null || screens.isEmpty())
                {
                    System.out.println("No Screens are present");
                    break;
                }
                for(int j=0;j< screens.size();j++)
                {
                    Screen screen = screens.get(j);
                    System.out.println((j+1)+" . Screen "+screen.getScreenNo());
                }
                System.out.println("Enter Screen serial no: ");
                int n = sc.nextInt();
                sc.nextLine();
                if(n<1 || n> screens.size())
                {
                    System.out.println("Invalid Number selection");
                    break;
                }
                selectedScreen = screens.get(n-1);
                if(selectedScreen == null)
                {
                    System.out.println("Screen not found");
                    break;
                }
                List<Seat> seats = seatManager.getAllSeat(selectedScreen);
                Seat selectedSeat = null;
                if(seats == null || seats.isEmpty())
                {
                    System.out.println("No Seats are present");
                }
                else {
                    for(int i=0;i<seats.size();i++)
                    {
                        Seat seat = seats.get(i);
                        System.out.println((i+1)+". Seat No: "+seat.getRow()+seat.getSeatNo()+"- "+seat.getType());
                    }
                }
                System.out.println("1. Add Seat");
                System.out.println("2. Delete / Update Seat");
                System.out.println("3. Back");
                System.out.println("Select to perform operation: ");
                int operation = sc.nextInt();
                sc.nextLine();
                if(operation == 1)
                {
                    System.out.println("Enter seat type: ");
                    String type = sc.nextLine();
                    System.out.println("Enter seat Row: ");
                    String row = sc.nextLine();
                    System.out.println("Enter seat No: ");
                    int num1 = sc.nextInt();
                    sc.nextLine();
                    Seat seat = new Seat(row,num1,type);
                    if(seatManager.addSeat(selectedScreen,seat))
                    {
                        System.out.println("Added");
                    }
                    else {
                        System.out.println("Not added");
                    }
                    break;
                }
                if (operation == 2 && (seats == null || seats.isEmpty())) {
                    System.out.println("No seats available to delete or update.");
                    break;
                }
                else if(operation == 2)
                {
                    System.out.println("Enter seat serial no: ");
                    int num1 = sc.nextInt();
                    sc.nextLine();
                    if(num1<1 || num1> seats.size())
                    {
                        System.out.println("Invalid Number selection");
                        break;
                    }
                    selectedSeat = seats.get(num1-1);
                    System.out.println("1. Delete Seat");
                    System.out.println("2. Update Seat No");
                    System.out.println("3. Update Seat Row");
                    System.out.println("4. Update Seat Type");
                    System.out.println("5. Back");
                    System.out.println("Which operation you want to perform: ");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    switch (choice)
                    {
                        case 1:
                            if(seatManager.deleteSeat(selectedScreen,selectedSeat))
                            {
                                System.out.println("Deleted");
                            }
                            else {
                                System.out.println("Not Deleted");
                            }
                            break;
                        case 2:
                            System.out.println("Enter new seat No: ");
                            int newSeat = sc.nextInt();
                            sc.nextLine();
                            if(seatManager.updateSeatNumber(selectedScreen,selectedSeat,newSeat))
                            {
                                System.out.println("Updated");
                            }
                            else {
                                System.out.println("Not Updated");
                            }
                            break;
                        case 3:
                            System.out.println("Enter new Row: ");
                            String newRow = sc.nextLine();
                            if(seatManager.updateSeatByRow(selectedScreen,selectedSeat,newRow))
                            {
                                System.out.println("Updated");
                            }
                            else {
                                System.out.println("Not Updated");
                            }
                            break;
                        case 4:
                            System.out.println("Enter new Type: ");
                            String newType = sc.nextLine();
                            if(seatManager.updateSeatByType(selectedScreen,selectedSeat,newType))
                            {
                                System.out.println("Updated");
                            }
                            else {
                                System.out.println("Not Updated");
                            }
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Invalid Choice");
                    }
                }
                else if(operation==3)
                {
                    break;
                }
                else{
                    System.out.println("Invalid Choice");
                }
            }
            else if(x==4)
            {
                Screen selectedScreen = null;
                List<Screen> screens = screenManager.getAllScreenOfCinemaHall(selectedHall);
                if(screens == null || screens.isEmpty())
                {
                    System.out.println("No Screens are present");
                    break;
                }
                for(int j=0;j< screens.size();j++)
                {
                    Screen screen = screens.get(j);
                    System.out.println((j+1)+" . Screen "+screen.getScreenNo());
                }
                System.out.println("Enter Screen serial no: ");
                int n = sc.nextInt();
                sc.nextLine();
                if(n<1 || n> screens.size())
                {
                    System.out.println("Invalid Number selection");
                    break;
                }
                selectedScreen = screens.get(n-1);
                if(selectedScreen == null)
                {
                    System.out.println("Screen not found");
                    break;
                }
                List<Show> shows = showManager.getAllShowsByScreen(selectedScreen);
                Show selectedShow = null;
                if(shows == null || shows.isEmpty())
                {
                    System.out.println("No shows are available in this screen yet");
                }
                else {
                    for(int i=0;i<shows.size();i++)
                    {
                        Show shows1 = shows.get(i);
                        System.out.println((i+1)+" . MovieId: "+shows1.getMovieId()+" | "+shows1.getStartTime()
                                +" | "+shows1.getDate());
                    }
                    System.out.println("Select a show serial no. to delete or update:");
                    int select = sc.nextInt();
                    sc.nextLine();
                    if(select<1 || select> shows.size())
                    {
                        System.out.println("Invalid Number selection");
                        break;
                    }
                    selectedShow = shows.get(select-1);
                }
                System.out.println("1. Add Show");
                System.out.println("2. Delete Show");
                System.out.println("3. Update Show");
                System.out.println("4. Back");
                System.out.println("Which operation you want to perform: ");
                int num = sc.nextInt();
                sc.nextLine();
                if((num==2 || num==3) && selectedShow==null)
                {
                    System.out.println("No shows are available so we can't delete or update show");
                    break;
                }
                switch (num)
                {
                    case 1: System.out.println("Enter movie title: ");
                        String movieTitle = sc.nextLine();
                        ArrayList<Movie> title = movieManager.searchMovieByTitle(movieTitle);
                        if(title.isEmpty())
                        {
                            System.out.println("Movie not found");
                            break;
                        }
                        int movieId;
                        if(title.size() == 1)
                        {
                            movieId = title.getFirst().getMovieId();
                        }
                        else
                        {
                            for(Movie movie1: title)
                            {
                                System.out.println("MovieId: "+movie1.getMovieId()+" | "+movie1.getTitle()+
                                        " | "+movie1.getProductionHouse()+" | "+movie1.getProducer()+
                                        " | "+movie1.getDirector()+" | "+movie1.getLanguage()+
                                        " | "+movie1.getDuration()+" | "+movie1.getGenre());
                            }
                            System.out.println("Multiple films found");
                            System.out.println("Give the Id of the movie you want to add for show: ");
                            movieId = sc.nextInt();
                            sc.nextLine();
                            boolean movieFound = false;
                            for(Movie movie1:title)
                            {
                                if(movie1.getMovieId()==movieId)
                                {
                                    movieFound = true;
                                    break;
                                }
                            }
                            if(!movieFound)
                            {
                                System.out.println("Invalid Movie Id");
                                break;
                            }
                        }
                        System.out.println("Enter show date(in dd-MM-yy): ");
                        String userInput = sc.nextLine();
                        LocalDate date = null;
                        LocalTime time = null;
                        try
                        {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                            date = LocalDate.parse(userInput,formatter);
                        }
                        catch(DateTimeParseException e){
                            System.out.println("Invalid format");
                            break;
                        }
                        System.out.println("Enter show time(in HH:mm): ");
                        String user = sc.nextLine();
                        try{
                            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
                            time = LocalTime.parse(user, formatter1);
                        }
                        catch(DateTimeParseException e){
                            System.out.println("Invalid format");
                            break;
                        }
                        Show show = new Show(movieId,selectedScreen.getScreenId(),date,time);
                        if(showManager.addShow(show))
                        {
                            System.out.println("Added Successfully");
                        }
                        else {
                            System.out.println("Not Added");
                        }
                        break;
                    case 2:
                        if(showManager.deleteShow(selectedShow))
                        {
                            System.out.println("Deleted");
                        }
                        else {
                            System.out.println("Not Deleted");
                        }
                        break;
                    case 3:
                        System.out.println("1. Update Show Date");
                        System.out.println("2. Update Show Time");
                        System.out.println("3. Back");
                        System.out.println("Enter your choice: ");
                        int num1 = sc.nextInt();
                        sc.nextLine();
                        switch (num1)
                        {
                            case 1: LocalDate newDate = null;

                                System.out.println("Enter new show date(in dd-MM-yy): ");
                                String newUserInput = sc.nextLine();
                                try
                                {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                                    newDate = LocalDate.parse(newUserInput,formatter);
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Invalid format");
                                    break;
                                }
                                if(newDate==null)
                                {
                                    System.out.println("Date can't be null");
                                    break;
                                }
                                if(showManager.updateShowDate(selectedShow,newDate))
                                {
                                    System.out.println("Updated");
                                }
                                else {
                                    System.out.println("Not updated");
                                }
                                break;
                            case 2:
                                LocalTime newTime = null;
                                System.out.println("Enter new show time(in HH:mm): ");
                                String newUser = sc.nextLine();
                                try{
                                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
                                    newTime = LocalTime.parse(newUser, formatter1);
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Invalid format");
                                    break;
                                }
                                if(newTime==null)
                                {
                                    System.out.println("Date can't be null");
                                    break;
                                }
                                if(showManager.updateShowTime(selectedShow,newTime))
                                {
                                    System.out.println("Updated");
                                }
                                else {
                                    System.out.println("Not updated");
                                }
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Invalid choice");
                        }
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
            else{
                System.out.println("Invalid Choice");
            }
        }
    }
}