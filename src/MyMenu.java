import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MyMenu extends JMenuBar {
    private static JFrame frame;
    private JPanel panel;
    private JMenu menuInfo;
    private JMenu menuProductGroups;
    private JMenu menuProducts;
    private JMenu menuFind;
    private static HashMap<String, ProductGroup> allProducts = new HashMap<String, ProductGroup>();

    Font fontMenu = new Font("Verdana", Font.PLAIN, 16);
    public MyMenu(JFrame frame){
        initLaboratory();
        this.frame = frame;

        menuInfo = getInfoMenu();
        this.add(menuInfo);
        menuProductGroups = getProductsGroupMenu();
        this.add(menuProductGroups);
        menuProducts = getProductsMenu();
        this.add(menuProducts);
        menuFind = getFindMenu();
        this.add(menuFind);

    }

    private JMenu getFindMenu() {
        JMenu menu = new JMenu("Пошук");
        menu.setFont(fontMenu);
        JMenuItem findItem = new JMenuItem("Пошук");
        findItem.setFont(fontMenu);
        findItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panel!=null) {
                   frame.remove(panel);
                   frame.repaint();
                }
                panel = getFindPanel();
                frame.add(panel);
                frame.revalidate();
            }
        });
        menu.add(findItem);
        return menu;
    }

    JTable findTable;
    private JPanel getFindPanel() {
        JPanel tempPanel = new JPanel(new GridLayout(0,1,0,70));
        JTextField findField = new JTextField(20);
        tempPanel.add(findField,BorderLayout.WEST);
        findTable = getTableProducts(new ArrayList<>());
        tempPanel.add(findTable);
        findField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ArrayList<Product> products = shop.findProduct(findField.getText());
                System.out.println(findField.getText()+"--"+products);
                tempPanel.remove(findTable);
                findTable = getTableProducts(products);
                tempPanel.add(findTable);
                frame.revalidate();
                frame.repaint();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        return tempPanel;
    }

    private JTable getTableProducts(ArrayList<Product> products) {
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

    private JMenu getInfoMenu() {
        JMenu menu = new JMenu("Інформація");
        menu.setFont(fontMenu);

        Font fontItems = new Font("Verdana", Font.PLAIN, 11);
        JMenuItem infoAll = new JMenuItem("Вивести всі товари");
        infoAll.setFont(fontItems);
        infoAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panel!=null) {
                    frame.remove(panel);
                    frame.repaint();
                }
                JTable table = getTableAllProducts();
                panel = getScrollPanel(table);
                frame.add(panel);
                frame.revalidate();
            }
        });
        menu.add(infoAll);

        JMenuItem infoAllGroups = new JMenuItem("Вивести всі групи товарів");
        infoAllGroups.setFont(fontItems);
        infoAllGroups.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panel!=null) {
                    frame.remove(panel);
                    frame.repaint();
                }
                JTable table = getTableAllGroups();
                panel = getScrollPanel(table);
                frame.add(panel);
                frame.revalidate();
            }
        });
        menu.add(infoAllGroups);

        return menu;
    }

    private JPanel getScrollPanel(JTable table) {
        JPanel panelTemp = new JPanel();
        JScrollPane panelScroll = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelScroll.setPreferredSize(new Dimension((int) (frame.getWidth()*0.9), (frame.getHeight()-120)));
        panelTemp = new JPanel();
        panelTemp.add(panelScroll);
        return panelTemp;
    }

    private JTable getTableAllGroups() {
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

    private JMenu getProductsMenu() {
        JMenu menu = new JMenu("Продукти");
        menu.setFont(fontMenu);
        Font fontItems = new Font("Verdana", Font.PLAIN, 11);
        JMenuItem addProduct = new JMenuItem("Додати товар");
        addProduct.setFont(fontItems);
        menu.add(addProduct);
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateProductDialog dialog = new CreateProductDialog("Створення товару");
                dialog.setVisible(true);
                Product product = dialog.getProduct();
                if(product!=null) {
                    try {
                        shop.addProduct(product);
                    } catch (ExceptionSameName exceptionSameName) {
                        //fd
                    }
                }
            }
        });


        JMenuItem delProduct = new JMenuItem("Видалити товар");
        delProduct.setFont(fontItems);
        menu.add(delProduct);

        JMenuItem changeProduct = new JMenuItem("Редагувати товар");
        changeProduct.setFont(fontItems);
        menu.add(changeProduct);

        return menu;
    }

    private JMenu getProductsGroupMenu() {
        JMenu menu = new JMenu("Групи");
        menu.setFont(fontMenu);
        Font fontItems = new Font("Verdana", Font.PLAIN, 11);
        JMenuItem addGroup = new JMenuItem("Додати групу товарів");
        addGroup.setFont(fontItems);
        menu.add(addGroup);
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
                        //fd
                    }
                }
            }
        });

        JMenuItem delGroup = new JMenuItem("Видалити групу товарів");
        delGroup.setFont(fontItems);
        menu.add(delGroup);

        JMenuItem changeGroup = new JMenuItem("Редагувати групу товарів");
        changeGroup.setFont(fontItems);
        menu.add(changeGroup);

        return menu;
    }

    private JTable getTableAllProducts() {
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
    private static Shop shop = new Shop();

    private void initLaboratory() {
        shop.load();
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
               buttonOk.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       String name = nameField.getText();
                       String description = descriptionField.getText();
                       if(Laboratory.isWord(name)){
                           productGroup = new ProductGroup(name,description);
                           dialog.setVisible(false);
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

        public ProductGroup getProductGroup() {
            return productGroup;
        }
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
            buttonOk.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String description = descriptionField.getText();
                    String maker = makerField.getText();
                    double price = getPrice(priceField.getText());
                    ProductGroup productGroup = shop.getProductGroups().get(productGroupChoose.getSelectedItem());
                    if(Laboratory.isWord(name) && price != -1){
                        product = new Product(name,description,maker,0,price, productGroup);
                        dialog.setVisible(false);
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

        public Product getProduct() {
            return product;
        }
    }
}
