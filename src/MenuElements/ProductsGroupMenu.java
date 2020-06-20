package MenuElements;

import Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductsGroupMenu extends JMenu {
    private static JPanel panel;
    private static Font fontMenu;
    private static JFrame frame;
    private static Shop shop;

    public ProductsGroupMenu(JFrame frame, Shop shop, Font fontMenu) {
        super("Групи");
        this.shop = shop;
        this.fontMenu = fontMenu;
        this.frame = frame;
        this.shop = shop;

        this.setFont(fontMenu);
        this.setFont(fontMenu);
        Font fontItems = new Font("Verdana", Font.PLAIN, 11);
        JMenuItem addGroup = new JMenuItem("Додати групу товарів");
        addGroup.setFont(fontItems);
        this.add(addGroup);
        addGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateGroupDialog dialog = new CreateGroupDialog("Створення групи товарів");
                dialog.setVisible(true);
                ProductGroup productGroup = dialog.getProductGroup();
                if(productGroup!=null) {
                    try {
                        shop.addProductGroup(productGroup);
                    } catch (ExceptionSameName exceptionSameName) {
                        JOptionPane.showMessageDialog(null, exceptionSameName, "Помилка!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JMenuItem delGroup = new JMenuItem("Видалити групу товарів");
        delGroup.setFont(fontItems);
        this.add(delGroup);

        JMenuItem changeGroup = new JMenuItem("Редагувати групу товарів");
        changeGroup.setFont(fontItems);
        this.add(changeGroup);
    }

    static class CreateGroupDialog extends JDialog{
        private ProductGroup productGroup;
        private JPanel panel;
        private JTextField nameField;
        private JTextField descriptionField;
        private JButton buttonOk;
        private JButton buttonCancel;
        private Font font = new Font("Verdana", Font.PLAIN, 16);
        private JDialog dialog;
        public CreateGroupDialog(String str){
            super(frame,str,true);
            dialog = this;
            this.setLayout(new FlowLayout());
            panel = getGroupPanel();
            this.add(panel);
            this.pack();
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation((int)dimension.getWidth()/2-this.getWidth()/2,(int)dimension.getHeight()/2-this.getHeight()/2);
        }

        private JPanel getGroupPanel() {
            JPanel tempPanel = new JPanel(new GridLayout(3,2,40,20));
            JLabel label = new JLabel("Назва групи товарів");
            label.setFont(font);
            tempPanel.add(label);
            nameField = new JTextField();
            tempPanel.add(nameField);
            JLabel label2 = new JLabel("Опис групи товарів");
            label2.setFont(font);
            tempPanel.add(label2);
            descriptionField = new JTextField();
            tempPanel.add(descriptionField);
            buttonOk = new JButton("ОК");
            tempPanel.add(buttonOk);

            Color errorCol = new Color(255, 48, 48);
            Color normalCol = descriptionField.getBackground();
            buttonOk.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    if(Laboratory.isWord(name) && !shop.isContainsProductGroupName(name)){
                        productGroup = new ProductGroup(name,description);
                        dialog.setVisible(false);
                    }

                    if(!Laboratory.isWord(name) || shop.isContainsProductGroupName(name)) nameField.setBackground(errorCol);
                    else nameField.setBackground(normalCol);
                }
            });
            buttonCancel = new JButton("Відмінити");
            tempPanel.add(buttonCancel);
            buttonCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.setVisible(false);
                }
            });
            return tempPanel;
        }

        public ProductGroup getProductGroup() {
            return productGroup;
        }
    }
}
