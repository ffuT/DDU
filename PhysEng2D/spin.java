import java.awt.Graphics2D;
import java.awt.Color;

public class spin {
    
    spin(){

    }
    public void draw(Graphics2D gg, Color c, float rotation){
        Graphics2D g = (Graphics2D) gg.create();
        g.setColor(c);
        g.translate(Display2.WIDTH/2, Display2.HEIGHT/2);
        g.rotate(toRadians.toRad(rotation));
        g.drawLine(-50,0,50,0);
        g.fillOval(50,0,8,8);
        g.fillOval(-50,0,8,8);
        g.dispose();
    }
}
