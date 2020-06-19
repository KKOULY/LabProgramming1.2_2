import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Laboratory extends JFrame {






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
        Shop shop = new Shop();
        try {
            shop.addProductGroup("Test", "Test");
            shop.addProduct( shop.getProductGroups().get("Test"), "Новий", "Опис", "Рошен", 12, 12.5);
            shop.addProduct( shop.getProductGroups().get("Test"), "Новийфіс", "Опис", "Рошен", 12, 12.5);
            shop.addProduct( shop.getProductGroups().get("Test"), "Ух", "Опис", "Рошен", 12, 12.5);
            shop.addProductGroup("Test1", "Test");
            shop.addProduct(shop.getProductGroups().get("Test1"), "Qeqwe", "Опис", "Рошен", 1, 12.5);
        } catch (ExceptionSameName exceptionSameName) {
            exceptionSameName.printStackTrace();
        }
        shop.buyProduct("Qeqwe",100);
        shop.save();
        shop.load();
        System.out.println(shop.allProductsList());
    }
}
