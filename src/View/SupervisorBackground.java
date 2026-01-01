import javax.swing.*;
import java.awt.*;
public class SupervisorBackground extends JPanel{


        Image background;
        public SupervisorBackground(String imagePath) {
            background = new ImageIcon(imagePath).getImage();
            setLayout(null);
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }

}
