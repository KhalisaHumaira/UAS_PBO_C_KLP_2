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
