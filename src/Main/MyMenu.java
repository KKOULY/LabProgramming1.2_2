package Main;

import MenuElements.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Головний меню бар
 */
public class MyMenu extends JMenuBar {
    private static JFrame frame;
    private JPanel panel = new JPanel();
    private JMenu menuInfo;
    private JMenu menuProductGroups;
    private JMenu menuProducts;
    private JMenu menuFind;
    private JMenu menuMain;
    private static HashMap<String, ProductGroup> allProducts = new HashMap<String, ProductGroup>();

    Font fontMenu = new Font("Verdana", Font.PLAIN, 16);

    /**
     * Конструктор меню бара
     * @param frame frame
     */
    public MyMenu(JFrame frame){
        loadShop();
        this.frame = frame;

        menuMain = new MainMenu(frame,fontMenu,this);
        this.add(menuMain);
        menuInfo = new InfoMenu(frame,shop,fontMenu,this);
        this.add(menuInfo);
        menuProductGroups = new ProductsGroupMenu(frame,shop,fontMenu);
        this.add(menuProductGroups);
        menuProducts = new ProductsMenu(frame,shop,fontMenu);
        this.add(menuProducts);
        menuFind = new FindMenu(frame,shop,fontMenu, this);
        this.add(menuFind);

    }

    private static Shop shop = new Shop();

    /**
     * загружає shop з файлу
     */
    private void loadShop() {
        shop.load();
    }

    /**
     * Змінює JPanel panel
     * @param panel panel
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /**
     * Повертає JPanel panel
     * @return  panel panel
     */
    public JPanel getPanel(){
        return panel;
    }

    /**
     * Повертає Shop shop
     * @return shop
     */
    public static Shop getShop() {
        return shop;
    }
}
