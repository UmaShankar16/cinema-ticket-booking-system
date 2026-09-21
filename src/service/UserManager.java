package service;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;
    public UserManager()
    {
        users = new ArrayList<>();
    }
    public boolean registerUser(User user)
    {
        for(User user1 : users)
        {
            if(user1.getPhone().equals(user.getPhone()))
            {
                return false;
            }
        }
        return users.add(user);
    }
    public User loginUser(String phone)
    {
        User foundUser = null;
        for(User user : users)
        {
            if(user.getPhone().equals(phone))
            {
                foundUser = user;
                break;
            }
        }
        return foundUser;
    }
}
