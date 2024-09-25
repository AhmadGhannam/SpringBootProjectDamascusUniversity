package net.enjoy.springboot.registration_login;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Storage {
     static Map<String, Users> usersDatabase = new HashMap<>();

    private List<Users> users = new ArrayList<>();
    public boolean saveUser(Users user) {
        if (usersDatabase.containsKey(user.getEmail())) {
            return false; // User already exists
        }
        usersDatabase.put(user.getEmail(), user);
//        users.add(user);

        return true;
    }



    public void loginUsers(Users user) {
        users.add(user);
    }

    public List<Users> getUsers() {
        return users;
    }

    public List<Users> getregisterdUsers() {
        return new ArrayList<>(usersDatabase.values());
    }

    public boolean verifyUsersByEmail(String email) {
        for (Users user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    public Users findUsersByEmail(String email) {
        for (Users user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public boolean updateUsers(String id, Users updateUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, updateUser);
                return true;
            }
        }
        return false;
    }
}
