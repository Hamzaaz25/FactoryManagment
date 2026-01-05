package View;
import javax.swing.*;
import java.awt.*;

public class ProductLineDisplayView extends JPanel {

    JPanel background;
    JLabel title;

    JPanel task1;
    JPanel task2;
    JPanel task3;

    JButton saveButton;


        public ProductLineDisplayView() {

         //   this.setTitle("Supervisor - Task Management");
            this.setSize(1000, 650);
         //   this.setLocationRelativeTo(null);
         //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);

            //background
            background = new JPanel();
            background.setBackground(new Color(108, 185, 190));
            background.setBounds(0, 0, 1000, 650);
            background.setLayout(null);
            this.add(background);

            //title label
            title = new JLabel("First Product Line", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 28));
            title.setBounds(250, 20, 500, 40);
            background.add(title);

            //First Task
            task1 = createTaskPanel(
                    "1",
                    "First Task",
                    "First Product Line",
                    "Running",
                    60
            );
            task1.setBounds(150, 100, 650, 130);
            background.add(task1);

            //Second Task
            task2 = createTaskPanel(
                    "2",
                    "Second Task",
                    "First Product Line",
                    "Stopped",
                    40
            );
            task2.setBounds(150, 250, 650, 130);
            background.add(task2);

            //Third Task
            task3 = createTaskPanel(
                    "3",
                    "Third Task",
                    "First Product Line",
                    "Disabled",
                    0
            );
            task3.setBounds(150, 400, 650, 130);
            background.add(task3);

            //save changes button
            saveButton = new JButton("Save Changes");
            saveButton.setBounds(410, 560, 180, 45);
            saveButton.setFont(new Font("Arial", Font.BOLD, 16));
            saveButton.setBackground(Color.BLACK);
            saveButton.setForeground(Color.WHITE);
            saveButton.setFocusPainted(false);
            background.add(saveButton);

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
            this.setVisible(true);
        }

    JPanel panel;
    JLabel titleLabel;

    JLabel pLineLabel;
    JLabel PStatusLabel;

    JProgressBar bar;
    JButton deleteButton;

    private JPanel createTaskPanel(String id, String taskName,
                                   String currentLine, String currentStatus,
                                   int progress) {

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        //Task Title Label
        titleLabel = new JLabel("ID: " + id + "   -   " + taskName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(20, 10, 400, 25);
        panel.add(titleLabel);

        //Product Line Tasks
        pLineLabel = new JLabel("Product Line:");
        pLineLabel.setBounds(20, 45, 100, 25);
        panel.add(pLineLabel);

        JComboBox<String> lineBox = new JComboBox<>(
                new String[]{"Tables", "Beds", "Curtains"}
        );
        lineBox.setSelectedItem(currentLine);
        lineBox.setBounds(130, 45, 200, 30);
        panel.add(lineBox);

        //Product Line Statues
        PStatusLabel = new JLabel("Status:");
        PStatusLabel.setBounds(360, 45, 60, 25);
        panel.add(PStatusLabel);

        JComboBox<String> statusBox = new JComboBox<>(
                new String[]{"Running", "Stopped", "Disabled"}
        );
        statusBox.setSelectedItem(currentStatus);
        statusBox.setBounds(430, 45, 160, 30);
        panel.add(statusBox);

        //Tasks Progress Bar
        bar = new JProgressBar(0, 100);
        bar.setValue(progress);
        bar.setStringPainted(true);
        bar.setBounds(20, 90, 420, 25);
        panel.add(bar);

        //Task Deleting
        deleteButton = new JButton("Delete Task");
        deleteButton.setBounds(460, 85, 160, 35);
        panel.add(deleteButton);

        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    panel,
                    "Are you sure you want to delete this task?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
               panel.setVisible(false);
            }
        });

        return panel;
    }
}
