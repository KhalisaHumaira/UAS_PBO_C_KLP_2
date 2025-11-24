package minimarket.model;

/**

- Class TransactionItem untuk item dalam transaksi
- @author Kelompok UAS PBO C
  */
  public class TransactionItem {
  private Product product;
  private int quantity;
  private int subtotal;
  
  /**
  - Constructor TransactionItem
  - @param product Produk yang dibeli
  - @param quantity Jumlah produk
    */
    public TransactionItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
    this.subtotal = product.getPrice() * quantity;
    }
  
  // Getter dan Setter
  public Product getProduct() {
  return product;
  }
  
  public void setProduct(Product product) {
  this.product = product;
  calculateSubtotal();
  }
  
  public int getQuantity() {
  return quantity;
  }
  
  public void setQuantity(int quantity) {
  this.quantity = quantity;
  calculateSubtotal();
  }
  
  public int getSubtotal() {
  return subtotal;
  }
  
  /**
  - Menghitung subtotal
    */
    private void calculateSubtotal() {
    this.subtotal = product.getPrice() * quantity;
    }
    }
