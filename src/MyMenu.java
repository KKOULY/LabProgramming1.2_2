import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MyMenu extends JMenuBar {
    private JFrame frame;
    private JButton infoItem;
    private JPanel panelInfo = new JPanel();
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
               panelInfo = new JPanel();
               panelInfo.setLayout(new GridLayout(0,1,0,20));
               fillPanelInfo();
               frame.add(panelInfo);
               frame.revalidate();
            }
        });
        this.add(infoItem);

    }

    private void initLaboratory() {
        allProducts = laboratory.getAllProducts();
        try {
            laboratory.addProductGroup("Test", "Test");
            laboratory.addProduct(laboratory.getAllProducts().get("Test"), "Новий", "Опис", "Рошен", 12, 12.5);
            laboratory.addProduct(laboratory.getAllProducts().get("Test"), "Ух", "Опис", "Рошен", 12, 12.5);
            laboratory.addProductGroup("Test1", "Test");
            laboratory.addProduct(laboratory.getAllProducts().get("Test1"), "Ух1", "Опис", "Рошен", 16, 12.5);
        } catch (ExceptionSameName exceptionSameName) {
            exceptionSameName.printStackTrace();
        }
    }

    private void fillPanelInfo() {
        Font font = new Font("Verdana", Font.PLAIN, 11);

        StringTokenizer stringTokenizer = new StringTokenizer(laboratory.listOfProductsInAGroup(allProducts.get("Test")),"\n");
        while (stringTokenizer.hasMoreTokens()){
            JLabel label = new JLabel(stringTokenizer.nextToken());
            label.setFont(font);
            panelInfo.add(label);
        }
    }
}
