package framesPackage;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.xml.transform.Source;

public class GraphingClass extends JPanel {
    private static final Color BACKGROUND_COLOR = Color.white;
    private static final Color BAR_COLOR = Color.red;

    private ArrayList<Integer> inputData;
    //private JPanel source;

    public GraphingClass(ArrayList<Integer> inputData) {
        this.inputData = inputData;
        //this.source = source;
    }

    @Override
    public void paintComponent(final Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);

        drawBars(g2d);
    }

    private void drawBars(final Graphics g) {
        int /*i,*/ OUTER_MARGIN = 0,
        		BOTTOM_MARGIN = 15,
                WIDTH = super.getWidth(),
                HEIGHT = super.getHeight();
                /*SPACE_BETWEEN_BARS = 10, SPACE_ON_TOP_BOTTOM = 25;*/

        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        String quantity = "Teste";
        int multiply = 10;
        
        final int barWidth = super.getWidth() / (inputData.size()); //source.getWidth()
        for (int itemIndex = 0; itemIndex < inputData.size(); itemIndex++) {
        	g.setColor(BAR_COLOR);
            int x = OUTER_MARGIN + (barWidth+1) * itemIndex;
            if((multiply * inputData.get(itemIndex)+BOTTOM_MARGIN) >= super.getHeight())multiply/=10;
            
            int barHeight = multiply * inputData.get(itemIndex);
            
            int y = super.getHeight() - barHeight - BOTTOM_MARGIN;//[...y is calculated using barHeight; the higher the bar, the lower y should be...];
            
            g.fillRect(x, y, barWidth, barHeight);
           // quantity = new String();
            //quantity = 
            //quantity = inputData[itemIndex].toString();
            g.setColor(Color.black);

            quantity = inputData.get(itemIndex).toString();
            g.drawString(inputData.get(itemIndex).toString(), x+5, y+(barHeight/2));
            //g.drawString("X", x, y);
        }
    }
}
