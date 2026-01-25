package View;

import Model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class ProfileView extends JPanel {
    private final JButton logoutButton;
    JButton backButton;
    public ProfileView(User user) {

        setLayout(new BorderLayout());
        setBackground(new Color(26, 35, 50));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(100, 60, 0, 0));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = createLabel("Name : "+user.getUsername(), 65, true);
        JLabel jobLabel = createLabel("Role : "+user.getRole().toString(), 40, false);
        JLabel emailLabel = createLabel("Email : "+user.getEmail(), 25, false);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(50));
        infoPanel.add(jobLabel);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(emailLabel);

        infoPanel.add(Box.createVerticalStrut(200));

        backButton=new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backButton.setBackground(new Color(40, 50, 65));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        ImageIcon backIcon = resizeIcon(new ImageIcon("./assets/return.png"), 20, 20);
        backButton.setIcon(backIcon);
        backButton.setHorizontalTextPosition(SwingConstants.LEFT);
        backButton.setIconTextGap(10);



        logoutButton = new JButton("Log Out");
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        logoutButton.setForeground(new Color(255, 102, 102));
        logoutButton.setBackground(new Color(40, 50, 65));
        logoutButton.setFocusPainted(false);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        logoutButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        ImageIcon logoutIcon = resizeIcon(new ImageIcon("./assets/logout.png"), 20, 20);
        logoutButton.setIcon(logoutIcon);
        logoutButton.setHorizontalTextPosition(SwingConstants.LEFT);
        logoutButton.setIconTextGap(10);


        infoPanel.add(logoutButton);
        infoPanel.add(backButton);

        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        imagePanel.setOpaque(false);
        imagePanel.setBorder(new EmptyBorder(100, 0, 0, 60));

        JLabel profileImg = new JLabel(getCircularImage("./assets/user.png", 400));
        imagePanel.add(profileImg);

        add(infoPanel, BorderLayout.WEST);
        add(imagePanel, BorderLayout.EAST);
    }

    private JLabel createLabel(String text, int size, boolean isBold) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", isBold ? Font.BOLD : Font.PLAIN, size));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private ImageIcon getCircularImage(String path, int size) {

            BufferedImage master = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = master.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(new Ellipse2D.Double(0, 0, size, size));
            g2.setComposite(AlphaComposite.SrcIn);

            Image img = new ImageIcon(path).getImage();
            g2.drawImage(img, 0, 0, size, size, null);
            g2.dispose();
            return new ImageIcon(master);
    }
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
