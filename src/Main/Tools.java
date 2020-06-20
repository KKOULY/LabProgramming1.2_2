package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Tools {

    public static JTable getTableProducts(ArrayList<Product> products) {
        String[][] prodTableString = new String[products.size()][6];
        for(int i = 0;i<prodTableString.length;i++){
            prodTableString[i][0] = products.get(i).getName();
            prodTableString[i][1] = products.get(i).getDescription();
            prodTableString[i][2] = products.get(i).getMaker();
            prodTableString[i][3] = products.get(i).getNumber() +" шт.";
            prodTableString[i][4] = products.get(i).getPrice() +" грн";
            prodTableString[i][5] = products.get(i).getProductGroup().getName();
        }
        String[] columnsHeader = new String[]{"Назва товару","Опис","Виробник","Кількість","Ціна","Група товарів"};
        JTable table = new JTable(prodTableString,columnsHeader);
        table.setEnabled(false);
        Font font = new Font("Verdana", Font.PLAIN, 15);
        table.setFont(font);
        table.setPreferredSize(new Dimension(700,500));
        table.setOpaque(false);
        return table;
    }

    public static JTable getTableAllGroups(Shop shop) {
        ArrayList<ProductGroup> productGroups = shop.getAllProductGroups();
        String[][] prodTableString = new String[productGroups.size()][2];
        for(int i = 0;i<prodTableString.length;i++){
            prodTableString[i][0] = productGroups.get(i).getName();
            prodTableString[i][1] = productGroups.get(i).getDescription();
        }
        String[] columnsHeader = new String[]{"Назва групи","Опис"};
        JTable table = new JTable(prodTableString,columnsHeader);
        table.setEnabled(false);
        Font font = new Font("Verdana", Font.PLAIN, 15);
        table.setFont(font);
        return table;
    }

    public static JTable getTableAllProducts(Shop shop) {
        ArrayList<Product> products = shop.getAllProducts();
        String[][] prodTableString = new String[products.size()][6];
        for(int i = 0;i<prodTableString.length;i++){
            prodTableString[i][0] = products.get(i).getName();
            prodTableString[i][1] = products.get(i).getDescription();
            prodTableString[i][2] = products.get(i).getMaker();
            prodTableString[i][3] = products.get(i).getNumber() +" шт.";
            prodTableString[i][4] = products.get(i).getPrice() +" грн";
            prodTableString[i][5] = products.get(i).getProductGroup().getName();
        }
        String[] columnsHeader = new String[]{"Назва товару","Опис","Виробник","Кількість","Ціна","Група товарів"};
        JTable table = new JTable(prodTableString,columnsHeader);
        table.setEnabled(false);
        Font font = new Font("Verdana", Font.PLAIN, 15);
        table.setFont(font);
        return table;
    }

    public static JPanel getScrollPanel(JTable table, JFrame frame) {
        JPanel panelTemp;
        JScrollPane panelScroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelScroll.setPreferredSize(new Dimension((int) (frame.getWidth()*0.9), (frame.getHeight()-85)));
        panelTemp = new JPanel();
        panelTemp.add(panelScroll);
        return panelTemp;
    }

    public static JPanel getBankPanel(Font fontMenu, Shop shop) {
        JPanel panel = new JPanel(new GridLayout(3,1,0,40));
        panel.setOpaque(false);
        JLabel label = new JLabel("Виберіть групу товарів");
        panel.add(label);
        label.setFont(fontMenu);
        JComboBox<String> groupsComboBox = new JComboBox<>();
        panel.add(groupsComboBox);
        groupsComboBox.setFont(fontMenu);
        groupsComboBox.addItem("Всі товари");
        JLabel bank = new JLabel("Всього: "+shop.getAllBank()+" грн");
        bank.setFont(fontMenu);
        for(String key:shop.getProductGroups().keySet()){
            groupsComboBox.addItem(key);
        }
        groupsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) groupsComboBox.getSelectedItem();
                if(s.equals("Всі товари")) bank.setText("Всього: "+shop.getAllBank()+" грн");
                else bank.setText("Всього: "+shop.getBankProductGroup(s)+" грн");
            }
        });
        panel.add(bank);
        return panel;
    }
}
