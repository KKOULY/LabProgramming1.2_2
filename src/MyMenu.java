import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MyMenu extends JMenuBar {
    private JFrame frame;
    private JScrollPane panelInfo = new JScrollPane();
    private JPanel panel;
    private JMenu menuInfo;
    private JMenu menuProductGroups;
    private JMenu menuProducts;
    private static HashMap<String, ProductGroup> allProducts = new HashMap<String, ProductGroup>();

    Font fontMenu = new Font("Verdana", Font.PLAIN, 16);
    public MyMenu(JFrame frame){
        initLaboratory();
        this.frame = frame;

        menuInfo = getInfoMenu();
        this.add(menuInfo);
        menuProductGroups = getProductsGroupMenu();
        this.add(menuProductGroups);
        menuProducts = getProductsMenu();
        this.add(menuProducts);

    }

    private JMenu getInfoMenu() {
        JMenu menu = new JMenu("Інформація");
        menu.setFont(fontMenu);

        Font fontItems = new Font("Verdana", Font.PLAIN, 11);
        JMenuItem infoAll = new JMenuItem("Вивести всі товари");
        infoAll.setFont(fontItems);
        infoAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panelInfo != null) frame.remove(panelInfo);
                JTable table = getTableAllProducts();
                panelInfo = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                panelInfo.setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
                panelInfo.setMaximumSize(new Dimension((int) (frame.getWidth()*0.8),frame.getHeight()));
                frame.add(panelInfo);
                frame.revalidate();
            }
        });
        menu.add(infoAll);

        return menu;
    }

    private JMenu getProductsMenu() {
        JMenu menu = new JMenu("Продукти");
        menu.setFont(fontMenu);
        Font fontItems = new Font("Verdana", Font.PLAIN, 11);
        JMenuItem addGroup = new JMenuItem("Додати товар");
        addGroup.setFont(fontItems);
        menu.add(addGroup);

        JMenuItem delGroup = new JMenuItem("Видалити товар");
        delGroup.setFont(fontItems);
        menu.add(delGroup);

        JMenuItem changeGroup = new JMenuItem("Редагувати товар");
        changeGroup.setFont(fontItems);
        menu.add(changeGroup);

        return menu;
    }

    private JMenu getProductsGroupMenu() {
        JMenu menu = new JMenu("Групи");
        menu.setFont(fontMenu);
        Font fontItems = new Font("Verdana", Font.PLAIN, 11);
        JMenuItem addGroup = new JMenuItem("Додати групу товарів");
        addGroup.setFont(fontItems);
        menu.add(addGroup);

        JMenuItem delGroup = new JMenuItem("Видалити групу товарів");
        delGroup.setFont(fontItems);
        menu.add(delGroup);

        JMenuItem changeGroup = new JMenuItem("Редагувати групу товарів");
        changeGroup.setFont(fontItems);
        menu.add(changeGroup);

        return menu;
    }

    private JTable getTableAllProducts() {
        ArrayList<Product> products = shop.getAllProducts();
        String[][] prodTableString = new String[products.size()][5];
        for(int i = 0;i<prodTableString.length;i++){
            prodTableString[i][0] = products.get(i).getName();
            prodTableString[i][1] = products.get(i).getDescription();
            prodTableString[i][2] = products.get(i).getMaker();
            prodTableString[i][3] = products.get(i).getNumber() +" шт.";
            prodTableString[i][4] = products.get(i).getPrice() +" грн";
        }
        String[] columnsHeader = new String[]{"Назва","Опис","Виробник","Кількість","Ціна"};
        JTable table = new JTable(prodTableString,columnsHeader);
        table.setEnabled(false);
        Font font = new Font("Verdana", Font.PLAIN, 15);
        table.setFont(font);
        return table;
    }
    Shop shop = new Shop();
       private void initLaboratory() {
        try {
            shop.addProductGroup("Test", "Test");
            shop.addProduct(shop.getProductGroups().get("Test"), "Новий", "Опис", "Рошен", 12, 12.5);
            shop.addProduct(shop.getProductGroups().get("Test"), "Ух", "Опис", "Рошен", 12, 12.5);
            shop.addProductGroup("Test1", "Test");
            shop.addProduct(shop.getProductGroups().get("Test1"), "Ух1", "Опис", "Рошен", 16, 12.5);
            shop.addProduct(shop.getProductGroups().get("Test1"), "Ух2", "Опис", "Рошен", 16, 12.5);
            shop.addProduct(shop.getProductGroups().get("Test1"), "Ух3", "Опис", "Рошен", 16, 12.5);
            shop.addProduct(shop.getProductGroups().get("Test1"), "Ух4", "Опис", "Рошен", 16, 12.5);
            shop.addProduct(shop.getProductGroups().get("Test1"), "Ух5", "Опис", "Рошен", 16, 12.5);
            shop.addProduct(shop.getProductGroups().get("Test1"), "Ух6", "Опис", "Рошен", 16, 12.5);
            shop.addProduct(shop.getProductGroups().get("Test1"), "Ух7", "Опис", "Рошен", 16, 12.5);
        } catch (ExceptionSameName exceptionSameName) {
            exceptionSameName.printStackTrace();
        }
    }

    private void fillPanelInfo() {
        Font font = new Font("Verdana", Font.PLAIN, 11);

        StringTokenizer stringTokenizer = new StringTokenizer(shop.listOfProductsInAGroup(allProducts.get("Test")),"\n");
        while (stringTokenizer.hasMoreTokens()){
            JLabel label = new JLabel(stringTokenizer.nextToken());
            label.setFont(font);
            panelInfo.add(label);
        }
    }
}
