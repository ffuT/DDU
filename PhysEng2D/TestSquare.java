import java.awt.Graphics;
import java.awt.Color;

public class TestSquare {
    private int width,height;
    float gravity;
    mover mov;
    TestSquare(int x, int y, int width, int height, float mass, float gravity){
        mov = new mover(x, y, mass);
        this.width = width;
        this.height = height;
        this.gravity = gravity * mov.mass;
    }
    public void draw(Graphics g, Color k){
        g.setColor(k);
        g.fillRect(mov.location.getx(), mov.location.gety(), width, height);
    }
}
