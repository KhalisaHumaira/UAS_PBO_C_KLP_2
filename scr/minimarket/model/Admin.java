package minimarket.model;

/**
- Class Admin yang mewarisi User
- Menerapkan konsep Inheritance dan Polymorphism
- @author Kelompok UAS PBO C
*/
public class Admin extends User {

    /**
    - Constructor Admin
    - @param username Username admin
    - @param password Password admin
    - @param name Nama lengkap admin
    */
    public Admin(String username, String password, String name) {
        super(username, password, name);
    }


