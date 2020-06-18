import java.util.HashMap;

public class ProductGroup {
    private HashMap<String, Product> products = new HashMap<String, Product>();
    private String name;
    private String description;
    public ProductGroup(String name, String description){
        this.name=name;
        this.description=description;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public HashMap<String, Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Группа продуктів. Назва: "+ name + ". Опис: " + description +'.';
    }
}
