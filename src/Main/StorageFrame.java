package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StorageFrame extends JFrame {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 700;


    public StorageFrame(){
        super("Склад");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setIconImage(new ImageIcon("storageIcon.png").getImage());
        this.setContentPane(new BgPanel());
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((int)dimension.getWidth()/2-WIDTH/2,(int)dimension.getHeight()/2-HEIGHT/2,WIDTH,HEIGHT);
        initAllElements();


    }

    private void initAllElements() {
        this.setJMenuBar(new MyMenu(this));
    }

    class BgPanel extends JPanel{
        public void paintComponent(Graphics g){
            Image im = null;
            try {
                im = ImageIO.read(new File("background.png"));
            } catch (IOException e) {}
            g.drawImage(im, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        StorageFrame frame = new StorageFrame();
        frame.setVisible(true);
    }
}
