package MenuElements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenu extends JMenu {
    JPanel panel;
    public MainMenu(JFrame frame,JPanel panelTemp, Font fontMenu){
        super("Головна");
        panel = panelTemp;
        this.setFont(fontMenu);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(panel !=null) {
                    frame.remove(panel);
                    frame.repaint();
                }
                panel = getMainPanel();
                frame.add(panel);
                frame.revalidate();
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
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Головна");
        label.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel.add(label);
        panel.setOpaque(false);
        return panel;
    }
}
