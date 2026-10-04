package model;

public class Admin {
    private int adminId;
    private String adminUserName;
    private int hallId;
    private String password;

    public Admin(String adminUserName,String password)
    {
        this.adminUserName = adminUserName;
        this.password = password;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    public int getAdminId() {
        return adminId;
    }
    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }
    public String getAdminUserName() {
        return adminUserName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setHallId(int hallId) {
        this.hallId = hallId;
    }
    public int getHallId() {
        return hallId;
    }
}
