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
    /**
     * Proses login
     */
    private static void login() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("LOGIN");
        
        try {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            
            currentUser = authService.login(username, password);
            
            if (currentUser != null) {
                System.out.println("\nLogin berhasil! Selamat datang, " + currentUser.getName());
                CLIHelper.pressEnter();
            } else {
                System.out.println("\nUsername atau password salah!");
                CLIHelper.pressEnter();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            CLIHelper.pressEnter();
        }
    }

    /**
     * Proses register
     */
    private static void register() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("REGISTER");
        
        try {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.print("Nama Lengkap: ");
            String name = scanner.nextLine();
            
            if (authService.register(username, password, name, false)) {
                System.out.println("\nRegistrasi berhasil! Silakan login.");
            } else {
                System.out.println("\nUsername sudah digunakan!");
            }
            CLIHelper.pressEnter();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            CLIHelper.pressEnter();
        }
    }

    /**
     * Menu untuk Admin
     */
    private static void showAdminMenu() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("MENU ADMIN - " + currentUser.getName());
        System.out.println("1. Kelola Produk");
        System.out.println("2. Lihat Semua Transaksi");
        System.out.println("3. Lihat Laporan Penjualan");
        System.out.println("4. Lihat Data User");
        System.out.println("5. Logout");
        CLIHelper.printSeparator();
        
        try {
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    viewAllTransactions();
                    break;
                case 3:
                    viewSalesReport();
                    break;
                case 4:
                    viewAllUsers();
                    break;
                case 5:
                    currentUser = null;
                    System.out.println("Logout berhasil!");
                    CLIHelper.pressEnter();
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

    /**
     * Menu kelola produk
     */
    private static void manageProducts() {
        while (true) {
            CLIHelper.clearScreen();
            CLIHelper.printHeader("KELOLA PRODUK");
            System.out.println("1. Lihat Semua Produk");
            System.out.println("2. Tambah Produk");
            System.out.println("3. Edit Produk");
            System.out.println("4. Hapus Produk");
            System.out.println("5. Cari Produk");
            System.out.println("6. Kembali");
            CLIHelper.printSeparator();
            
            try {
                System.out.print("Pilih menu: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                switch (choice) {
                    case 1:
                        viewAllProducts();
                        break;
                    case 2:
                        addProduct();
                        break;
                    case 3:
                        editProduct();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                    case 5:
                        searchProduct();
                        break;
                    case 6:
                        return;
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
    }

    /**
     * Menampilkan semua produk
     */
    private static void viewAllProducts() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("DAFTAR PRODUK");
        
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Belum ada produk.");
        } else {
            System.out.printf("%-10s %-20s %-12s %-10s %-15s%n", 
                "ID", "Nama", "Harga", "Stok", "Kategori");
            CLIHelper.printSeparator();
            for (Product p : products) {
                System.out.printf("%-10s %-20s Rp%-10d %-10d %-15s%n",
                    p.getId(), p.getName(), p.getPrice(), p.getStock(), p.getCategory());
            }
        }
        CLIHelper.pressEnter();
    }
     /**
     * Menambah produk baru
     */
    private static void addProduct() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("TAMBAH PRODUK");
        
        try {
            System.out.print("ID Produk: ");
            String id = scanner.nextLine();
            System.out.print("Nama Produk: ");
            String name = scanner.nextLine();
            System.out.print("Harga: ");
            int price = scanner.nextInt();
            System.out.print("Stok: ");
            int stock = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Kategori: ");
            String category = scanner.nextLine();
            
            Product product = new Product(id, name, price, stock, category);
            if (productService.addProduct(product)) {
                System.out.println("\nProduk berhasil ditambahkan!");
            } else {
                System.out.println("\nID produk sudah ada!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid!");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        CLIHelper.pressEnter();
    }

    /**
     * Edit produk
     */
    private static void editProduct() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("EDIT PRODUK");
        
        try {
            System.out.print("ID Produk yang akan diedit: ");
            String id = scanner.nextLine();
            
            Product product = productService.getProductById(id);
            if (product == null) {
                System.out.println("Produk tidak ditemukan!");
                CLIHelper.pressEnter();
                return;
            }
            
            System.out.println("\nData saat ini:");
            System.out.println("Nama: " + product.getName());
            System.out.println("Harga: " + product.getPrice());
            System.out.println("Stok: " + product.getStock());
            System.out.println("Kategori: " + product.getCategory());
            
            System.out.print("\nNama baru (enter untuk skip): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) product.setName(name);
            
            System.out.print("Harga baru (0 untuk skip): ");
            int price = scanner.nextInt();
            if (price > 0) product.setPrice(price);
            
            System.out.print("Stok baru (-1 untuk skip): ");
            int stock = scanner.nextInt();
            if (stock >= 0) product.setStock(stock);
            
            scanner.nextLine();
            System.out.print("Kategori baru (enter untuk skip): ");
            String category = scanner.nextLine();
            if (!category.isEmpty()) product.setCategory(category);
            
            productService.updateProduct(product);
            System.out.println("\nProduk berhasil diupdate!");
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid!");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        CLIHelper.pressEnter();
    }

    /**
     * Hapus produk
     */
    private static void deleteProduct() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("HAPUS PRODUK");
        
        try {
            System.out.print("ID Produk yang akan dihapus: ");
            String id = scanner.nextLine();
            
            System.out.print("Yakin ingin menghapus? (y/n): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("y")) {
                if (productService.deleteProduct(id)) {
                    System.out.println("\nProduk berhasil dihapus!");
                } else {
                    System.out.println("\nProduk tidak ditemukan!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        CLIHelper.pressEnter();
    }
    /**
     * Cari produk
     */
    private static void searchProduct() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("CARI PRODUK");
        
        try {
            System.out.print("Masukkan nama produk: ");
            String keyword = scanner.nextLine();
            
            List<Product> results = productService.searchProducts(keyword);
            
            if (results.isEmpty()) {
                System.out.println("\nProduk tidak ditemukan!");
            } else {
                System.out.println("\nHasil pencarian:");
                System.out.printf("%-10s %-20s %-12s %-10s %-15s%n", 
                    "ID", "Nama", "Harga", "Stok", "Kategori");
                CLIHelper.printSeparator();
                for (Product p : results) {
                    System.out.printf("%-10s %-20s Rp%-10d %-10d %-15s%n",
                        p.getId(), p.getName(), p.getPrice(), p.getStock(), p.getCategory());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        CLIHelper.pressEnter();
    }

    /**
     * Lihat semua transaksi
     */
    private static void viewAllTransactions() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("SEMUA TRANSAKSI");
        
        List<Transaction> transactions = transactionService.getAllTransactions();
        
        if (transactions.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            for (Transaction t : transactions) {
                t.displayInvoice();
                CLIHelper.printSeparator();
            }
        }
        CLIHelper.pressEnter();
    }

    /**
     * Lihat laporan penjualan
     */
    private static void viewSalesReport() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("LAPORAN PENJUALAN");
        
        transactionService.generateSalesReport();
        CLIHelper.pressEnter();
    }

    /**
     * Lihat semua user
     */
    private static void viewAllUsers() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("DATA USER");
        
        List<User> users = authService.getAllUsers();
        
        System.out.printf("%-15s %-20s %-10s%n", "Username", "Nama", "Role");
        CLIHelper.printSeparator();
        for (User u : users) {
            String role = u instanceof Admin ? "Admin" : "User";
            System.out.printf("%-15s %-20s %-10s%n", 
                u.getUsername(), u.getName(), role);
        }
        CLIHelper.pressEnter();
    }

    /**
     * Menu untuk User
     */
    private static void showUserMenu() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("MENU USER - " + currentUser.getName());
        System.out.println("1. Lihat Produk");
        System.out.println("2. Belanja");
        System.out.println("3. Riwayat Transaksi");
        System.out.println("4. Cari Produk");
        System.out.println("5. Logout");
        CLIHelper.printSeparator();
        
        try {
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    viewAllProducts();
                    break;
                case 2:
                    shopping();
                    break;
                case 3:
                    viewUserTransactions();
                    break;
                case 4:
                    searchProduct();
                    break;
                case 5:
                    currentUser = null;
                    System.out.println("Logout berhasil!");
                    CLIHelper.pressEnter();
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

    /**
     * Proses belanja
     */
    private static void shopping() {
        CLIHelper.clearScreen();
        CLIHelper.printHeader("BELANJA");
        
        Transaction transaction = new Transaction(
            "TRX" + System.currentTimeMillis(), 
            currentUser.getUsername()
        );
        
        while (true) {
            try {
                viewAllProducts();
                
                System.out.print("\nID Produk (0 untuk selesai): ");
                String productId = scanner.nextLine();
                
                if (productId.equals("0")) break;
                
                Product product = productService.getProductById(productId);
                if (product == null) {
                    System.out.println("Produk tidak ditemukan!");
                    CLIHelper.pressEnter();
                    continue;
                }
                
                System.out.print("Jumlah: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                
                if (quantity > product.getStock()) {
                    System.out.println("Stok tidak mencukupi! Stok tersedia: " + product.getStock());
                    CLIHelper.pressEnter();
                    continue;
                }
                
                TransactionItem item = new TransactionItem(product, quantity);
                transaction.addItem(item);
                
                System.out.println("\nProduk ditambahkan ke keranjang!");
                CLIHelper.pressEnter();
                
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid!");
                scanner.nextLine();
                CLIHelper.pressEnter();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                CLIHelper.pressEnter();
            }
        }
        
        if (transaction.getItems().isEmpty()) {
            System.out.println("Tidak ada produk yang dibeli!");
            CLIHelper.pressEnter();
            return;
        }
        
        // Proses pembayaran
        CLIHelper.clearScreen();
        transaction.displayInvoice();
        
        System.out.print("\nMasukkan jumlah uang: Rp ");
        try {
            int payment = scanner.nextInt();
            scanner.nextLine();
            
            if (payment < transaction.getTotalPrice()) {
                System.out.println("Uang tidak cukup!");
                CLIHelper.pressEnter();
                return;
            }
            
            int change = payment - transaction.getTotalPrice();
            System.out.println("Kembalian: Rp " + change);
            
            // Update stok dan simpan transaksi
            for (TransactionItem item : transaction.getItems()) {
                Product p = item.getProduct();
                p.setStock(p.getStock() - item.getQuantity());
                productService.updateProduct(p);
            }
            
            transactionService.addTransaction(transaction);
            System.out.println("\nTransaksi berhasil!");
            
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid!");
            scanner.nextLine();
        }
        
        CLIHelper.pressEnter();
    }
