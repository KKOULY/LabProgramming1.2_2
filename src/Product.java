import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String description;
    private String maker;
    private int number;
    private double price;
    private ProductGroup productGroup;
    public Product(String name, String description, String maker,int number, double price, ProductGroup productGroup){
    this.name=getRightString(name);
    this.description=getRightString(description);
    this.maker=maker;
    this.number=number;
    this.price=price;
    this.productGroup = productGroup;
    }

    private String getRightString(String name) {
        String temp = name.toLowerCase();
        if(temp.length()>0) {
            temp = Character.toUpperCase(temp.charAt(0))+temp.substring(1,temp.length());
        }
        return temp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        if (number<0){
            this.number=0;
        }
    }

    public double getPrice() {
        return price;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setPrice(double price) {
        this.price = price;
        if (price<0){
            this.price=0;
        }
    }

    @Override
    public String toString() {
        return "Продукт." + " Назва: " + name +". Опис: " + description +". Виробник: " + maker + ". Кількіть на складі: " + number + ". Ціна за одиницю: " + price+"грн.";
    }
}
