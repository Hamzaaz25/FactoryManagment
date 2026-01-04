package View;

import Enums.TaskStatus;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import Model.Task;

public class TaskView extends JPanel {
    // ==============================
    // MODEL
    // ==============================
    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> sorter;
    private  List<Task> tasks = new ArrayList<>();
    private final String[] COLUMNS = { "Task number ","Product Line #", "Product", "Status", "Progress" };

    // ==============================
    // VIEW COMPONENTS
    // ==============================
    private JPanel jPanel1;
    private JScrollPane scroll;
    private JTable table;

    public TaskView(ArrayList<Task> task) {
        initComponents();
        this.tasks = task;
        initTable();
        loadTasks();
        initSorter();
        styleComponents();
        startProgressUpdater(); // dynamic progress
    }

    // ------------------------------
    // 1️⃣ Initialize table
    // ------------------------------
    private void initTable() {
        model = new DefaultTableModel(COLUMNS, 0) {
            boolean[] canEdit = { false, false, false, false, false };

            @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        };

        table.setModel(model);
        table.setDefaultRenderer(Object.class, new TableGradientCell());
        table.getColumnModel().getColumn(4).setCellRenderer(new ProgressBarRenderer()); // progress bar
    }

    // ------------------------------
    // 2️⃣ Load sample tasks
    // ------------------------------
    private void loadTasks( ) {


        refreshTableFromTasks();
    }

    private void refreshTableFromTasks() {
        model.setRowCount(0); // clear
        for (Task t : this.tasks) {
            model.addRow(new Object[]{
                    t.getTaskNumber(),
                    t.getProductLine(),
                    t.getRequestedProduct(),
                    t.getStatus(),
                    t.getProgressPercentage()
            });
        }
    }

    // ------------------------------
    // 3️⃣ Sorter / Filter
    // ------------------------------
    private void initSorter() {
        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
    }

    // Filter panel creation
    public JPanel createFilterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 16));
        panel.putClientProperty(
                FlatClientProperties.STYLE,
                "background:$Table.background; border:0,0,1,0,$Component.borderColor"
        );

        JTextField productLine = new JTextField(10);
        productLine.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Product Line #");

        JTextField productSearchBox = new JTextField(16);
        productSearchBox.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Product Name");

        JComboBox<String> status = new JComboBox<>(new String[]{"All", TaskStatus.Completed.toString(), TaskStatus.InProgress.toString(), TaskStatus.Cancelled.toString()});

        JButton clearBtn = new JButton("Clear");

        SimpleDocumentListener listener = new SimpleDocumentListener() {
            @Override
            public void update() {
                applyFilters(productLine.getText(), productSearchBox.getText(), status.getSelectedItem().toString());
            }
        };

        productLine.getDocument().addDocumentListener(listener);
        productSearchBox.getDocument().addDocumentListener(listener);
        status.addActionListener(e -> applyFilters(productLine.getText(), productSearchBox.getText(), status.getSelectedItem().toString()));

        clearBtn.addActionListener(e -> {
            productLine.setText("");
            productSearchBox.setText("");
            status.setSelectedIndex(0);
            sorter.setRowFilter(null);
        });

        panel.add(productLine);
        panel.add(productSearchBox);
        panel.add(status);
        panel.add(clearBtn);

        return panel;
    }

    private void addFilterPanel() {
        jPanel1.add(createFilterPanel(), BorderLayout.NORTH);
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    private void applyFilters(String lineNo, String productName, String status) {
        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        if (!lineNo.isBlank()) filters.add(RowFilter.regexFilter("^" + lineNo, 1));
        if (!productName.isBlank()) filters.add(RowFilter.regexFilter("(?i)" + productName, 2));
        if (!status.equals("All")) filters.add(RowFilter.regexFilter("^" + status + "$", 3));

        sorter.setRowFilter(filters.isEmpty() ? null : RowFilter.andFilter(filters));
    }

    // ------------------------------
    // 4️⃣ Components / Layout
    // ------------------------------
    private void initComponents() {
        jPanel1 = new JPanel();
        scroll = new JScrollPane();
        table = new JTable();
        setLayout(new BorderLayout());
        jPanel1.setLayout(new BorderLayout());
        addFilterPanel();
        table.setRowHeight(64);
        jPanel1.add(createSidebar() , BorderLayout.WEST);
        scroll.setViewportView(table);
        jPanel1.add(scroll, BorderLayout.CENTER);

        this.add( jPanel1, BorderLayout.CENTER);


    }

    // FlatLaf styling
    private void styleComponents() {
        jPanel1.putClientProperty(FlatClientProperties.STYLE,
                "border:1,1,1,1,$TableHeader.bottomSeparatorColor,,10");
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE,
                "hoverBackground:null;pressedBackground:null;separatorColor:$TableHeader.background");
        scroll.putClientProperty(FlatClientProperties.STYLE,
                "border:3,0,3,0,$Table.background,10,10");
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,
                "hoverTrackColor:null");
    }

    // ------------------------------
    // 5️⃣ DYNAMIC PROGRESS UPDATER
    // ------------------------------
    private void startProgressUpdater() {
        Timer timer = new Timer(1000, e -> {
            for (int i = 0; i < this.tasks.size(); i++) {
                Task t = this.tasks.get(i);
                if(t.getProgressPercentage() >=100 ) {
                    t.setProgressPercentage(100);
                    model.setValueAt(t.getProgressPercentage(), i, 4); // update table
                    t.setStatus(TaskStatus.Completed);
                    model.setValueAt(t.getStatus().toString() ,i, 3 );
                }
                if ( t.getStatus() == TaskStatus.InProgress && t.getProgressPercentage() < 100) {

                    model.setValueAt(t.getProgressPercentage(), i, 4); // update table

                }
            }
        });
        timer.start();


    }

    private JPanel createSidebar() {
        ImageSideBar sidebar = new ImageSideBar(); // ✅ USE IT
        sidebar.setPreferredSize(new Dimension(460, 0));

        sidebar.putClientProperty(
                FlatClientProperties.STYLE,
                "border:0,1,0,0,$Component.borderColor"
        );

        return sidebar;
    }

}
