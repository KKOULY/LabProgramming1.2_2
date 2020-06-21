package MenuElements;

import Main.MyMenu;
import Main.ProductGroup;
import Main.Tools;
import Main.TransactionInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MainMenu extends JMenu {
    JPanel panel;
    MyMenu myMenu;
    JFrame frame;
    public MainMenu(JFrame frame, Font fontMenu, MyMenu myMenu){
        super("Головна");
        panel = myMenu.getPanel();
        this.frame = frame;
        this.setFont(fontMenu);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(myMenu.getPanel() !=null) {
                    frame.remove(myMenu.getPanel());
                    frame.repaint();
                }
                panel = getMainPanel();
                frame.add(panel);
                frame.revalidate();
                myMenu.setPanel(panel);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private JPanel getMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Історія продаж");
        label.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel.add(label,BorderLayout.NORTH);
        JTable table = getTableTransactionHistory();
        JPanel scrollTable = Tools.getScrollPanel(table,frame);
        panel.add(scrollTable,BorderLayout.CENTER);
        panel.setOpaque(false);
        return panel;
    }

    private JTable getTableTransactionHistory() {
        ArrayList<TransactionInfo> transactionHistory = myMenu.getShop().getTransactionHistory();
        String[][] prodTableString = new String[transactionHistory.size()][4];
        for(int i = 0;i<prodTableString.length;i++){
            prodTableString[i][0] = transactionHistory.get(i).getDate();
            prodTableString[i][1] = transactionHistory.get(i).getNameProduct();
            prodTableString[i][2] = transactionHistory.get(i).getNum();
            prodTableString[i][3] = transactionHistory.get(i).getSum();
        }
        String[] columnsHeader = new String[]{"Дата","Назва продукту","Кількість","Прибуток"};
        JTable table = new JTable(prodTableString,columnsHeader);
        table.setEnabled(false);
        Font font = new Font("Verdana", Font.PLAIN, 15);
        table.setFont(font);
        return table;
    }
}
