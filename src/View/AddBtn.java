package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddBtn extends JButton {
    public AddBtn() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 250));
        setBackground(new Color(94, 142, 180));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel Pluslbl = new JLabel("+", SwingConstants.CENTER);
        Pluslbl.setFont(new Font("Segoe UI", Font.BOLD, 50));
        Pluslbl.setForeground(Color.black);
        add(Pluslbl, BorderLayout.CENTER);

        JLabel Textlbl = new JLabel("Add New ", SwingConstants.CENTER);
        add(Textlbl, BorderLayout.SOUTH);

//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                onAddAction.run();
//            }
//        });
    }
}
