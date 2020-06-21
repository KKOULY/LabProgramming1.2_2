package MenuElements;

import Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Клас меню групи продуктів
 */
public class ProductsGroupMenu extends JMenu {
    private static JPanel panel;
    private static Font fontMenu;
    private static JFrame frame;
    private static Shop shop;

    /**
     * Конструктор меню групи продуктів
     * @param frame форма
     * @param shop магазин
     * @param fontMenu шрифт
     */
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
        delGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (shop.getAllProductGroups().size()>0) {
                    DeleteGroupDialog deleteGroupDialog = new DeleteGroupDialog("Видалити групу товарів");
                    deleteGroupDialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "В магазині немає груп товарів!", "Помилка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JMenuItem changeGroup = new JMenuItem("Редагувати групу товарів");
        changeGroup.setFont(fontItems);
        this.add(changeGroup);
        changeGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChangeGroupDialog changeGroupDialog = new ChangeGroupDialog("Редагувати групу товарів");
                changeGroupDialog.setVisible(true);
            }
        });
    }

    /**
     * Клас створення нової групи
     */
    static class CreateGroupDialog extends JDialog{
        private ProductGroup productGroup;
        private JPanel panel;
        private JTextField nameField;
        private JTextField descriptionField;
        private JButton buttonOk;
        private JButton buttonCancel;
        private Font font = new Font("Verdana", Font.PLAIN, 16);
        private JDialog dialog;

        /**
         * Ініціалізує форму створення групи
         * @param str назва
         */
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

        /**
         * Повертає панель для створення групи
         * @return панель для створення групи
         */
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
                    if(Tools.isWord(name) && !shop.isContainsProductGroupName(name)){
                        productGroup = new ProductGroup(name,description);
                        dialog.setVisible(false);
                    }

                    if(!Tools.isWord(name) || shop.isContainsProductGroupName(name)) nameField.setBackground(errorCol);
                    name=Tools.getRightString(name);
                    if (shop.isContainsProductGroupName(name))   JOptionPane.showMessageDialog(null, "Існує продукт з такою назвою: "+name, "Помилка!", JOptionPane.ERROR_MESSAGE);
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

        /**
         * Повертає групу продуктів
         * @return група продуктів
         */
        public ProductGroup getProductGroup() {
            return productGroup;
        }
    }

    /**
     * Класс форми видалення групи
     */
    static class DeleteGroupDialog extends JDialog{
        private JPanel panel;
        private JButton buttonOk;
        private JButton buttonCancel;
        private JComboBox<String> productGroupChoose;
        private Font font = new Font("Verdana", Font.PLAIN, 16);
        private JDialog dialog;

        /**
         * Ініціалізує форму видалення групи
         * @param str назва форми
         */
        public DeleteGroupDialog(String str){
            super(frame,str,true);
            dialog = this;
            this.setLayout(new FlowLayout());
            panel = getGroupPanel();
            this.add(panel);
            this.pack();
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation((int)dimension.getWidth()/2-this.getWidth()/2,(int)dimension.getHeight()/2-this.getHeight()/2);
        }

        /**
         * Створює панель для видалення групи
         * @return панель
         */
        private JPanel getGroupPanel() {
            JPanel tempPanel = new JPanel(new GridLayout(2,2,40,20));
            JLabel label = new JLabel("Група товарів");
            label.setFont(font);
            tempPanel.add(label);
            String[] productGroupArr = new String[shop.getAllProductGroups().size()];
            for (int i = 0; i<productGroupArr.length; i++){
                productGroupArr[i] = shop.getAllProductGroups().get(i).getName();
            }
            productGroupChoose = new JComboBox<>(productGroupArr);
            tempPanel.add(productGroupChoose);
            buttonOk = new JButton("ОК");
            tempPanel.add(buttonOk);
            buttonOk.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                shop.deleteProductGroup(productGroupChoose.getSelectedItem().toString());
                dialog.setVisible(false);
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
    }

    /**
     * Клас форми редагування групи
     */
    static class ChangeGroupDialog extends JDialog{
        private ProductGroup productGroup;
        private JPanel panel;
        private JButton buttonOk;
        private JButton buttonCancel;
        private JTextField nameField;
        private JTextField descriptionField;
        private JComboBox<String> productGroupChoose;
        private Font font = new Font("Verdana", Font.PLAIN, 16);
        private JDialog dialog;

        /**
         * Ініціалізує форму видалення групи
         * @param str назва форми
         */
        public ChangeGroupDialog(String str){
            super(frame,str,true);
            dialog = this;
            this.setLayout(new FlowLayout());
            panel = getGroupPanel();
            this.add(panel);
            this.pack();
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation((int)dimension.getWidth()/2-this.getWidth()/2,(int)dimension.getHeight()/2-this.getHeight()/2);
        }

        /**
         * Створює панель редагування групи
         * @return панель
         */
        private JPanel getGroupPanel() {
            JPanel tempPanel = new JPanel(new GridLayout(4,3,40,20));
            JLabel label = new JLabel("Група товарів");
            label.setFont(font);
            tempPanel.add(label);
            String[] productGroupArr = new String[shop.getAllProductGroups().size()];
            for (int i = 0; i<productGroupArr.length; i++){
                productGroupArr[i] = shop.getAllProductGroups().get(i).getName();
            }
            productGroupChoose = new JComboBox<>(productGroupArr);
            tempPanel.add(productGroupChoose);
            productGroupChoose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    nameField.setText(shop.getProductGroups().get(productGroupChoose.getSelectedItem()).getName());
                    descriptionField.setText(shop.getProductGroups().get(productGroupChoose.getSelectedItem()).getDescription());
                }
            });
            JLabel label1 = new JLabel("Назва групи товарів");
            label1.setFont(font);
            tempPanel.add(label1);
            nameField = new JTextField();
            tempPanel.add(nameField);
            nameField.setText(shop.getProductGroups().get(productGroupChoose.getSelectedItem()).getName());
            JLabel label2 = new JLabel("Опис групи товарів");
            label2.setFont(font);
            tempPanel.add(label2);
            descriptionField = new JTextField();
            tempPanel.add(descriptionField);
            descriptionField.setText(shop.getProductGroups().get(productGroupChoose.getSelectedItem()).getDescription());
            buttonOk = new JButton("ОК");
            tempPanel.add(buttonOk);

            Color errorCol = new Color(255, 48, 48);
            Color normalCol = descriptionField.getBackground();
            buttonOk.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    ProductGroup oldProductGroup = shop.getProductGroups().get(productGroupChoose.getSelectedItem());
                    name=Tools.getRightString(name);
                    boolean add = false;
                    if (oldProductGroup.getName().equals(name) || !shop.isContainsProductGroupName(name) && Tools.isWord(name)) {
                            try {
                                shop.deleteProductGroup(oldProductGroup.getName());
                                shop.addProductGroup(new ProductGroup(name, description));
                                add=true;
                            } catch (ExceptionSameName exceptionSameName) {
                                exceptionSameName.printStackTrace();
                            }
                            dialog.setVisible(false);
                        }
                        if (!add) {
                            if (!Tools.isWord(name) || shop.isContainsProductGroupName(name)) {
                                nameField.setBackground(errorCol);
                                if (shop.isContainsProductGroupName(name))
                                    JOptionPane.showMessageDialog(null, "Існує продукт з такою назвою: " + name, "Помилка!", JOptionPane.ERROR_MESSAGE);
                            }
                            else nameField.setBackground(normalCol);
                        }
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
    }
}
