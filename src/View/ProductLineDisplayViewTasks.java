package View;

import Enums.TaskStatus;
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
    private final List<Task> tasks = new ArrayList<>();
    private final List<JProgressBar> progressBars = new ArrayList<>();
    private Timer progressTimer;


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
        this.tasks.clear();
        this.tasks.addAll(tasks);

        tasksContainer.removeAll();
        progressBars.clear();

        for (Task task : tasks) {
            JPanel card = createTaskView(task);
            tasksContainer.add(card);
            tasksContainer.add(Box.createVerticalStrut(20));
        }

        revalidate();
        repaint();

        startProgressUpdater();
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

        // ---- TOP: title + cancel
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));

        JLabel title = new JLabel(
                "ID: " + task.getTaskNumber() + " - " + task.getRequestedProduct()
        );
        title.setForeground(new Color(220, 230, 245));
        title.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblStatus = new JLabel(task.getStatus().toString());
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblStatus.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));

        switch (task.getStatus()) {
            case InProgress -> lblStatus.setForeground(new Color(100, 200, 255));
            case Completed  -> lblStatus.setForeground(new Color(46, 204, 113));
            case Pending    -> lblStatus.setForeground(new Color(241, 196, 15));
            case Cancelled  -> lblStatus.setForeground(new Color(231, 76, 60));
        }

        JPanel eastActions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        eastActions.setOpaque(false);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFocusPainted(false);
        cancelBtn.putClientProperty(
                "FlatLaf.style",
                "background: #f5f5f5; foreground: #555; arc:10"
        );


        cancelBtn.addActionListener(e -> onCancelTask.accept(task));

        eastActions.add(lblStatus);
        eastActions.add(cancelBtn);

        top.add(title, BorderLayout.WEST);
        top.add(eastActions, BorderLayout.EAST);

        taskPanel.add(top, BorderLayout.NORTH);

        // ---- CENTER: progress
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(task.getProgressPercentage());
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(800, 28));
        progressBar.setBackground(new Color(30, 45, 70));
        progressBar.setForeground(new Color(100, 150, 200));
        progressBar.setUI(createGradientProgressUI(progressBar));

        progressBars.add(progressBar);







        JPanel progressWrapper = new JPanel(new BorderLayout());
        progressWrapper.setOpaque(false);
        progressWrapper.setBorder(
                BorderFactory.createEmptyBorder(0, 20, 20, 20)
        );
        progressWrapper.add(progressBar, BorderLayout.CENTER);

        taskPanel.add(progressWrapper, BorderLayout.CENTER);

        return taskPanel;
    }


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


    private void startProgressUpdater() {

        if (progressTimer != null) {
            progressTimer.stop();
        }

        progressTimer = new Timer(1000, e -> {

            int count = Math.min(tasks.size(), progressBars.size());
            boolean anyRunning = false;

            for (int i = 0; i < count; i++) {
                Task task = tasks.get(i);
                JProgressBar bar = progressBars.get(i);

                if (task.getProgressPercentage() >= 100) {
                    task.setProgressPercentage(100);
                    bar.setValue(100);
                }
                else if (task.getStatus() == TaskStatus.InProgress) {
                    bar.setValue(task.getProgressPercentage());
                    anyRunning = true;
                }
            }


            if (!anyRunning) {
                progressTimer.stop();
            }
        });

        progressTimer.start();
    }

}
