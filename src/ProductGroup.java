import java.io.Serializable;
import java.util.HashMap;

/**
 * Клас групи продуктів
 */
public class ProductGroup implements Serializable {
    private HashMap<String, Product> products = new HashMap<String, Product>();
    private String name;
    private String description;

    /**
     * Конструктор групи продуктів
     * @param name назва продукту
     * @param description опис продукту
     */
    public ProductGroup(String name, String description){
        this.name=getRightString(name);
        this.description=getRightString(description);
    }

    /**
     * Коректує назву групи
     * @param name назва яку ввели
     * @return повертає правильну назву
     */
    private String getRightString(String name) {
        String temp = name.toLowerCase();
        if(temp.length()>0) {
            temp = Character.toUpperCase(temp.charAt(0))+temp.substring(1,temp.length());
        }
        return temp;
    }

    /**
     * Опис групи
     * @return повертає опис групи
     */
    public String getDescription() {
        return description;
    }

    /**
     * Задає опис групи
     * @param description новий опис групи
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Назва групи
     * @return повертає назву групи продутів
     */
    public String getName() {
        return name;
    }

    /**
     * Задає назву групи продуків
     * @param name нова назва
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Повертає продукти які знаходяться в групі
     * @return продукти групи
     */
    public HashMap<String, Product> getProducts() {
        return products;
    }

    /**
     * Задає список продуктів
     * @param products новий список продуктів
     */
    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }

    /**
     * Стрічка групи продуктів
     * @return повертає стрічку групи продуктів
     */
    @Override
    public String toString() {
        return "Группа продуктів. Назва: "+ name + ". Опис: " + description +'.';
    }

    public Double getBank() {
        double bank = 0;
        for(String key:products.keySet()){
            bank+=(products.get(key).getNumber()*products.get(key).getPrice());
        }
        return bank;
    }
}
