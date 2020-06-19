import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MyMenu extends JMenuBar {
    private JFrame frame;
    private JButton infoItem;
    private JScrollPane panelInfo = new JScrollPane();
    private Laboratory laboratory = new Laboratory();
    private static HashMap<String, ProductGroup> allProducts = new HashMap<String, ProductGroup>();
    public MyMenu(JFrame frame){
        initLaboratory();
        this.frame = frame;

        Font font = new Font("Verdana", Font.PLAIN, 16);

        JMenu menu = new JMenu("Інформація");
        menu.setFont(font);
        infoItem = new JButton("Інформація");
        infoItem.setFont(font);
        infoItem.setFocusPainted(false);
        infoItem.addActionListener(new ActionListener() {
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
        this.add(infoItem);

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
