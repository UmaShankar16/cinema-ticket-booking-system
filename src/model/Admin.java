package model;

public class Admin {
    private int adminId;
    private String cinemaHallName;
    private String cinemaHallLocation;
    private String password;

    public Admin(String cinemaHallName, String cinemaHallLocation,
                 String password)
    {
        this.cinemaHallName = cinemaHallName;
        this.cinemaHallLocation = cinemaHallLocation;
        this.password = password;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getCinemaHallName() {
        return cinemaHallName;
    }

    public void setCinemaHallName(String cinemaHallName) {
        this.cinemaHallName = cinemaHallName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCinemaHallLocation() {
        return cinemaHallLocation;
    }

    public void setCinemaHallLocation(String cinemaHallLocation) {
        this.cinemaHallLocation = cinemaHallLocation;
    }
}
