import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Particle2 extends mover {
    int width=10,height=10;
    float lifepsan;
    Color c;

    //mass is irrelevant
    public Particle2(int x, int y, float mass, Color c, float life) {
        super(x, y, mass);
        this.doesbounce = false;
        this.staybounds = false;
        this.aVelocity = 0.5f;
        this.acceleration = new PVector(2.1f,0);
        this.c = c;
        this.lifepsan = life;
    }
    @Override
    public void update() {
        super.update();
        lifepsan-=2;
    }

    public void draw(Graphics g){
        if(isdead())
            return;
        Graphics2D gg = (Graphics2D) g.create();
        
        gg.setColor(c);
        gg.translate(location.x, location.y);
        gg.rotate(toRadians.toRad(angle));
        gg.fillRect(0-width/2,0-height/2, width, height);
        gg.dispose();
    }
    public boolean isdead(){
        if(lifepsan < 0.0){
            return true;
        } else return false;
    }
}
