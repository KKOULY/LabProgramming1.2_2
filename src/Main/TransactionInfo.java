package Main;

import java.io.Serializable;
import java.util.Date;

public class TransactionInfo implements Serializable {
    private Date date;
    private String nameProduct;
    private int num;
    private double sum;
    public TransactionInfo(Date date,String nameProduct,int num,Double sum){
        this.date = date;
        this.nameProduct = nameProduct;
        this.num = num;
        this.sum = sum;
    }

    public String getDate(){
        return date.toString();
    }

    public String getNameProduct(){
        return nameProduct;
    }

    public String getNum() {
        return String.valueOf(num);
    }

    public String getSum() {
        return String.valueOf(sum);
    }
}
