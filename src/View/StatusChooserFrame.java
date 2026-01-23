package View;

import Enums.Status;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StatusChooserFrame extends JFrame {

    private Status selectedStatus = null;

    private JRadioButton activeBtn;
    private JRadioButton idleBtn;
    private JRadioButton maintenanceBtn;
    private JButton okButton;

    public StatusChooserFrame() {
        setVisible(false);
        setTitle("Select Status");
        setSize(450, 280);               // slightly bigger
        setResizable(false);             // make unresizable
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(38, 55, 85));

        // ===== TITLE =====
        JLabel title = new JLabel("Choose Status");
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        add(title, BorderLayout.NORTH);

        // ===== RADIO BUTTONS =====
        JPanel radioPanel = new JPanel();
        radioPanel.setBackground(new Color(38, 55, 85));
        radioPanel.setLayout(new GridLayout(3, 1, 15, 15));

        ButtonGroup group = new ButtonGroup();

        activeBtn = createRadioButton("Active");
        idleBtn = createRadioButton("Idle");
        maintenanceBtn = createRadioButton("Maintenance");

        group.add(activeBtn);
        group.add(idleBtn);
        group.add(maintenanceBtn);

        radioPanel.add(activeBtn);
        radioPanel.add(idleBtn);
        radioPanel.add(maintenanceBtn);

        radioPanel.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 60));

        add(radioPanel, BorderLayout.CENTER);

        // ===== OK BUTTON =====
        okButton = new JButton("OK");
        okButton.setBackground(new Color(20, 33, 61));
        okButton.setForeground(new Color(120, 165, 200));
        okButton.setFocusPainted(false);
        okButton.setFont(okButton.getFont().deriveFont(Font.BOLD, 16f));
        okButton.setPreferredSize(new Dimension(100, 40));


        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(38, 55, 85));
        btnPanel.add(okButton);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(15,0,20,0));
        add(btnPanel, BorderLayout.SOUTH);
    }

    private JRadioButton createRadioButton(String text) {
        JRadioButton rb = new JRadioButton(text);
        rb.setForeground(Color.WHITE);
        rb.setBackground(new Color(38, 55, 85));
        rb.setFont(rb.getFont().deriveFont(Font.PLAIN, 20f));
        return rb;
    }

    // ===== GETTER =====
    public Status getSelectedStatus() {
        return selectedStatus;
    }

    public JRadioButton getActiveBtn() {
        return activeBtn;
    }

    public JRadioButton getMaintenanceBtn() {
        return maintenanceBtn;
    }

    public JRadioButton getIdleBtn() {
        return idleBtn;
    }

    public JButton getOkButton() {
        return okButton;
    }




}
