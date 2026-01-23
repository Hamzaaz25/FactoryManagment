package View;

import Model.Task;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ProductLineDisplayViewTasks extends JPanel {

    private static final Color BG = new Color(28, 42, 66);
    private static final Color CARD = new Color(38, 55, 85);

    private JPanel tasksContainer;
    private final Consumer<Task> onCancelTask;
    private final JButton addTaskButton;

    public ProductLineDisplayViewTasks(ArrayList<Task> tasks,
                                       Consumer<Task> onCancelTask) {

        this.onCancelTask = onCancelTask;

        setLayout(new BorderLayout());
        setBackground(BG);
        setOpaque(true);

        // ---- Add Task Button ----
        addTaskButton = new JButton("ADD TASK");
        addTaskButton.setFocusPainted(false);
        addTaskButton.putClientProperty(
                "FlatLaf.style",
                "background: #4caf50; foreground: #fff; arc:10; font: bold 14"
        );
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topBar.setOpaque(false);
        topBar.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        topBar.add(addTaskButton);

        add(topBar, BorderLayout.NORTH);

        // ---- Tasks container ----
        tasksContainer = new JPanel();
        tasksContainer.setLayout(new BoxLayout(tasksContainer, BoxLayout.Y_AXIS));
        tasksContainer.setBackground(BG);
        tasksContainer.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        tasksContainer.setOpaque(true);

        setTasks(tasks);

        JScrollPane scrollPane = new JScrollPane(tasksContainer);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(BG);
        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        add(scrollPane, BorderLayout.CENTER);
    }

    /* ===== Controller updates tasks ===== */
    public void setTasks(List<Task> tasks) {
        tasksContainer.removeAll();

        for (Task task : tasks) {
            tasksContainer.add(createTaskView(task));
            tasksContainer.add(Box.createVerticalStrut(20));
        }

        revalidate();
        repaint();
    }

    /* ===== Getter for Add Task button ===== */
    public JButton getAddTaskButton() {
        return addTaskButton;
    }

    /* ===== Single task card ===== */
    private JPanel createTaskView(Task task) {

        JPanel taskPanel = new JPanel(new BorderLayout());
        taskPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));
        taskPanel.setBackground(CARD);
        taskPanel.setBorder(BorderFactory.createLineBorder(
                new Color(58, 80, 120), 2
        ));
        taskPanel.setOpaque(true);

        // ---- TOP: title + cancel ----
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));

        JLabel title = new JLabel(
                "ID: " + task.getTaskNumber() + " - " + task.getRequestedProduct()
        );
        title.setForeground(new Color(220, 230, 245));
        title.setFont(new Font("Arial", Font.BOLD, 16));

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFocusPainted(false);
        cancelBtn.putClientProperty(
                "FlatLaf.style",
                "background: #f5f5f5; foreground: #555; arc:10"
        );

        // 🔥 Consumer hook
        cancelBtn.addActionListener(e -> onCancelTask.accept(task));

        top.add(title, BorderLayout.WEST);
        top.add(cancelBtn, BorderLayout.EAST);

        taskPanel.add(top, BorderLayout.NORTH);

        // ---- CENTER: progress ----
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(task.getProgressPercentage());
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(800, 28));
        progressBar.setBackground(new Color(30, 45, 70));
        progressBar.setForeground(new Color(100, 150, 200));
        progressBar.setUI(createGradientProgressUI(progressBar));

        JPanel progressWrapper = new JPanel(new BorderLayout());
        progressWrapper.setOpaque(false);
        progressWrapper.setBorder(
                BorderFactory.createEmptyBorder(0, 20, 20, 20)
        );
        progressWrapper.add(progressBar, BorderLayout.CENTER);

        taskPanel.add(progressWrapper, BorderLayout.CENTER);

        return taskPanel;
    }

    /* ===== Gradient progress bar (unchanged) ===== */
    private BasicProgressBarUI createGradientProgressUI(JProgressBar bar) {
        return new BasicProgressBarUI() {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();

                int width = bar.getWidth();
                int height = bar.getHeight();
                int fill = (int) (width * bar.getPercentComplete());

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(100, 150, 200),
                        width, 0, new Color(40, 70, 120)
                );

                g2.setPaint(gradient);
                g2.fillRect(0, 0, fill, height);

                g2.setColor(Color.DARK_GRAY);
                g2.drawRect(0, 0, width - 1, height - 1);

                paintString(g, 0, 0, width, height, fill, null);
                g2.dispose();
            }
        };
    }
}
