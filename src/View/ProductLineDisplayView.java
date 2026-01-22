package View;

import Model.Task;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ProductLineDisplayView extends JPanel {

    JLabel title;
    String font = "Segoe UI";
    JButton saveButton;
    ArrayList<JPanel> taskPanels = new ArrayList<>();

    private static final Color BG_COLOR = new Color(0, 35, 71);

    public ProductLineDisplayView(ArrayList<Task> tasks) {

        setLayout(new BorderLayout());
        setBackground(BG_COLOR);
        setOpaque(true);

        // ===== TITLE =====
        title = new JLabel("First Product Line", SwingConstants.CENTER);
        title.setFont(new Font(font, Font.BOLD, 28));
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        title.setOpaque(true);
        title.setBackground(BG_COLOR);
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // ===== CENTER =====
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(new EmptyBorder(20, 150, 20, 150));
        centerPanel.setBackground(BG_COLOR);
        centerPanel.setOpaque(true);
        add(centerPanel, BorderLayout.CENTER);

        taskPanels = createTasks(tasks);
        for (JPanel panel : taskPanels) {
            centerPanel.add(panel);
        }

        centerPanel.add(Box.createVerticalStrut(50));

        // ===== SAVE BUTTON =====
        saveButton = new JButton("Save Changes");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setFont(new Font(font, Font.BOLD, 16));
        centerPanel.add(saveButton);
    }

    // ===== TASK PANEL (TRANSPARENT) =====
    private JPanel createTaskPanel(String id, String taskName,
                                   String currentStatus, int progress) {

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false); // 🔥 transparent
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(8, 8, 8, 8),
                BorderFactory.createLineBorder(
                        new Color(255, 255, 255, 120), 1, true
                )
        ));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

        // ----- NORTH -----
        JLabel titleLabel = new JLabel("ID: " + id + "   -   " + taskName);
        titleLabel.setFont(new Font(font, Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, BorderLayout.NORTH);

        // ----- CENTER -----
        JPanel center = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        center.setOpaque(false);

        JLabel statusText = new JLabel("Status:");
        statusText.setForeground(Color.LIGHT_GRAY);
        center.add(statusText);

        JLabel statusLabel = new JLabel(currentStatus);
        statusLabel.setFont(new Font(font, Font.BOLD, 13));
        statusLabel.setForeground(Color.WHITE);
        center.add(statusLabel);

        panel.add(center, BorderLayout.CENTER);

        // ----- SOUTH -----
        JPanel south = new JPanel(new BorderLayout(5, 0));
        south.setOpaque(false);

        ProgressBarRenderer bar = new ProgressBarRenderer();
        bar.setValue(progress);
        bar.setStringPainted(true);
        bar.setOpaque(false); // important
        south.add(bar, BorderLayout.CENTER);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.putClientProperty(
                "FlatLaf.style",
                "background: #00afa0; foreground: #555; arc:12"
        );
        cancelButton.setFocusPainted(false);
        south.add(cancelButton, BorderLayout.EAST);

        cancelButton.addActionListener(e -> panel.setVisible(false));

        panel.add(south, BorderLayout.SOUTH);

        return panel;
    }

    // ===== CREATE TASKS =====
    public ArrayList<JPanel> createTasks(ArrayList<Task> list) {
        ArrayList<JPanel> panels = new ArrayList<>();
        for (Task t : list) {
            panels.add(createTaskPanel(
                    String.valueOf(t.getTaskNumber()),
                    t.getClientName(),
                    t.getStatus().toString(),
                    t.getProgressPercentage()
            ));
        }
        return panels;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
