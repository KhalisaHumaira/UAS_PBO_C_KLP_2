package minimarket.model;

/**
 * Class Product untuk merepresentasikan produk di minimarket
 * Menerapkan konsep Encapsulation
 * @author Kelompok UAS PBO C
 */
public class Product {
    private String id;
    private String name;
    private int price;
    private int stock;
    private String category;

    /**
     * Constructor Product
     * @param id ID produk
     * @param name Nama produk
     * @param price Harga produk
     * @param stock Stok produk
     * @param category Kategori produk
     */
    public Product(String id, String name, int price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    // Getter dan Setter (Encapsulation)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
/**
     * Method untuk mengurangi stok produk
     * @param quantity Jumlah yang dikurangi
     * @throws Exception Jika stok tidak mencukupi
     */
    public void reduceStock(int quantity) throws Exception {
        if (quantity > this.stock) {
            throw new Exception("Stok tidak mencukupi!");
        }
        this.stock -= quantity;
    }
     /**
     * Method untuk menambah stok produk
     * @param quantity Jumlah yang ditambahkan
     */
    public void addStock(int quantity) {
        this.stock += quantity;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (Rp %d)", id, name, price);
    }
}

