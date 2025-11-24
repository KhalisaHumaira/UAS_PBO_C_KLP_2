package minimarket.service;

import minimarket.model.Transaction;
import minimarket.model.TransactionItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Service untuk mengelola transaksi
 * Menerapkan konsep Collection dan Exception Handling
 * @author Kelompok UAS PBO C
 */
public class TransactionService {
    private List<Transaction> transactions;
    private HashMap<String, List<Transaction>> userTransactions;

    /**
     * Constructor TransactionService
     */
    public TransactionService() {
        this.transactions = new ArrayList<>();
        this.userTransactions = new HashMap<>();
    }

    /**
     * Menambah transaksi baru
     * @param transaction Transaksi yang akan ditambahkan
     */
    public void addTransaction(Transaction transaction) {
        try {
            transactions.add(transaction);

            // Simpan ke HashMap berdasarkan username
            String username = transaction.getUsername();
            if (!userTransactions.containsKey(username)) {
                userTransactions.put(username, new ArrayList<>());
            }
            userTransactions.get(username).add(transaction);

        } catch (Exception e) {
            System.out.println("Error menambah transaksi: " + e.getMessage());
        }
    }

    /**
     * Mendapatkan semua transaksi
     * @return List transaksi
     */
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    /**
     * Mendapatkan transaksi berdasarkan username
     * @param username Username
     * @return List transaksi user
     */
    public List<Transaction> getTransactionsByUser(String username) {
        try {
            return userTransactions.getOrDefault(username, new ArrayList<>());
        } catch (Exception e) {
            System.out.println("Error get transaksi user: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Mendapatkan transaksi berdasarkan ID
     * @param transactionId ID transaksi
     * @return Transaction object
     */
    public Transaction getTransactionById(String transactionId) {
        try {
            return transactions.stream()
                    .filter(t -> t.getTransactionId().equals(transactionId))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            System.out.println("Error get transaksi: " + e.getMessage());
            return null;
        }
    }

    /**
     * Generate laporan penjualan
     */
    public void generateSalesReport() {
        try {
            System.out.println("========================================");
            System.out.println("         LAPORAN PENJUALAN");
            System.out.println("========================================");

            if (transactions.isEmpty()) {
                System.out.println("Belum ada transaksi.");
                return;
            }

            int totalTransactions = transactions.size();
            int totalRevenue = 0;
            HashMap<String, Integer> productSales = new HashMap<>();

            for (Transaction t : transactions) {
                totalRevenue += t.getTotalPrice();

                for (TransactionItem item : t.getItems()) {
                    String productName = item.getProduct().getName();
                    int qty = item.getQuantity();
                    productSales.put(productName,
                            productSales.getOrDefault(productName, 0) + qty);
                }
            }

            System.out.println("Total Transaksi  : " + totalTransactions);
            System.out.println("Total Pendapatan : Rp " + totalRevenue);
            System.out.println("\nProduk Terlaris:");
            System.out.println("----------------------------------------");

            productSales.entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .limit(5)
                    .forEach(entry ->
                            System.out.printf("%-25s : %d terjual%n",
                                    entry.getKey(), entry.getValue()));

            System.out.println("========================================");

        } catch (Exception e) {
            System.out.println("Error generate laporan: " + e.getMessage());
        }
    }

    /**
     * Mendapatkan total pendapatan
     * @return Total pendapatan
     */
    public int getTotalRevenue() {
        try {
            return transactions.stream()
                    .mapToInt(Transaction::getTotalPrice)
                    .sum();
        } catch (Exception e) {
            System.out.println("Error get total revenue: " + e.getMessage());
            return 0;
        }
    }
}
