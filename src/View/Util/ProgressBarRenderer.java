package View.Util;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;


public class ProgressBarRenderer extends JProgressBar implements TableCellRenderer {

    public ProgressBarRenderer() {
        super(0, 100);
        setStringPainted(true);
        setBorderPainted(false);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        int progress = 0;
        if (value instanceof Integer) {
            progress = (Integer) value;
        }

        setValue(progress);
        setString(progress + "%");


        Color fillColor;
        if (progress < 50) {
            fillColor = new Color(204, 204, 255);
        } else if (progress < 80) {
            fillColor = new Color(79, 75, 158);
        } else {
            fillColor = new Color(0, 49, 152);
        }


        Color startColor = fillColor.brighter();
        Color endColor = fillColor.darker();


        setForeground(fillColor);
        setBackground(new Color(60, 60, 60));

        return this;
    }


    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();
        int progressWidth = (int) (width * getPercentComplete());


        g2.setColor(getBackground());
        g2.fillRect(0, 0, width, height);


        Color startColor = getForeground().brighter();
        Color endColor = getForeground().darker();
        GradientPaint gp = new GradientPaint(0, 0, startColor, progressWidth, 0, endColor);

        g2.setPaint(gp);
        g2.fillRect(0, 0, progressWidth, height);


        if (isStringPainted()) {
            g2.setColor(Color.WHITE);
            String text = getString();
            FontMetrics fm = g2.getFontMetrics();
            int stringWidth = fm.stringWidth(text);
            int stringHeight = fm.getAscent();
            g2.drawString(text, (width - stringWidth) / 2, (height + stringHeight) / 2 - 2);
        }

        g2.dispose();
    }
}
