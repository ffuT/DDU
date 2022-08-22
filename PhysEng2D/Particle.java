import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class Particle {
    public PVector location,velocity,acceleration;
    public float lifepsan;
    public Particle(PVector l) {
        this.location = l.get();
        this.acceleration = new PVector();
        this.velocity = new PVector();
        this.lifepsan = 255;
    }

    public void update(){
        //x and y movement
        velocity.add(acceleration);
        location.add(velocity);
        
        acceleration.mult(0f);
        
        lifepsan-=2;
    }

    public void draw(Graphics g){
        if(isdead())
            return;
        Graphics2D gg = (Graphics2D) g.create();
        gg.setColor(Color.orange);
        gg.fillOval(location.getx(),location.gety(), 8,8);
        gg.dispose();
    }

    public boolean isdead(){
        if(lifepsan < 0.0){
            return true;
        } else return false;
    }
}
