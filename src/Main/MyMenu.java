package Main;

import MenuElements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MyMenu extends JMenuBar {
    private static JFrame frame;
    private JPanel panel;
    private JMenu menuInfo;
    private JMenu menuProductGroups;
    private JMenu menuProducts;
    private JMenu menuFind;
    private JMenu menuMain;
    private static HashMap<String, ProductGroup> allProducts = new HashMap<String, ProductGroup>();

    Font fontMenu = new Font("Verdana", Font.PLAIN, 16);
    public MyMenu(JFrame frame){
        initLaboratory();
        this.frame = frame;

        menuMain = new MainMenu(frame,panel,fontMenu);
        this.add(menuMain);
        menuInfo = new InfoMenu(frame,panel,shop,fontMenu);
        this.add(menuInfo);
        menuProductGroups = new ProductsGroupMenu(frame,panel,shop,fontMenu);
        this.add(menuProductGroups);
        menuProducts = new ProductsMenu(frame,panel,shop,fontMenu);
        this.add(menuProducts);
        menuFind = new FindMenu(frame,panel,shop,fontMenu);
        this.add(menuFind);

    }

    private static Shop shop = new Shop();

    private void initLaboratory() {
        shop.load();
    }

}
