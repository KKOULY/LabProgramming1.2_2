package MenuElements;

import Main.*;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Клас меню для пошуку товарів
 */
public class FindMenu extends JMenu {
    private JPanel panel;
    private Font fontMenu;
    private JFrame frame;
    private Shop shop;

    /**
     * Констурктор пошукового меню
     * @param frame форма
     * @param shop магазин
     * @param font шрифт
     * @param myMenu куди додати це меню
     */
    public FindMenu(JFrame frame, Shop shop, Font font, MyMenu myMenu){
        super("Пошук");
        panel = myMenu.getPanel();
        fontMenu = font;
        this.frame = frame;
        this.shop = shop;
        this.setFont(fontMenu);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(myMenu.getPanel()!=null) {
                    frame.remove(myMenu.getPanel());
                    frame.repaint();
                }
                panel = getFindPanel();
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

    JTable findTable;
    JPanel scrollPane;

    /**
     * Створює пошукову панель
     * @return пошукова панель
     */
    private JPanel getFindPanel() {
        JPanel tempPanel = new JPanel(new BorderLayout());
        tempPanel.setOpaque(false);

        JPanel text = new JPanel(new GridLayout(1,2));
        JLabel label = new JLabel("Введіть назву");
        label.setFont(fontMenu);
        label.setHorizontalAlignment(JLabel.CENTER);
        text.add(label);
        JTextField findField = new JTextField(10);
        text.add(findField);
        findField.setOpaque(false);

        tempPanel.add(text,BorderLayout.NORTH);

        findTable = Tools.getTableProducts(new ArrayList<>());
        scrollPane = Tools.getScrollPanel(findTable,frame);
        scrollPane.setOpaque(false);
        tempPanel.add(scrollPane);
        findField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                ArrayList<Product> products = shop.findProduct(findField.getText());
                tempPanel.remove(scrollPane);
                findTable = Tools.getTableProducts(products);
                scrollPane = Tools.getScrollPanel(findTable,frame);
                scrollPane.setOpaque(false);
                tempPanel.add(scrollPane);
                frame.revalidate();
                frame.repaint();
            }
        });
        return tempPanel;
    }

}
