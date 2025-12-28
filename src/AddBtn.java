import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddBtn extends JButton {
    public AddBtn(Runnable onAddAction) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 250));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblPlus = new JLabel("+", SwingConstants.CENTER);
        lblPlus.setFont(new Font("Segoe UI", Font.BOLD, 50));
        lblPlus.setForeground(Color.black);
        add(lblPlus, BorderLayout.CENTER);

        JLabel lblText = new JLabel("Add New Product", SwingConstants.CENTER);
        add(lblText, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onAddAction.run();
            }
        });
    }
}
