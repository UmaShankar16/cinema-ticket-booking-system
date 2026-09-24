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
        AdminManager adminManager = new AdminManager();
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
                case 2: System.out.println("========== Admin ==========");
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.println("3. Exit");
                    System.out.println("Enter your choice: ");
                    int y = sc.nextInt();
                    sc.nextLine();
                    if(y==1)
                    {
                        System.out.println("Enter Cinema Hall: ");
                        String hall = sc.nextLine();
                        System.out.println("Enter Proper Location: ");
                        String location = sc.nextLine();
                        System.out.println("Enter Password: ");
                        String password = sc.nextLine();
                        Admin admin = new Admin(hall,location,password);
                        if(adminManager.addAdmin(admin))
                        {
                            System.out.println("Registered Successfully");
                            adminMenu(sc,admin,cinemaHallManager,movieManager,screenManager,
                                    showManager,seatManager);
                        }
                        else{
                            System.out.println("Already present");
                        }
                    } else if(y==2) {
                        System.out.println("Enter Cinema Hall: ");
                        String hall = sc.nextLine();
                        System.out.println("Enter Proper Location: ");
                        String location = sc.nextLine();
                        System.out.println("Enter Password: ");
                        String password = sc.nextLine();
                        Admin admin = new Admin(hall,location,password);
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
            int x = sc.nextInt();
            sc.nextLine();
            CinemaHall hall = (CinemaHall) cinemaHallManager.searchByNameAndLocation(admin.getCinemaHallName(),
                    admin.getCinemaHallLocation());
            if(hall == null)
            {
                System.out.println("Cinema Hall Not Found");
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
                        if(movieManager.deleteMovie(title))
                        {
                            System.out.println("Deleted Successfully");
                        }
                        else{
                            System.out.println("Not Found");
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
                            if(movieManager.updateMovieTitle(newTitle,oldTitle))
                            {
                                System.out.println("Updated");
                            }
                            else{
                                System.out.println("Not Updated");
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
                            if(movieManager.updateMovieProductionHouse(title1,newProdHsc,oldProdHsc))
                            {
                                System.out.println("Updated");
                            }
                            else{
                                System.out.println("Not Updated");
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
                            if(movieManager.updateMovieProducer(title2,newProd,oldProd))
                            {
                                System.out.println("Updated");
                            }
                            else{
                                System.out.println("Not Updated");
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
                            if(movieManager.updateMovieDirector(title3,newDirector,oldDirector))
                            {
                                System.out.println("Updated");
                            }
                            else{
                                System.out.println("Not Updated");
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
                            if(movieManager.updateMovieLanguage(title4,newLang,oldLang))
                            {
                                System.out.println("Updated");
                            }
                            else{
                                System.out.println("Not Updated");
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
                            if(movieManager.updateMovieDuration(title5,duration1,duration2))
                            {
                                System.out.println("Updated");
                            }
                            else{
                                System.out.println("Not Updated");
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
                            if(movieManager.updateMovieGenre(title6,oldGenre,newGenre))
                            {
                                System.out.println("Updated");
                            }
                            else{
                                System.out.println("Not Updated");
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
                System.out.println("1. Add Screen");
                System.out.println("2. Delete Screen");
                System.out.println("3. Update Screen");
                System.out.println("4. Back");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice)
                {
                    case 1: System.out.println("Enter Screen no: ");
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
                                Seat seat = new Seat("Regular",row,j);
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
                    case 2:
                        System.out.println("Enter Screen No: ");
                        int num = sc.nextInt();
                        if(screenManager.deleteScreen(hall,num))
                        {
                            System.out.println("Deleted Successfully");
                        }
                        else{
                            System.out.println("Not deleted");
                        }
                        break;
                    case 3: System.out.println("Enter old Screen No: ");
                        int oldNum = sc.nextInt();
                        System.out.println("Enter new Screen No: ");
                        int newNum = sc.nextInt();
                        if(screenManager.updateScreen(hall,oldNum,newNum))
                        {
                            System.out.println("Updated Successfully");
                        }
                        else{
                            System.out.println("Not updated");
                        }
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }
            }
            else if(x==3)
            {
                System.out.println("1. Add Seat");
                System.out.println("2. Delete Seat");
                System.out.println("3. Update Seat No");
                System.out.println("4. Update Seat Row");
                System.out.println("5. Update Seat Type");
                System.out.println("6. Back");
                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter Screen No in which you want to perform this operation: ");
                int no =sc.nextInt();
                Screen selectedScreen = null;
                for(Screen screen: hall.getScreen())
                {
                    if(screen.getScreenNo()==no)
                    {
                        selectedScreen = screen;
                        break;
                    }
                }
                if(selectedScreen == null)
                {
                    System.out.println("Screen not found");
                }
                switch (choice)
                {
                    case 1: System.out.println("Enter seat type: ");
                        String type = sc.nextLine();
                        System.out.println("Enter seat Row: ");
                        String row = sc.nextLine();
                        System.out.println("Enter seat No: ");
                        int num1 = sc.nextInt();
                        sc.nextLine();
                        Seat seat = new Seat(type,row,num1);
                        if(seatManager.addSeat(selectedScreen,seat))
                        {
                            System.out.println("Added");
                        }
                        else {
                            System.out.println("Not added");
                        }
                        break;
                    case 2:
                        System.out.println("Enter seat Row: ");
                        String row1 = sc.nextLine();
                        System.out.println("Enter seat No: ");
                        int n = sc.nextInt();
                        sc.nextLine();
                        if(seatManager.deleteSeat(selectedScreen,n,row1))
                        {
                            System.out.println("Deleted");
                        }
                        else {
                            System.out.println("Not Deleted");
                        }
                        break;
                    case 3:
                        System.out.println("Enter seat Row: ");
                        String row2 = sc.nextLine();
                        System.out.println("Enter old seat No: ");
                        int oldSeat = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter new seat No: ");
                        int newSeat = sc.nextInt();
                        sc.nextLine();
                        if(seatManager.updateSeatNumber(selectedScreen,oldSeat,newSeat,row2))
                        {
                            System.out.println("Updated");
                        }
                        else {
                            System.out.println("Not Updated");
                        }
                        break;
                    case 4:
                        System.out.println("Enter old seat Row: ");
                        String oldRow = sc.nextLine();
                        System.out.println("Enter new Row: ");
                        String newRow = sc.nextLine();
                        if(seatManager.updateSeatByRow(selectedScreen,oldRow,newRow))
                        {
                            System.out.println("Updated");
                        }
                        else {
                            System.out.println("Not Updated");
                        }
                        break;
                    case 5:
                        System.out.println("Enter seat Row: ");
                        String row3 = sc.nextLine();
                        System.out.println("Enter new Type: ");
                        String newType = sc.nextLine();
                        System.out.println("Enter old Type: ");
                        String oldType = sc.nextLine();
                        System.out.println("Enter seat No: ");
                        int y = sc.nextInt();
                        sc.nextLine();
                        if(seatManager.updateSeatByType(selectedScreen,y,newType,oldType,row3))
                        {
                            System.out.println("Updated");
                        }
                        else {
                            System.out.println("Not Updated");
                        }
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }
            }
            else if(x==4)
            {
                Screen selectedScreen = null;
                System.out.println("Enter Screen no: ");
                int n = sc.nextInt();
                for(Screen screen: hall.getScreen())
                {
                    if(screen.getScreenNo()==n)
                    {
                        selectedScreen = screen;
                        break;
                    }
                }
                if(selectedScreen == null)
                {
                    System.out.println("Screen not found");
                    break;
                }
                System.out.println("1. Add Show");
                System.out.println("2. Delete Show");
                System.out.println("3. Update Show");
                System.out.println("4. Back");
                System.out.println("Enter your choice: ");
                int num = sc.nextInt();
                sc.nextLine();
                switch (num)
                {
                    case 1: System.out.println("Enter movie title: ");
                        String movieTitle = sc.nextLine();
                        Movie title = movieManager.findMovie(movieTitle);
                        if(title == null)
                        {
                            System.out.println("Movie not found");
                            break;
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
                        }
                        System.out.println("Enter show time(in HH:mm): ");
                        String user = sc.nextLine();
                        try{
                            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
                            time = LocalTime.parse(user, formatter1);
                        }
                        catch(DateTimeParseException e){
                            System.out.println("Invalid format");
                        }
                        Show show = new Show(movieTitle,date,time);
                        if(showManager.addShow(selectedScreen,show))
                        {
                            System.out.println("Added Successfully");
                        }
                        else {
                            System.out.println("Not Added");
                        }
                        break;
                    case 2: System.out.println("Enter movie title: ");
                        String movie = sc.nextLine();
                        Movie film = movieManager.findMovie(movie);
                        if(film == null)
                        {
                            System.out.println("Movie not found");
                            break;
                        }
                        System.out.println("Enter show date(in dd-MM-yy): ");
                        String userInput1 = sc.nextLine();
                        LocalDate date1 = null;
                        LocalTime time1 = null;
                        try
                        {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                            date1 = LocalDate.parse(userInput1,formatter);
                        }
                        catch(DateTimeParseException e){
                            System.out.println("Invalid format");
                        }
                        System.out.println("Enter show time(in HH:mm): ");
                        String user1 = sc.nextLine();
                        try{
                            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
                            time1 = LocalTime.parse(user1, formatter1);
                        }
                        catch(DateTimeParseException e){
                            System.out.println("Invalid format");
                        }
                        if(showManager.deleteShow(selectedScreen,movie,date1,time1))
                        {
                            System.out.println("Deleted");
                        }
                        else {
                            System.out.println("Not Deleted");
                        }
                        break;
                    case 3: System.out.println("1. Update Movie Title");
                        System.out.println("2. Update Show Date");
                        System.out.println("3. Update Show Time");
                        System.out.println("4. Back");
                        System.out.println("Enter your choice: ");
                        int num1 = sc.nextInt();
                        sc.nextLine();
                        switch (num1)
                        {
                            case 1:System.out.println("Enter old movie title: ");
                                String oldMovie = sc.nextLine();
                                Movie film1 = movieManager.findMovie(oldMovie);
                                if(film1 == null)
                                {
                                    System.out.println("Movie not found");
                                    break;
                                }System.out.println("Enter new movie title: ");
                                String newMovie = sc.nextLine();
                                Movie film2 = movieManager.findMovie(newMovie);
                                if(film2 == null)
                                {
                                    System.out.println("Movie not found.First update movie in Movie section");
                                    break;
                                }
                                System.out.println("Enter show date(in dd-MM-yy): ");
                                String userInput2 = sc.nextLine();
                                LocalDate date2 = null;
                                LocalTime time2 = null;
                                try
                                {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                                    date2 = LocalDate.parse(userInput2,formatter);
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Invalid format");
                                }
                                System.out.println("Enter show time(in HH:mm): ");
                                String user2 = sc.nextLine();
                                try{
                                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
                                    time2 = LocalTime.parse(user2, formatter1);
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Invalid format");
                                }
                                if(showManager.updateShowMovie(selectedScreen,oldMovie,newMovie,date2,time2))
                                {
                                    System.out.println("Updated");
                                }
                                else {
                                    System.out.println("Not updated");
                                }
                                break;
                            case 2: LocalDate newDate = null;
                                LocalDate oldDate = null;
                                LocalTime time3 = null;
                                System.out.println("Enter movie title: ");
                                String movie1 = sc.nextLine();
                                Movie title1 = movieManager.findMovie(movie1);
                                if(title1 == null)
                                {
                                    System.out.println("Movie not found");
                                    break;
                                }
                                System.out.println("Enter old show date(in dd-MM-yy): ");
                                String oldUserInput = sc.nextLine();
                                try
                                {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                                    oldDate = LocalDate.parse(oldUserInput,formatter);
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Invalid format");
                                }
                                System.out.println("Enter new show date(in dd-MM-yy): ");
                                String newUserInput = sc.nextLine();
                                try
                                {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                                    newDate = LocalDate.parse(newUserInput,formatter);
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Invalid format");
                                }
                                System.out.println("Enter show time(in HH:mm): ");
                                String user3 = sc.nextLine();
                                try{
                                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
                                    time3 = LocalTime.parse(user3, formatter1);
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Invalid format");
                                }
                                if(showManager.updateShowDate(selectedScreen,movie1,newDate,oldDate,time3))
                                {
                                    System.out.println("Updated");
                                }
                                else {
                                    System.out.println("Not updated");
                                }
                                break;
                            case 3: LocalDate date3 = null;
                                LocalTime newTime = null;
                                LocalTime oldTime = null;
                                System.out.println("Enter movie title: ");
                                String movie2 = sc.nextLine();
                                Movie title2 = movieManager.findMovie(movie2);
                                if(title2 == null)
                                {
                                    System.out.println("Movie not found");
                                    break;
                                }
                                System.out.println("Enter show date(in dd-MM-yy): ");
                                String userInput3 = sc.nextLine();
                                try
                                {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
                                    date3 = LocalDate.parse(userInput3,formatter);
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Invalid format");
                                }
                                System.out.println("Enter old show time(in HH:mm): ");
                                String oldUser = sc.nextLine();
                                try{
                                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
                                    oldTime = LocalTime.parse(oldUser, formatter1);
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Invalid format");
                                }
                                System.out.println("Enter new show time(in HH:mm): ");
                                String newUser = sc.nextLine();
                                try{
                                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
                                    newTime = LocalTime.parse(newUser, formatter1);
                                }
                                catch(DateTimeParseException e){
                                    System.out.println("Invalid format");
                                }
                                if(showManager.updateShowTime(selectedScreen,movie1,newTime,date3,oldTime))
                                {
                                    System.out.println("Updated");
                                }
                                else {
                                    System.out.println("Not updated");
                                }
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("Invalid choice");
                        }
                    case 4:
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
            else if (x==5)
            {
                break;
            }
            else{
                System.out.println("Invalid Choice");
            }
        }
    }
}
