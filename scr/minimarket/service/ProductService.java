package minimarket.service;

import minimarket.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service untuk mengelola produk
 * Menerapkan konsep Collection dan Exception Handling
 * @author ...
 */
public class ProductService {
    private HashMap<String, Product> products;

    /**
     * Constructor ProductService
     */
    public ProductService() {
        this.products = new HashMap<>();
    }

    /**
     * Menambah produk baru
     * @param product Produk yang akan ditambahkan
     * @return true jika berhasil
     */
    public boolean addProduct(Product product) {
        try {
            if (products.containsKey(product.getId())) {
                return false;
            }
            products.put(product.getId(), product);
            return true;
        } catch (Exception e) {
            System.out.println("Error menambah produk: " + e.getMessage());
            return false;
        }
    }

    /**
     * Update produk
     * @param product Produk yang akan diupdate
     * @return true jika berhasil
     */
    public boolean updateProduct(Product product) {
        try {
            if (!products.containsKey(product.getId())) {
                return false;
            }
            products.put(product.getId(), product);
            return true;
        } catch (Exception e) {
            System.out.println("Error update produk: " + e.getMessage());
            return false;
        }
    }


