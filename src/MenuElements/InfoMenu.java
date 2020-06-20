package MenuElements;

import Main.*;
import Main.Tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JMenuItem infoAll = new JMenuItem("Вивести всі товари");
        infoAll.setFont(fontItems);
        infoAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myMenu.getPanel()!=null) {
                    frame.remove(myMenu.getPanel());
                    frame.repaint();
                }
                JTable table = Tools.getTableAllProducts(shop);
                panel = Tools.getScrollPanel(table,frame);
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
}
