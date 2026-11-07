package main.java.com.scholarship.service;

public class AuthService {
    
    private final String realName = "Jay Mark Lacano";
    private final String realPassword = "12345";

    public boolean login(String name, String password) {

        return name.equals(realName) &&
               password.equals(realPassword);
    }
}
