import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String description;
    private String maker;
    private int number;
    private double price;
    public Product(String name, String description, String maker,int number, double price){
    this.name=name;
    this.description=description;
    this.maker=maker;
    this.number=number;
    this.price=price;
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
