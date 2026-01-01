package View;
import javax.swing.*;
import java.awt.*;


public class LogInBackground extends JPanel {

    Image background;
    public LogInBackground(String imagePath) {
        background = new ImageIcon(imagePath).getImage();

    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

}
