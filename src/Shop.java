import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    private HashMap<String, ProductGroup> productGroups = new HashMap<String, ProductGroup>();

    public HashMap<String, ProductGroup> getProductGroups() {
        return productGroups;
    }

    public void addProductGroup(String name, String description) throws ExceptionSameName{
        ProductGroup productGroup = new ProductGroup(name, description);
        if (productGroups.containsKey(productGroup.getName())) throw new ExceptionSameName(productGroup);
        productGroups.put(productGroup.getName(), productGroup);
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
