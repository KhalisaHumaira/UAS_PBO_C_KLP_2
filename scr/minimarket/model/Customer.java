package minimarket.model;

/**
 * Class Customer yang mewarisi User
 * Menerapkan konsep Inheritance dan Polymorphism
 */
public class Customer extends User {

    /**
     * Constructor Customer
     * @param username Username customer
     * @param password Password customer
     * @param name Nama lengkap customer
     */
    public Customer(String username, String password, String name) {
        super(username, password, name);
    }

    /**
     * Override method getRole dari User
     * Menerapkan Polymorphism
     * @return Role customer
     */
    @Override
    public String getRole() {
        return "Customer";
    }
}
