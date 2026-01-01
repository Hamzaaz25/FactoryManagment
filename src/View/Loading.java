package View;

import javax.swing.*;
import java.awt.*;

public class Loading extends JFrame {

    public Loading() {

    setUndecorated(true);
    setSize(800, 500);
    setLocationRelativeTo(null);
    setBackground(Color.black);
    setLayout(new BorderLayout());
    JLabel background = new JLabel(new ImageIcon("./assets/AMH.png"));
        background.setForeground(Color.black);
        background.setLayout(new BorderLayout());
    add(background);

    JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setPreferredSize(new Dimension(getWidth(), 10));
        progressBar.setForeground(new Color(55, 100, 145));
        progressBar.setBorderPainted(false);
        progressBar.setBackground(new Color(0, 0, 0, 0));

        background.add(progressBar, BorderLayout.SOUTH);

    Timer timer = new Timer(6000, e -> {
        this.dispose();
        new ProductFrame("meow").setVisible(true);
    });
        timer.setRepeats(false);
        timer.start();
}
}

