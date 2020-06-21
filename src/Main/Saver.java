package Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс який зберігає інформацію
 */
public class Saver implements Serializable {
    private HashMap<String, ProductGroup> productGroups = new HashMap<String, ProductGroup>();
    private ArrayList<TransactionInfo> transactionHistory = new ArrayList<>();
    private Double profit = 0.0;

    /**
     * Конструктор
     * @param productGroups групи продуктів
     * @param transactionHistory історія транзакцій
     * @param profit прибуток
     */
    public Saver(HashMap<String, ProductGroup> productGroups,ArrayList<TransactionInfo> transactionHistory,Double profit){
        this.productGroups = productGroups;
        this.transactionHistory = transactionHistory;
        this.profit = profit;
    }

    /**
     * Повертає історію транзакцій
     * @return історія транзакцій
     */
    public ArrayList<TransactionInfo> getTransactionHistory() {
        return transactionHistory;
    }

    /**
     * Повертає прибуток
     * @return прибуток
     */
    public Double getProfit() {
        return profit;
    }

    /**
     * Повертає групи продуктів
     * @return групи продуктів
     */
    public HashMap<String, ProductGroup> getProductGroups() {
        return productGroups;
    }
}
