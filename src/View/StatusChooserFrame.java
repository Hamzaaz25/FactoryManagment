package View;

import Enums.Status;

import javax.swing.*;
import java.awt.*;

import java.util.function.Consumer;

public class StatusChooserFrame extends JFrame {

    private JRadioButton activeBtn;
    private JRadioButton idleBtn;
    private JRadioButton maintenanceBtn;
    private JButton okButton;

    private Consumer<Status> onConfirm;

    public StatusChooserFrame() {
        setTitle("Select Status");
        setSize(450, 280);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(38, 55, 85));

        JLabel title = new JLabel("Choose Status");
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        add(title, BorderLayout.NORTH);

        JPanel radioPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        radioPanel.setBackground(new Color(38, 55, 85));

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

        okButton = new JButton("OK");
        okButton.setBackground(new Color(20, 33, 61));
        okButton.setForeground(new Color(120, 165, 200));
        okButton.setFocusPainted(false);
        okButton.setFont(okButton.getFont().deriveFont(Font.BOLD, 16f));
        okButton.setPreferredSize(new Dimension(100, 40));

        okButton.addActionListener(e -> {
            if (onConfirm != null) {
                onConfirm.accept(getSelectedStatus());
            }
            setVisible(false);
            dispose();
        });

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

    private Status getSelectedStatus() {
        if (activeBtn.isSelected()) return Status.Active;
        if (idleBtn.isSelected()) return Status.Idle;
        if (maintenanceBtn.isSelected()) return Status.Maintenance;
        return null;
    }

    public void setOnConfirm(Consumer<Status> onConfirm) {
        this.onConfirm = onConfirm;
    }

    public void setSelectedStatus(Status status) {
        if (status == Status.Active) activeBtn.setSelected(true);
        else if (status == Status.Idle) idleBtn.setSelected(true);
        else if (status == Status.Maintenance) maintenanceBtn.setSelected(true);
    }
}

