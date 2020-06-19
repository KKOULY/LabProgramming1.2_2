import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Клас для роботи з магазином
 */
public class Shop {
    /**
     * Всі группи продуктів магазину
     */
    private HashMap<String, ProductGroup> productGroups = new HashMap<String, ProductGroup>();

    /**
     * Повертає группи продуктів магазину
     * @return группи продуктів магазину
     */
    public HashMap<String, ProductGroup> getProductGroups() {
        return productGroups;
    }

    /**
     * Встановлює нові группи продуктів магазину
     * @param productGroups
     */
    public void setProductGroups(HashMap<String, ProductGroup> productGroups) {
        this.productGroups = productGroups;
        save();
    }

    /**
     * Зберігає всі дані про магазин
     */
    public void save() {
            try {
                ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream("products.dat"));
                saver.writeObject(productGroups);
                saver.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * Завантажує дані про магазин
     */
    public void load(){
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream("products.dat"));
            productGroups=(HashMap<String, ProductGroup>)reader.readObject();
            reader.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Всі групи товарів
     * @return  групи товарів
     */
    public ArrayList<ProductGroup> getAllProductGroups() {
        ArrayList<ProductGroup> productGroupsTemp = new ArrayList<ProductGroup>();
        for(String s:productGroups.keySet()){
            productGroupsTemp.add(productGroups.get(s));
        }
        return productGroupsTemp;
    }

    /**
     *Метод для пошуку товару за назвою
     * @param name назва групи продуктів
     * @return продукти
     */
    public ArrayList<Product> findProduct(String name){
    ArrayList<Product> products = new ArrayList<Product>();
    for (Product p : getAllProducts()){
        boolean find = false;
        if (name.length()<=p.getName().length()) {
            for (int i = 0; i < name.length(); i++) {
                if (p.getName().charAt(i) == name.charAt(i)) find = true;
                else {
                    find = false;
                    break;
                }
            }
        }
        if (find==true){
            products.add(p);
        }
    }
    return products;
    }

    /**
     * Метод для продажу продукта
     * @param name назва продукту
     * @param numberOfSell кількість проданих товарів
     * @throws IllegalArgumentException кидає помилку, якщо намагаєшся продати більше, ніж є продуктів
     */
    public void sellProduct(String name, int numberOfSell)throws IllegalArgumentException{
        for (Product product : getAllProducts()){
            if (product.getName().equals(name)){
                if (product.getNumber()<numberOfSell) throw new IllegalArgumentException("Ви намагаєтась продати більше товару, чим є на складі!");
                else product.setNumber(product.getNumber()-numberOfSell);
            }
        }
        save();
    }

    /**
     * Метод для покупки продуктів
     * @param name назва продукту
     * @param numberOfBuy кількість куплених продуктів
     */
    public void buyProduct(String name, int numberOfBuy){
        for (Product product : getAllProducts()){
            if (product.getName().equals(name)){
               product.setNumber(product.getNumber()+numberOfBuy);
            }
        }
        save();
    }

    /**
     * Створити нову групу продуктів
     * @param name назва групи продуктів
     * @param description опис групи продуктів
     * @throws ExceptionSameName кидає помилку, якшо існує група з такою назвою
     */
    public void addProductGroup(String name, String description) throws ExceptionSameName{
        ProductGroup productGroup = new ProductGroup(name, description);
        if (productGroups.containsKey(productGroup.getName())) throw new ExceptionSameName(productGroup);
        productGroups.put(productGroup.getName(), productGroup);
        save();
    }

    /**
     * Створити нову групу продуктів
     * @param productGroup нова група
     * @throws ExceptionSameName кидає помилку, якшо існує група з такою назвою
     */
    public void addProductGroup(ProductGroup productGroup) throws ExceptionSameName{
        if (productGroups.containsKey(productGroup.getName())) throw new ExceptionSameName(productGroup);
        productGroups.put(productGroup.getName(), productGroup);
        save();
    }

    /**
     * Ціна всіх продуктів
     * @return повертає ціну всіх продуктів
     */
    public double priceOfAllProducts(){
        double price=0;
      for (Product p : getAllProducts()){
          price+=p.getPrice()*p.getNumber();
      }
      return price;
     }

    /**
     * Ціна продуктів в групі
     * @param groupName назва групи
     * @return повертає ціну продуктів в групі
     */
    public double priceOfProductsInAGroup(String groupName){
        double price=0;
        ProductGroup productGroup = productGroups.get(groupName);
        for (String key : productGroup.getProducts().keySet()){
            price+= productGroup.getProducts().get(key).getPrice()*productGroup.getProducts().get(key).getNumber();
        }
        return price;
    }

    /**
     * Додає новий продукт
     * @param productGroup группа в якій знаходиться продукт
     * @param name назва продукту
     * @param description опис продукту
     * @param maker виробник продукту
     * @param number кількість товару на складі
     * @param price ціна за одиницю товару
     * @throws ExceptionSameName кидає помилку, якщо існує товар з такою назвою
     */
    public void addProduct(ProductGroup productGroup, String name, String description, String maker, int number, double price) throws  ExceptionSameName{
        Product product = new Product(name, description,maker,number,price, productGroup);
        for (String key: productGroups.keySet()){
            if (productGroups.get(key).getProducts().containsKey(name)) throw new ExceptionSameName(product);
        }
        productGroups.get(productGroup.getName()).getProducts().put(product.getName(),product);
        save();
    }

    /**
     * Додає новий продукт
     * @param product продукт
     * @throws ExceptionSameName кидає помилку, якщо існує товар з такою назвою
     */
    public void addProduct(Product product) throws ExceptionSameName {
        for (String key: productGroups.keySet()){
            if (productGroups.get(key).getProducts().containsValue(product)) throw new ExceptionSameName(product);
        }
        productGroups.get(product.getProductGroup().getName()).getProducts().put(product.getName(),product);
        save();
    }

    /**
     * Видаляє групу продуктів
     * @param name назва групи продуктів
     */
    public void deleteProductGroup(String name){
        productGroups.remove(name);
        save();
    }

    /**
     * Видаляє продукт
     * @param productGroup група продукту
     * @param name назва продукту
     */
    public void deleteProduct(ProductGroup productGroup, String name){
        productGroup.getProducts().remove(name);
        save();
    }

    /**
     * Видаляє продукт
     * @param name назва продукту
     */
    public void deleteProduct(String name){
        for(String productGroupName: productGroups.keySet()){
            productGroups.get(productGroupName).getProducts().remove(name);
        }
        save();
    }

    /**
     * Список всіх продуктів
     * @return повертає список всіз продуктів
     */
    public String allProductsList(){
        String list = "";
        for (String key: productGroups.keySet()){
            list+="Группа продуктів: "+key+". Продукти:\n";
            for (String keyProducts : productGroups.get(key).getProducts().keySet()){
                list+= productGroups.get(key).getProducts().get(keyProducts).toString()+"\n";
            }
        }
        return list;
    }

    /**
     * Всі продукти магазину
     * @return повертає всі продукти магазину
     */
    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> products = new ArrayList<Product>();

        for(String productGroupName: productGroups.keySet()){
            for(String productName: productGroups.get(productGroupName).getProducts().keySet()){
                products.add(productGroups.get(productGroupName).getProducts().get(productName));
            }
        }
        return products;
    }

    /**
     * Список продуктів в групі
     * @param productGroup група продуктів
     * @return продукти в групі
     */
    public String listOfProductsInAGroup(ProductGroup productGroup){
        String list = "";
        list+=productGroup.getName()+": \n";
        for (String keyProducts : productGroup.getProducts().keySet()){
            list+=productGroup.getProducts().get(keyProducts).toString()+"\n";
        }
        return list;
    }
}
