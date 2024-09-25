package net.enjoy.springboot.registration_login;

public class account {

    private String email;
    private String password;

    public account(String email, String password) {
        this.email = email;
        this.password = LoginUtils.hashPassword(password);
    }

    // Getters and Setters






    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = LoginUtils.hashPassword(password);
    }
}
