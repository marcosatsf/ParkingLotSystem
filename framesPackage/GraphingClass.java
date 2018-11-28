package framesPackage;
import java.awt.*;
import javax.swing.*;
import javax.xml.transform.Source;

public class GraphingClass extends JPanel {
    private static final Color BACKGROUND_COLOR = Color.white;
    private static final Color BAR_COLOR = Color.red;

    private int[] inputData;
    private JFrame source;

    public GraphingClass(final int[] inputData, JFrame source) {
        this.inputData = inputData;
        this.source = source;
    }

    @Override
    public void paintComponent(final Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        drawBars(g2d);
    }

    private void drawBars(final Graphics g) {
        int /*i,*/ OUTER_MARGIN = 2,
                WIDTH = source.getWidth(),
                HEIGHT = source.getHeight();
                /*SPACE_BETWEEN_BARS = 10, SPACE_ON_TOP_BOTTOM = 25;*/

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        String quantity = null;
        g.setColor(BAR_COLOR);
        final int barWidth = source.getWidth() / (inputData.length+1);
        for (int itemIndex = 0; itemIndex < inputData.length; itemIndex++) {
            final int x = OUTER_MARGIN + (barWidth+1) * itemIndex;
            final int barHeight = 10 * inputData[itemIndex];
            final int y = 250 - barHeight;//[...y is calculated using barHeight; the higher the bar, the lower y should be...];
            g.fillRect(x, y, barWidth, barHeight);
           // quantity = new String();
            //quantity = 
            //quantity = inputData[itemIndex].toString();
            g.drawString("X", x+(barWidth/2), y+(barHeight/2));
        }
    }
}
