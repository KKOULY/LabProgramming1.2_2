package MenuElements;

import Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InfoMenu extends JMenu {

    private JPanel panel;
    private Font fontMenu;
    private JFrame frame;
    private Shop shop;
    public InfoMenu(JFrame frame, Shop shop, Font fontMenu, MyMenu myMenu) {
        super("Інформація");
        this.shop = shop;
        this.fontMenu = fontMenu;
        this.frame = frame;
        this.shop = shop;
        this.panel = myMenu.getPanel();
        this.setFont(fontMenu);
        Font fontItems = new Font("Verdana", Font.PLAIN, 11);
        JMenuItem infoAll = new JMenuItem("Вивести товари");
        infoAll.setFont(fontItems);
        infoAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myMenu.getPanel()!=null) {
                    frame.remove(myMenu.getPanel());
                    frame.repaint();
                }
                panel = getProductsListTable();
                frame.add(panel);
                frame.revalidate();
                myMenu.setPanel(panel);
            }
        });
        this.add(infoAll);

        JMenuItem infoAllGroups = new JMenuItem("Вивести всі групи товарів");
        infoAllGroups.setFont(fontItems);
        infoAllGroups.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panel!=null) {
                    frame.remove(panel);
                    frame.repaint();
                }
                JTable table = Tools.getTableAllGroups(shop);
                panel = Tools.getScrollPanel(table,frame);
                frame.add(panel);
                frame.revalidate();
                myMenu.setPanel(panel);
            }
        });
        this.add(infoAllGroups);

        JMenuItem infoBank = new JMenuItem("Інформація по банку");
        infoBank.setFont(fontItems);
        infoBank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panel != null){
                    frame.remove(panel);
                    frame.repaint();
                }
                panel = Tools.getBankPanel(fontMenu, shop);
                frame.add(panel);
                frame.revalidate();
                myMenu.setPanel(panel);
            }
        });
        this.add(infoBank);
    }
    JTable productsTable;
    JPanel scrollPane;
    private JPanel getProductsListTable(){
        JPanel tempPanel = new JPanel(new BorderLayout());
        tempPanel.setOpaque(false);
        JPanel text = new JPanel(new GridLayout(1,1));
        JComboBox groupsComboBox = new JComboBox();
        text.add(groupsComboBox);
        groupsComboBox.setOpaque(false);
        groupsComboBox.addItem("Всі товари");
        for(String key:shop.getProductGroups().keySet()){
            groupsComboBox.addItem(key);
        }
        tempPanel.add(text,BorderLayout.NORTH);
        productsTable = Tools.getTableAllProducts(shop);
        scrollPane = Tools.getScrollPanel(productsTable,frame);
        scrollPane.setOpaque(false);
        tempPanel.add(scrollPane);
        groupsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tempPanel.remove(scrollPane);
                if (groupsComboBox.getSelectedItem().equals("Всі товари")){
                    productsTable=Tools.getTableAllGroups(shop);
                }else {
                    productsTable = Tools.getTableProductsOfGroup(shop.getProductGroups().get(groupsComboBox.getSelectedItem()));
                }
                scrollPane = Tools.getScrollPanel(productsTable,frame);
                scrollPane.setOpaque(false);
                tempPanel.add(scrollPane);
                frame.revalidate();
                frame.repaint();
            }
        });
        return tempPanel;
    }
}
