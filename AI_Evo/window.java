import javax.swing.*;
import java.awt.Graphics;
import java.util.Arrays;
public class window extends JFrame{
    private int[] yPoints;

    //makes window and graph
    public window(dna[] yPoints){
        this.yPoints = new int[yPoints.length];
        for(int i=0;i < yPoints.length;i++){
            this.yPoints[i] = yPoints[i].fitness;
        }

        setTitle("look at this Graph!");
        setSize(yPoints.length*11,600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    //draws graph and info
    public void paint(Graphics g){
        super.paint(g);
        
        int[] xPoints = new int[yPoints.length];
        for(int i=0;i<yPoints.length;i++){
            xPoints[i] = i;
        }
        for (int i : xPoints) {
            g.fillRect(i*10+40, 500, 8, -yPoints[i]/3);
            if(i%2==0)
                g.drawString(""+i, i*10+40, 515);   
            if(i==0)
                g.drawString(""+yPoints[i], 15, 500-yPoints[i]/3);
            if(i==yPoints.length/10)
                g.drawString(""+yPoints[i], 15, 500-yPoints[i]/3);
            if(i==yPoints.length/4)
                g.drawString(""+yPoints[i], 15, 500-yPoints[i]/3);
            if(i==yPoints.length-1)
                g.drawString(""+yPoints[i], 15, 500-yPoints[i]/3);
        }
        Arrays.sort(yPoints);
        g.drawString("highest value: " + yPoints[(yPoints.length-1)] + "kr", 25, 50);
    }
}
