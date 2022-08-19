import java.awt.Graphics2D;
import java.awt.Color;

public class TestSquare2 {
    private int width,height;
    float gravity;
    mover mov;
    TestSquare2(int x, int y, int width, int height, float mass, float gravity){
        mov = new mover(x, y, mass);
        this.width = width;
        this.height = height;
        this.gravity = gravity * mov.mass;
    }
    public void draw(Graphics2D gg, Color k){
        Graphics2D g = (Graphics2D) gg.create();
        g.translate(mov.location.x, mov.location.y);
        g.setColor(k);
        g.rotate(toRadians.toRad(mov.angle));
        g.fillRect(0-width/2,0-height/2, width, height);

        g.dispose();
    }
}