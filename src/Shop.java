import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    private HashMap<String, ProductGroup> productGroups = new HashMap<String, ProductGroup>();

    public HashMap<String, ProductGroup> getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(HashMap<String, ProductGroup> productGroups) {
        this.productGroups = productGroups;
    }

    public void save() {
            try {
                ObjectOutputStream saver = new ObjectOutputStream(new FileOutputStream("products.dat"));
                saver.writeObject(productGroups);
                saver.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void load(){
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream("products.dat"));
            productGroups=(HashMap<String, ProductGroup>)reader.readObject();
            reader.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



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

    public void sellProduct(String name, int numberOfSell)throws IllegalArgumentException{
        for (Product product : getAllProducts()){
            if (product.getName().equals(name)){
                if (product.getNumber()<numberOfSell) throw new IllegalArgumentException("Ви намагаєтась продати більше товару, чим є на складі!");
                else product.setNumber(product.getNumber()-numberOfSell);
            }
        }
    }

    public void buyProduct(String name, int numberOfBuy){
        for (Product product : getAllProducts()){
            if (product.getName().equals(name)){
               product.setNumber(product.getNumber()+numberOfBuy);
            }
        }
    }

    public void addProductGroup(String name, String description) throws ExceptionSameName{
        ProductGroup productGroup = new ProductGroup(name, description);
        if (productGroups.containsKey(productGroup.getName())) throw new ExceptionSameName(productGroup);
        productGroups.put(productGroup.getName(), productGroup);
    }

    public void addProductGroup(ProductGroup productGroup) throws ExceptionSameName{
        if (productGroups.containsKey(productGroup.getName())) throw new ExceptionSameName(productGroup);
        productGroups.put(productGroup.getName(), productGroup);
    }


    public double priceOfAllProducts(){
        double price=0;
      for (Product p : getAllProducts()){
          price+=p.getPrice()*p.getNumber();
      }
      return price;
     }

    public double priceOfProductsInAGroup(String groupName){
        double price=0;
        ProductGroup productGroup = productGroups.get(groupName);
        for (String key : productGroup.getProducts().keySet()){
            price+= productGroup.getProducts().get(key).getPrice()*productGroup.getProducts().get(key).getNumber();
        }
        return price;
    }

    public void addProduct(ProductGroup productGroup, String name, String description, String maker, int number, double price) throws  ExceptionSameName{
        Product product = new Product(name, description,maker,number,price);
        for (String key: productGroups.keySet()){
            if (productGroups.get(key).getProducts().containsKey(name)) throw new ExceptionSameName(product);
        }
        productGroups.get(productGroup.getName()).getProducts().put(product.getName(),product);
    }

    public void deleteProductGroup(String name){
        productGroups.remove(name);
    }

    public void deleteProduct(ProductGroup productGroup, String name){
        productGroup.getProducts().remove(name);
    }

    public void deleteProduct(String name){
        for(String productGroupName: productGroups.keySet()){
            productGroups.get(productGroupName).getProducts().remove(name);
        }
    }

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

    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> products = new ArrayList<Product>();

        for(String productGroupName: productGroups.keySet()){
            for(String productName: productGroups.get(productGroupName).getProducts().keySet()){
                products.add(productGroups.get(productGroupName).getProducts().get(productName));
            }
        }
        return products;
    }
    public String listOfProductsInAGroup(ProductGroup productGroup){
        String list = "";
        list+=productGroup.getName()+": \n";
        for (String keyProducts : productGroup.getProducts().keySet()){
            list+=productGroup.getProducts().get(keyProducts).toString()+"\n";
        }
        return list;
    }
}
