package net.enjoy.springboot.registration_login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class LogInController {

    private Storage registeredUsers;
    private Storage loggedInUsers;

    @Autowired
    public LogInController(Storage storage1,Storage storage2) {
        this.registeredUsers=storage1;
        this.loggedInUsers=storage2;

    }

    @PostMapping("/loginUser")
    public String loginUser(@RequestBody account account) {
        if (!LoginUtils.isValidEmail(account.getEmail())) {
            return "Invalid email format";
        }
        if (!registeredUsers.usersDatabase.containsKey(account.getEmail())) {
            return "Unknown account email";
        }

        Users temp = registeredUsers.usersDatabase.get(account.getEmail());
        if (temp == null) {
            return "Unknown account email";
        }

        if (!temp.getPassword().equals(account.getPassword())) {
            return "Incorrect password";
        }

        loggedInUsers.loginUsers(temp);
        return "User logged in successfully";
    }
    @GetMapping("/verifyUser/{email}")
    public String verifyUser(@PathVariable String email) {

        if (!registeredUsers.verifyUsersByEmail(email)){

            return "unknown account email ";
        }
        return  "user found";


    }

    @GetMapping("/getloginUser")
    public List<Users> getUsers() {
        return loggedInUsers.getUsers();
    }


    @PostMapping("/register")
    public String register(@RequestBody Users user) {

        if (registeredUsers.saveUser(user)) {
            return "User registered successfully!";
        } else {
            return "Registration failed. User may already exist.";
        }
    }
    @GetMapping("/getregistedUser")
    public List<Users> getregistedUsers() {
        return registeredUsers.getregisterdUsers();
    }
}

