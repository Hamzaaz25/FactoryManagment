package View;
import Model.Task;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ProductLineDisplayView extends JPanel {

    JPanel background;
    JLabel title;
    String font ="Segoe UI";
    JButton saveButton;
    ArrayList<JPanel> taskPanels = new ArrayList<>();


    public ProductLineDisplayView(ArrayList<Task> tasks) {

        setLayout(new BorderLayout());
        setBackground(new Color(0, 35, 71));


        title = new JLabel("First Product Line", SwingConstants.CENTER);
        title.setFont(new Font(font, Font.BOLD, 28));
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(new EmptyBorder(20, 150, 20, 150));
        centerPanel.setBackground(new Color(0, 35, 71));
        centerPanel.setOpaque(true);
        add(centerPanel, BorderLayout.CENTER);


        this.taskPanels =createTasks(tasks);
        for(JPanel pa :this.taskPanels){
            centerPanel.add(pa);
        }
        centerPanel.add(Box.createVerticalStrut(50));


        saveButton = new JButton("Save Changes");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setFont(new Font(font, Font.BOLD, 16));
        centerPanel.add(saveButton);

        saveButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to apply these changes?",
                    "Confirm Changes",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(
                        this,
                        "Changes applied successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
    }




    ProgressBarRenderer bar;
    JButton cancelButton;

    private JPanel createTaskPanel(String id, String taskName,
                                     String currentStatus,
                                   int progress) {

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(8, 8, 8, 8),
                BorderFactory.createLineBorder(new Color(255, 255, 255), 1, true)
        ));

        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

        // ===== NORTH : Title =====
        JLabel titleLabel = new JLabel("ID: " + id + "   -   " + taskName);
        titleLabel.setFont(new Font(font, Font.BOLD, 16));
        titleLabel.putClientProperty("FlatLaf.style",
                "font: bold +2");

        panel.add(titleLabel, BorderLayout.NORTH);

        // ===== CENTER : Controls =====
        JPanel center = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        center.setOpaque(false);


        center.add(new JLabel("Status:"));
        JLabel statusLabel = new JLabel(currentStatus);
        statusLabel.setFont(new Font(font, Font.BOLD, 13));
        center.add(statusLabel);

        panel.add(center, BorderLayout.CENTER);

        // ===== SOUTH : Progress + Cancel =====
        JPanel south = new JPanel(new BorderLayout(5, 0));
        south.setOpaque(false);

        bar = new ProgressBarRenderer();
        bar.setValue(progress);
        bar.setStringPainted(true);
        south.add(bar, BorderLayout.CENTER);

        this.cancelButton = new JButton("Cancel");
        south.add(cancelButton, BorderLayout.EAST);
        cancelButton.putClientProperty("FlatLaf.style",
                "background: #f5f5f5; foreground: #555; arc:12");
        cancelButton.setFocusPainted(false);

        panel.add(south, BorderLayout.SOUTH);

        this.cancelButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    panel,
                    "Cancel this task?",
                    "Confirm Cancel",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                panel.setVisible(false);
            }
        });

        return panel;
    }

   public ArrayList<JPanel> createTasks(ArrayList<Task> list){
        ArrayList<JPanel>  panels = new ArrayList<>();
        for(Task t : list){
           panels.add(createTaskPanel(String.valueOf(t.getTaskNumber()), t.getClientName(),  t.getStatus().toString(), t.getProgressPercentage()));
        }
        return  panels;
   }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

}
