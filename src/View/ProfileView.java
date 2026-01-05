package View;

import Model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProfileView extends JPanel {

    private final User user;

    public ProfileView(User user) {
        this.user = user;

        setBackground(new Color(69, 101, 149));
        setLayout(new BorderLayout());

        add(createProfileCard(), BorderLayout.CENTER);

    }

    private JPanel createProfileCard() {
        JPanel card = new JPanel();
        card.setOpaque(false);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Avatar
        ImageIcon icon = new ImageIcon("./assets/user.png");
        Image img = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        JLabel avatar = new JLabel(new ImageIcon(img));
        avatar.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username
        JLabel username = new JLabel(user.getUsername());
        username.setFont(new Font("SansSerif", Font.BOLD, 40));
        username.setForeground(Color.WHITE);
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Email
        JLabel email = new JLabel(user.getEmail());
        email.setFont(new Font("SansSerif", Font.PLAIN, 14));
        email.setForeground(Color.LIGHT_GRAY);
        email.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components
        card.add(avatar);
        card.add(Box.createVerticalStrut(15));
        card.add(username);
        card.add(Box.createVerticalStrut(5));
        card.add(email);

        return card;
    }
}
