package View.Util;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateRangePopup extends JFrame {

    private static Color POPUP_COLOR = new Color(200, 230, 255);

    private  JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JButton submitButton;

    public DateRangePopup() {
        setTitle("Select Date Range");
        setSize(350, 150);
        setLocationRelativeTo(null);
        setResizable(false);



        JPanel panel = new JPanel();
        panel.setBackground(POPUP_COLOR);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd");
        startDateSpinner.setEditor(startEditor);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Start Date:"), gbc);
        gbc.gridx = 1;
        panel.add(startDateSpinner, gbc);

        endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd");
        endDateSpinner.setEditor(endEditor);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("End Date:"), gbc);
        gbc.gridx = 1;
        panel.add(endDateSpinner, gbc);


        submitButton = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(submitButton, gbc);

        add(panel);


    }


    public LocalDate getStartDate() {
        Date date = (Date) startDateSpinner.getValue();
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate getEndDate() {
        Date date = (Date) endDateSpinner.getValue();
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public JButton getSubmitButton() {
        return submitButton;
    }
}
