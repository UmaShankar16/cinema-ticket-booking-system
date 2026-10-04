package service;

import DAO.AdminDAO;
import model.Admin;
import model.CinemaHall;

public class AdminManager {
    private AdminDAO adminDAO;
    public AdminManager()
    {
        adminDAO = new AdminDAO();
    }

    public boolean addAdmin(CinemaHall hall,Admin admin)
    {
        return adminDAO.addAdmin(hall,admin);
    }
    public boolean loginAdmin(Admin admin)
    {
        Admin selectedAdmin = adminDAO.findAdminByUserName(admin.getAdminUserName());
        if(selectedAdmin == null)
        {
            return false;
        }
        if(admin.getPassword().equals(selectedAdmin.getPassword()))
        {
            return true;
        }
        return false;
    }
    public boolean updateAdminUserName(Admin admin,String newUserName)
    {
        if(admin.getAdminUserName().equals(newUserName))
        {
            return false;
        }

        boolean updated = adminDAO.updateAdminUserName(admin,newUserName);

        if(updated)
        {
            admin.setAdminUserName(newUserName);
        }

        return updated;
    }
    public boolean updateAdminPassword(Admin admin,String newPassword)
    {
        if(admin.getPassword().equals(newPassword))
        {
            return false;
        }

        boolean updated = adminDAO.updateAdminPassword(admin,newPassword);

        if(updated)
        {
            admin.setPassword(newPassword);
        }

        return updated;
    }
}
