package MenuElements;

import Main.MyMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenu extends JMenu {
    JPanel panel;
    public MainMenu(JFrame frame, Font fontMenu, MyMenu myMenu){
        super("Головна");
        panel = myMenu.getPanel();
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
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Головна");
        label.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel.add(label);
        panel.setOpaque(false);
        return panel;
    }
}
