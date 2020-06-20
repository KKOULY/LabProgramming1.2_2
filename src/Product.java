import java.io.Serializable;

/**
 * Клас продукту
 */
public class Product implements Serializable {
    private String name;
    private String description;
    private String maker;
    private int number;
    private double price;
    private ProductGroup productGroup;

    /**
     * Конструктор продукту
     * @param name назва продукту
     * @param description опис продукту
     * @param maker виробник продукту
     * @param number кількість товару на складі
     * @param price ціна за одиницю
     * @param productGroup  група цього продукту
     */
    public Product(String name, String description, String maker,int number, double price, ProductGroup productGroup){
    this.name=getRightString(name);
    this.description=getRightString(description);
    this.maker=maker;
    this.number=number;
    this.price=price;
    this.productGroup = productGroup;
    }

    /**
     * Виправляє ім'я
     * @param name ім'я яке ввів користувач
     * @return перевірене ім'я
     */
    private String getRightString(String name) {
        String temp = name.toLowerCase();
        if(temp.length()>0) {
            temp = Character.toUpperCase(temp.charAt(0))+temp.substring(1,temp.length());
        }
        return temp;
    }

    /**
     * Ім'я товару
     * @return повертає ім'я товару
     */
    public String getName() {
        return name;
    }

    /**
     * Задає нове ім'я
     * @param name нове ім'я
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Опис товару
     * @return повертає опис товару
     */
    public String getDescription() {
        return description;
    }

    /**
     * Задає опис товару
     * @param description новий опис товару
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Виробник товару
     * @return повертає виробника товару
     */
    public String getMaker() {
        return maker;
    }

    /**
     * Задає нового виробника
     * @param maker новий виробник
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * Повертає кількість товару на складі
     * @return кількість товару на складі
     */
    public int getNumber() {
        return number;
    }

    /**
     * Нова кількість товару на складі
     * @param number кількість товару на складі
     */
    public void setNumber(int number) {
        this.number = number;
        if (number<0){
            this.number=0;
        }
    }

    /**
     * Ціна одиницю товару
     * @return ціна за одиницю
     */
    public double getPrice() {
        return price;
    }

    /**
     * Група продукту
     * @return провертає групу продукту
     */
    public ProductGroup getProductGroup() {
        return productGroup;
    }

    /**
     * Задає нову ціну продукту
     * @param price нова ціна продукту
     */
    public void setPrice(double price) {
        this.price = price;
        if (price<0){
            this.price=0;
        }
    }

    /**
     * Інформація про продукт
     * @return стрічка з інформацією
     */
    @Override
    public String toString() {
        return "Продукт." + " Назва: " + name +". Опис: " + description +". Виробник: " + maker + ". Кількіть на складі: " + number + ". Ціна за одиницю: " + price+"грн.";
    }
}
