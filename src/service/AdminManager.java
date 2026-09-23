package service;

import model.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminManager {
    private List<Admin> admins;

    public AdminManager()
    {
        admins = new ArrayList<>();
    }

    public boolean addAdmin(Admin admin)
    {
        for(Admin admin1 : admins)
        {
            if(admin1.getCinemaHallName().equalsIgnoreCase(admin.getCinemaHallName()) &&
            admin1.getCinemaHallLocation().equalsIgnoreCase(admin.getCinemaHallLocation()) &&
            admin1.getPassword().equals(admin.getPassword()))
            {
                return false;
            }
        }
        admins.add(admin);
        return true;
    }

    public boolean loginAdmin(Admin admin)
    {
        for(Admin admin1 : admins)
        {
            if(admin1.getCinemaHallName().equalsIgnoreCase(admin.getCinemaHallName())&&
            admin1.getCinemaHallLocation().equalsIgnoreCase(admin.getCinemaHallLocation())&&
            admin1.getPassword().equals(admin.getPassword()))
            {
                return true;
            }
        }
        return false;
    }
}
