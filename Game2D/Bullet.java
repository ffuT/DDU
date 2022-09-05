import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Bullet extends mover {
    int width=5,height=5;
    float lifepsan;
    Color c;

    //mass is irrelevant
    public Bullet(int x, int y, float mass, Color c, float life, PVector velocity) {
        super(x, y, mass);
        this.doesbounce = false;
        this.staybounds = false;
        this.c = c;
        this.lifepsan = life;     
        this.velocity = velocity;
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
        gg.fillOval(0-width/2,0-height/2, width, height);
        gg.dispose();
    }

    public boolean isdead(){
        if(lifepsan < 0.0){
            return true;
        } else return false;
    }
}