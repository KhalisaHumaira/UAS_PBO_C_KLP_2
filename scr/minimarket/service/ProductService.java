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

