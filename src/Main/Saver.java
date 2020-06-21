package Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Saver implements Serializable {
    private HashMap<String, ProductGroup> productGroups = new HashMap<String, ProductGroup>();
    private ArrayList<TransactionInfo> transactionHistory = new ArrayList<>();
    private Double profit = 0.0;
    public Saver(HashMap<String, ProductGroup> productGroups,ArrayList<TransactionInfo> transactionHistory,Double profit){
        this.productGroups = productGroups;
        this.transactionHistory = transactionHistory;
        this.profit = profit;
    }

    public ArrayList<TransactionInfo> getTransactionHistory() {
        return transactionHistory;
    }

    public Double getProfit() {
        return profit;
    }

    public HashMap<String, ProductGroup> getProductGroups() {
        return productGroups;
    }
}
