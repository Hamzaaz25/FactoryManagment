package View.Util;

import javax.swing.*;
import java.awt.*;

public class ImageSideBar extends JPanel {
    private final Image image;

    public ImageSideBar() {
        image = new ImageIcon(
                ("./assets/blue-townhouse-living.jpg")
        ).getImage();

        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
