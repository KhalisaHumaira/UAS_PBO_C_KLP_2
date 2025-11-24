package minimarket.service;

import minimarket.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Service untuk mengelola autentikasi
 * Menerapkan konsep Collection (HashMap)
 * @author ...
 */
public class AuthService {
    private HashMap<String, User> users;

    /**
     * Constructor AuthService
     */
    public AuthService() {
        this.users = new HashMap<>();
    }

    /**
     * Register user baru
     * @param username Username
     * @param password Password
     * @param name Nama lengkap
     * @param isAdmin Apakah admin
     * @return true jika berhasil
     */
    public boolean register(String username, String password, String name, boolean isAdmin) {
        try {
            if (users.containsKey(username)) {
                return false;
            }

            User user;
            if (isAdmin) {
                user = new Admin(username, password, name);
            } else {
                user = new Customer(username, password, name);
            }

            users.put(username, user);
            return true;
        } catch (Exception e) {
            System.out.println("Error saat register: " + e.getMessage());
            return false;
        }
    }

    /**
     * Login user
     * @param username Username
     * @param password Password
     * @return User object jika berhasil, null jika gagal
     */
    public User login(String username, String password) {
        try {
            User user = users.get(username);
            if (user != null && user.verifyPassword(password)) {
                return user;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Error saat login: " + e.getMessage());
            return null;
        }
    } k
