package minimarket;

import minimarket.model.*;
import minimarket.service.*;
import minimarket.util.*;
import java.util.*;

/**
 * Main class untuk menjalankan Sistem Minimarket
 * @author Kelompok UAS PBO C
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static AuthService authService = new AuthService();
    private static ProductService productService = new ProductService();
    private static TransactionService transactionService = new TransactionService();
    private static User currentUser = null;

    public static void main(String[] args) {
        initializeData();
        
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                if (currentUser instanceof Admin) {
                    showAdminMenu();
                } else {
                    showUserMenu();
                }
            }
        }
    }
     /**
     * Inisialisasi data awal sistem
     */
    private static void initializeData() {
        // Admin default
        authService.register("admin", "admin123", "Admin", true);
        
        // User default
        authService.register("user1", "user123", "Customer", false);
        
        // Produk default
        productService.addProduct(new Product("P001", "Indomie Goreng", 3000, 100, "Makanan"));
        productService.addProduct(new Product("P002", "Aqua 600ml", 3500, 80, "Minuman"));
        productService.addProduct(new Product("P003", "Mie Sedaap", 2500, 120, "Makanan"));
        productService.addProduct(new Product("P004", "Teh Botol", 4000, 60, "Minuman"));
        productService.addProduct(new Product("P005", "Chitato", 8000, 50, "Snack"));
    }
     /**
     * Menampilkan menu login
     */
    private static void showLoginMenu() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("SISTEM MINIMARKET - LOGIN");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        CLIHelper.printSeparator();
        
        try {
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Terima kasih!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    CLIHelper.pressEnter();
            }
        } catch (InputMismatchException e) {
            System.out.println("Input harus berupa angka!");
            scanner.nextLine();
            CLIHelper.pressEnter();
        }
    }
