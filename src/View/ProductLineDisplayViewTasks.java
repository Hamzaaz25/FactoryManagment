package View;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class ProductLineDisplayViewTasks extends JPanel {
    public ProductLineDisplayViewTasks() {

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(28, 42, 66));

        JPanel tasksContainer = new JPanel();
        tasksContainer.setLayout(new GridLayout(3, 1, 20, 20));
        tasksContainer.setBorder(BorderFactory.createEmptyBorder(80, 170, 220, 20));
        tasksContainer.setBackground(new Color(28, 42, 66));

        tasksContainer.add(createTask("1", "First Task", 60));
        tasksContainer.add(createTask("2", "Second Task", 40));
        tasksContainer.add(createTask("3", "Third Task", 15));

        this.add(tasksContainer, BorderLayout.CENTER);
    }

    private JPanel createTask(String id, String name, int progress) {

        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(null);
        taskPanel.setBackground(new Color(38, 55, 85));
        taskPanel.setBorder(BorderFactory.createLineBorder(new Color(58, 80, 120), 2));

        JLabel title = new JLabel("ID: " + id + " - " + name);
        title.setBounds(20, 15, 400, 25);
        title.setForeground(new Color(220, 230, 245));
        title.setFont(new Font("Arial", Font.BOLD, 16));
        taskPanel.add(title);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(20, 55, 1200, 28);
        progressBar.setValue(progress);
        progressBar.setStringPainted(true);
        progressBar.setBackground(new Color(30, 45, 70));
        progressBar.setForeground(new Color(100, 150, 200));

        //from light to darker colour
        progressBar.setUI(new BasicProgressBarUI() {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g;

                int width = progressBar.getWidth();
                int height = progressBar.getHeight();
                int fill = (int) (width * progressBar.getPercentComplete());

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(100, 150, 200),
                        width, 0, new Color(40, 70, 120)
                );

                g2.setPaint(gradient);
                g2.fillRect(0, 0, fill, height);

                g2.setColor(Color.DARK_GRAY);
                g2.drawRect(0, 0, width - 1, height - 1);

                paintString(g, 0, 0, width, height, fill, null);
            }
        });

        taskPanel.add(progressBar);

        return taskPanel;
    }

}
