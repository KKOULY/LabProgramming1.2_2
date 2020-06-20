package MenuElements;

import Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ProductsMenu extends JMenu{

    private static Font fontMenu;
    private static JFrame frame;
    private static Shop shop;

    public ProductsMenu(JFrame frame, Shop shop, Font fontMenu) {
        super("Продукти");
        this.shop = shop;
        this.fontMenu = fontMenu;
        this.frame = frame;
        this.shop = shop;

        this.setFont(fontMenu);
        Font fontItems = new Font("Verdana", Font.PLAIN, 11);
        JMenuItem addProduct = new JMenuItem("Додати товар");
        addProduct.setFont(fontItems);
        this.add(addProduct);
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shop.getProductGroups().size() > 0) {
                    CreateProductDialog dialog = new CreateProductDialog("Створення товару");
                    dialog.setVisible(true);
                    Product product = dialog.getProduct();
                    if (product != null) {
                        try {
                            shop.addProduct(product);
                        } catch (ExceptionSameName exceptionSameName) {
                            JOptionPane.showMessageDialog(null, exceptionSameName, "Помилка!", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "В магазині немає груп!", "Помилка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        JMenuItem delProduct = new JMenuItem("Видалити товар");
        delProduct.setFont(fontItems);
        this.add(delProduct);
        delProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean noClearGroups=false;
                for (ProductGroup productGroup :shop.getAllProductGroups()){
                    if (productGroup.getProducts().size()>0){
                        noClearGroups=true;
                        break;
                    }
                }
                if (noClearGroups) {
                    DeleteProductDialog dialog = new DeleteProductDialog("Видалити товар");
                    dialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "В магазині немає товарів!", "Помилка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JMenuItem changeProduct = new JMenuItem("Редагувати товар");
        changeProduct.setFont(fontItems);
        this.add(changeProduct);
        changeProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean noClearGroups=false;
                for (ProductGroup productGroup :shop.getAllProductGroups()){
                    if (productGroup.getProducts().size()>0){
                        noClearGroups=true;
                        break;
                    }
                }
                if (noClearGroups) {
                    ChangeProductDialog dialog = new ChangeProductDialog("Редагувати товар");
                    dialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "В магазині немає товарів!", "Помилка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    static class CreateProductDialog extends JDialog{
        private Product product;
        private JPanel panel;
        private JComboBox<String> productGroupChoose;
        private JTextField nameField;
        private JTextField descriptionField;
        private JTextField makerField;
        private JTextField priceField;
        private JButton buttonOk;
        private JButton buttonCancel;
        private Font font = new Font("Verdana", Font.PLAIN, 16);
        private JDialog dialog;
        public CreateProductDialog(String str){
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
            JPanel tempPanel = new JPanel(new GridLayout(7,2,40,20));
            JLabel label0 = new JLabel("Виберіть групу товару");
            label0.setFont(font);
            tempPanel.add(label0);
            String[] productGroupArr = new String[shop.getAllProductGroups().size()];
            for(int i = 0; i< productGroupArr.length;i++){
                productGroupArr[i] = shop.getAllProductGroups().get(i).getName();
            }
            productGroupChoose = new JComboBox<>(productGroupArr);
            tempPanel.add(productGroupChoose);
            JLabel label = new JLabel("Назва товару");
            label.setFont(font);
            tempPanel.add(label);
            nameField = new JTextField();
            tempPanel.add(nameField);
            JLabel label2 = new JLabel("Опис групи товарів");
            label2.setFont(font);
            tempPanel.add(label2);
            descriptionField = new JTextField();
            tempPanel.add(descriptionField);
            JLabel label3 = new JLabel("Виробник");
            label3.setFont(font);
            tempPanel.add(label3);
            makerField = new JTextField();
            tempPanel.add(makerField);

            JLabel label5 = new JLabel("Ціна за одиницю товару");
            label5.setFont(font);
            tempPanel.add(label5);
            priceField = new JTextField();
            tempPanel.add(priceField);
            buttonOk = new JButton("ОК");
            tempPanel.add(buttonOk);

            Color errorCol = new Color(255, 48, 48);
            Color normalCol = descriptionField.getBackground();
            buttonOk.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    String maker = makerField.getText();
                    double price = getPrice(priceField.getText());
                    ProductGroup productGroup = shop.getProductGroups().get(productGroupChoose.getSelectedItem());
                    if(Tools.isWord(name) && price != -1 && !shop.isContainsProductName(name)){
                        product = new Product(name,description,maker,0,price, productGroup);
                        dialog.setVisible(false);
                    }
                    if(price == -1) priceField.setBackground(errorCol);
                    else priceField.setBackground(normalCol);
                    if(!Tools.isWord(name) || shop.isContainsProductName(name)) nameField.setBackground(errorCol);
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

        private double getPrice(String text) {
            double n;
            try {
                n = Double.valueOf(text);
            }catch (Exception e){
                return -1;
            }

            if(n <= 0) return -1;
            return n;
        }

        private int getNumber(String text) {
            int n;
            try {
                n = Integer.valueOf(text);
            }catch (Exception e){
                return -1;
            }

            if(n < 0) return -1;
            return n;
        }

        public Product getProduct() {
            return product;
        }
    }

    static class DeleteProductDialog extends JDialog {
        private Product product;
        private JPanel panel;
        private JComboBox<String> productGroupChoose;
        private JComboBox<String> productChoose;
        private JButton buttonOk;
        private JButton buttonCancel;
        private Font font = new Font("Verdana", Font.PLAIN, 16);
        private JDialog dialog;

        public DeleteProductDialog(String str) {
            super(frame, str, true);
            dialog = this;
            this.setLayout(new FlowLayout());
            panel = getGroupPanel();
            this.add(panel);
            this.pack();
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation((int) dimension.getWidth() / 2 - this.getWidth() / 2, (int) dimension.getHeight() / 2 - this.getHeight() / 2);
        }

        private JPanel getGroupPanel() {
            JPanel tempPanel = new JPanel(new GridLayout(3, 2, 40, 20));
            JLabel label0 = new JLabel("Виберіть групу товару");
            label0.setFont(font);
            tempPanel.add(label0);
            int notEmptyGroup = 0;
            for (ProductGroup productGroup : shop.getAllProductGroups()) {
                if (productGroup.getProducts().size() > 0) notEmptyGroup++;
            }
            String[] productGroupArr = new String[notEmptyGroup];
            int readyGroups = 0;
            for (int i = 0; i < shop.getAllProductGroups().size(); i++) {
                if (shop.getAllProductGroups().get(i).getProducts().size() > 0) {
                    productGroupArr[readyGroups] = shop.getAllProductGroups().get(i).getName();
                    readyGroups++;
                }
            }
            productGroupChoose = new JComboBox<>(productGroupArr);
            tempPanel.add(productGroupChoose);
            productGroupChoose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    HashMap<String, Product> productHashMap = shop.getProductGroups().get(productGroupChoose.getSelectedItem()).getProducts();
                    productChoose.removeAllItems();
                    for (String s : productHashMap.keySet()) {
                        productChoose.addItem(s);
                    }

                }
            });
            String[] productArr = new String[shop.getProductGroups().get(productGroupChoose.getSelectedItem()).getProducts().size()];
            for (int i = 0; i < productArr.length; i++) {
                productArr[i] = shop.getAllProducts().get(i).getName();
            }
            productChoose = new JComboBox<>(productArr);
            JLabel label = new JLabel("Виберіть товар");
            label.setFont(font);
            tempPanel.add(label);
            tempPanel.add(productChoose);

            buttonOk = new JButton("ОК");
            tempPanel.add(buttonOk);
            buttonOk.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    product = shop.getProductGroups().get(productGroupChoose.getSelectedItem()).getProducts().get(productChoose.getSelectedItem());
                    shop.deleteProduct(product.getName());
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

    static class ChangeProductDialog extends JDialog{
        private Product product;
        private JPanel panel;
        private JComboBox<String> productGroupChoose;
        private JComboBox<String> productChoose;
        private JButton buttonOk;
        private JButton buttonCancel;
        private JTextField nameField;
        private JTextField descriptionField;
        private JTextField makerField;
        private JTextField priceField;
        private Font font = new Font("Verdana", Font.PLAIN, 16);
        private JDialog dialog;
        public ChangeProductDialog(String str){
            super(frame, str, true);
            dialog = this;
            this.setLayout(new FlowLayout());
            panel = getGroupPanel();
            this.add(panel);
            this.pack();
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation((int) dimension.getWidth() / 2 - this.getWidth() / 2, (int) dimension.getHeight() / 2 - this.getHeight() / 2);
        }
        private JPanel getGroupPanel() {
            JPanel tempPanel = new JPanel(new GridLayout(7, 2, 40, 20));
            JLabel label0 = new JLabel("Виберіть групу товару");
            label0.setFont(font);
            tempPanel.add(label0);
            int notEmptyGroup=0;
            for (ProductGroup productGroup : shop.getAllProductGroups()){
                if (productGroup.getProducts().size()>0) notEmptyGroup++;
            }
            String[] productGroupArr = new String[notEmptyGroup];
            int readyGroups=0;
            for (int i = 0; i < shop.getAllProductGroups().size(); i++) {
                if (shop.getAllProductGroups().get(i).getProducts().size()>0) {
                    productGroupArr[readyGroups] = shop.getAllProductGroups().get(i).getName();
                    readyGroups++;
                }
            }
            productGroupChoose = new JComboBox<>(productGroupArr);
            tempPanel.add(productGroupChoose);
            JLabel labelChoose = new JLabel("Виберіть товар");
            labelChoose.setFont(font);
            tempPanel.add(labelChoose);
            productGroupChoose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    HashMap<String,Product> productHashMap = shop.getProductGroups().get(productGroupChoose.getSelectedItem()).getProducts();
                    productChoose.removeAllItems();
                    for(String s:productHashMap.keySet()){
                        productChoose.addItem(s);
                    }
                    product=shop.getProductGroups().get(productGroupChoose.getSelectedItem()).getProducts().get(productChoose.getSelectedItem());
                    nameField.setText(product.getName());
                    descriptionField.setText(product.getDescription());
                    makerField.setText(product.getMaker());
                    priceField.setText(String.valueOf(product.getPrice()));
                }
            });
            String[] productArr = new String[shop.getProductGroups().get(productGroupChoose.getSelectedItem()).getProducts().size()];
            for (int i = 0; i<productArr.length;i++){
                productArr[i] = shop.getAllProducts().get(i).getName();
            }
            productChoose = new JComboBox<>(productArr);
            tempPanel.add(productChoose);
            Color errorCol = new Color(255, 48, 48);
            Color normalCol = productChoose.getBackground();
            productChoose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    product=shop.getProductGroups().get(productGroupChoose.getSelectedItem()).getProducts().get(productChoose.getSelectedItem());
                    if(product != null) {
                        nameField.setText(product.getName());
                        nameField.setBackground(normalCol);
                        descriptionField.setText(product.getDescription());
                        descriptionField.setBackground(normalCol);
                        makerField.setText(product.getMaker());
                        makerField.setBackground(normalCol);
                        priceField.setText(String.valueOf(product.getPrice()));
                        priceField.setBackground(normalCol);
                    }
                }
            });
            product=shop.getProductGroups().get(productGroupChoose.getSelectedItem()).getProducts().get(productChoose.getSelectedItem());
            JLabel label = new JLabel("Назва товару");
            label.setFont(font);
            tempPanel.add(label);
            nameField = new JTextField();
            tempPanel.add(nameField);
            nameField.setText(product.getName());
            JLabel label2 = new JLabel("Опис групи товарів");
            label2.setFont(font);
            tempPanel.add(label2);
            descriptionField = new JTextField();
            tempPanel.add(descriptionField);
            descriptionField.setText(product.getDescription());
            JLabel label3 = new JLabel("Виробник");
            label3.setFont(font);
            tempPanel.add(label3);
            makerField = new JTextField();
            tempPanel.add(makerField);
            makerField.setText(product.getMaker());
            JLabel label5 = new JLabel("Ціна за одиницю товару");
            label5.setFont(font);
            tempPanel.add(label5);
            priceField = new JTextField();
            tempPanel.add(priceField);
            priceField.setText(String.valueOf(product.getPrice()));
            buttonOk = new JButton("ОК");
            tempPanel.add(buttonOk);
            buttonOk.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    String maker = makerField.getText();
                    double price = getPrice(priceField.getText());
                    ProductGroup productGroup = shop.getProductGroups().get(productGroupChoose.getSelectedItem());

                    Product oldProduct=productGroup.getProducts().get(productChoose.getSelectedItem());
                    product = new Product(name,description,maker,0,price, productGroup);
                    if(Tools.isWord(name) && price != -1){
                        if(!product.getName().toLowerCase().equals(oldProduct.getName().toLowerCase())&&shop.isContainsProductName(product.getName())){
                        }else {
                            product.setNumber(oldProduct.getNumber());
                            shop.deleteProduct(oldProduct.getName());
                            try {
                                shop.addProduct(product);
                            } catch (ExceptionSameName exceptionSameName) {

                            }
                            dialog.setVisible(false);
                        }

                    }
                    if(price == -1.0) priceField.setBackground(errorCol);
                    else priceField.setBackground(normalCol);
                    if(product != null) {
                        if (!name.toLowerCase().equals(oldProduct.getName().toLowerCase()) && shop.isContainsProductName(name)) {
                            nameField.setBackground(errorCol);
                            JOptionPane.showMessageDialog(null, new ExceptionSameName(product), "Помилка!", JOptionPane.ERROR_MESSAGE);
                        }else nameField.setBackground(normalCol);
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
        private double getPrice(String text) {
            double n;
            try {
                n = Double.valueOf(text);
            }catch (Exception e){
                return -1;
            }

            if(n <= 0) return -1;
            return n;
        }

        private int getNumber(String text) {
            int n;
            try {
                n = Integer.valueOf(text);
            }catch (Exception e){
                return -1;
            }

            if(n < 0) return -1;
            return n;
        }

    }

}
