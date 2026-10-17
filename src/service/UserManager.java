package service;

import DAO.UserDAO;
import model.User;

public class UserManager {
    UserDAO userDAO;
    public UserManager()
    {
        userDAO = new UserDAO();
    }

    public boolean registerUser(User user)
    {
        return userDAO.addUser(user);
    }
    public User loginUser(String phone)
    {
        return userDAO.findUserByPhone(phone);
    }
    public boolean updateUserName(User user,String newUsername)
    {
        if(user.getName().equals(newUsername))
        {
            return false;
        }
        boolean updated = userDAO.updateUserName(user,newUsername);

        if(updated)
        {
            user.setName(newUsername);
        }

        return updated;
    }
    public boolean updateUserPhoneNo(User user,String newPhoneNo)
    {
        if(user.getPhone().equals(newPhoneNo))
        {
            return false;
        }
        boolean updated = userDAO.updateUserPhoneNo(user,newPhoneNo);

        if(updated)
        {
            user.setPhone(newPhoneNo);
        }

        return updated;
    }
}
