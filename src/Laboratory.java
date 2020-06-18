import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Laboratory extends JFrame {
    private static HashMap<String, ProductGroup> productGroups = new HashMap<String, ProductGroup>();



    public static void addProductGroup(String name, String description) throws ExceptionSameName{
    ProductGroup productGroup = new ProductGroup(name, description);
    if (productGroups.containsKey(productGroup.getName())) throw new ExceptionSameName(productGroup);
    productGroups.put(productGroup.getName(), productGroup);
    }


    public static void addProduct(ProductGroup productGroup, String name, String description, String maker, int number, double price) throws  ExceptionSameName{
        Product product = new Product(name, description,maker,number,price);
        for (String key: productGroups.keySet()){
            if (productGroups.get(key).getProducts().containsKey(name)) throw new ExceptionSameName(product);
        }
        productGroups.get(productGroup.getName()).getProducts().put(product.getName(),product);
    }

    public static void deleteProductGroup(String name){
        productGroups.remove(name);
    }

    public static void deleteProduct(ProductGroup productGroup, String name){
        productGroup.getProducts().remove(name);
    }

    public static void deleteProduct(String name){
        for(String productGroupName: productGroups.keySet()){
            productGroups.get(productGroupName).getProducts().remove(name);
        }
    }

    public static String allProductsList(){
        String list = "";
        for (String key: productGroups.keySet()){
            list+="Группа продуктів: "+key+". Продукти:\n";
            for (String keyProducts : productGroups.get(key).getProducts().keySet()){
                list+= productGroups.get(key).getProducts().get(keyProducts).toString()+"\n";
            }
        }
        return list;
    }

    public static ArrayList<Product> getAllProducts(){
        ArrayList<Product> products = new ArrayList<Product>();

        for(String productGroupName: productGroups.keySet()){
            for(String productName: productGroups.get(productGroupName).getProducts().keySet()){
                products.add(productGroups.get(productGroupName).getProducts().get(productName));
            }
        }
        return products;
    }
    public static String listOfProductsInAGroup(ProductGroup productGroup){
        String list = "";
        list+=productGroup.getName()+": \n";
        for (String keyProducts : productGroup.getProducts().keySet()){
            list+=productGroup.getProducts().get(keyProducts).toString()+"\n";
        }
        return list;
    }

    public static boolean isWordList(String wordList){
        if (wordList.charAt(0)==' ') return false;
        if (wordList.charAt(wordList.length()-1)==' ') return false;
        boolean space=false;
        for(int i =0; i<wordList.length(); i++) {
            if (space==true && wordList.charAt(i)==' ') return false;
            else space=false;
            if (!Character.isLetter(wordList.charAt(i)) && wordList.charAt(i)!=' ') {
                return false;
            }
            if (wordList.charAt(i)==' ')  space=true;
        }
        if (wordList.charAt(wordList.length()-1)==' ') return false;
        return true;
    }


    public static boolean isWord(String word){
        for(int i =0; i<word.length(); i++) {
            if (!Character.isLetter(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            addProductGroup("Test", "Test");
            addProduct(productGroups.get("Test"), "Новий", "Опис", "Рошен", 12, 12.5);
            addProduct(productGroups.get("Test"), "Ух", "Опис", "Рошен", 12, 12.5);
            addProductGroup("Test1", "Test");
            addProduct(productGroups.get("Test1"), "Ух", "Опис", "Рошен", 16, 12.5);
        } catch (ExceptionSameName exceptionSameName) {
            exceptionSameName.printStackTrace();
        }
        System.out.println(listOfProductsInAGroup(productGroups.get("Test")));
    }

    public static HashMap<String, ProductGroup> getProductGroups() {
        return productGroups;
    }
}
