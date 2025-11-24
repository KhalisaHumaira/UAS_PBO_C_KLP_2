package minimarket.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class Transaction untuk merepresentasikan transaksi
 * Menerapkan konsep Encapsulation dan Collection
 * @author Kelompok UAS PBO C
 */
public class Transaction {
    private String transactionId;
    private String username;
    private Date date;
    private List<TransactionItem> items;
    private int totalPrice;

