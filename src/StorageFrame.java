import javax.swing.*;
import java.awt.*;

public class StorageFrame extends JFrame {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 700;


    public StorageFrame(){
        super("Склад");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setIconImage(new ImageIcon("storageIcon.png").getImage());
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((int)dimension.getWidth()/2-WIDTH/2,(int)dimension.getHeight()/2-HEIGHT/2,WIDTH,HEIGHT);
        initAllElements();


    }

    private void initAllElements() {
        this.setJMenuBar(new MyMenu(this));

    }

    public static void main(String[] args) {
        StorageFrame frame = new StorageFrame();
        frame.setVisible(true);
    }
}
