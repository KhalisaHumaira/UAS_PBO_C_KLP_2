package minimarket.model;

/**
 * Abstract class untuk User
 * Menerapkan konsep Abstraction dan Encapsulation
 * @author Kelompok UAS PBO C
 */
public abstract class User {
    private String username;
    private String password;
    private String name;

    /**
     * Constructor User
     * @param username Username user
     * @param password Password user
     * @param name Nama lengkap user
     */
    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    // Getter dan Setter (Encapsulation)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method abstract untuk mendapatkan role user
     * @return Role user
     */
    public abstract String getRole();

    /**
     * Method untuk verifikasi password
     * @param password Password yang akan diverifikasi
     * @return true jika password benar
     */
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
