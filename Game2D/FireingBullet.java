import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;


public class FireingBullet{
    private Color c = Color.black;
    private ArrayList<Bullet> Particles;
    private float lifespan;
    public boolean fireing =  true;
    public PVector origin;

    public FireingBullet(float lifespan, PVector origin){
        this.lifespan = lifespan; 
        this.origin = origin;
        Particles = new ArrayList<Bullet>(); 
        Particles.add(new Bullet(origin.getx(),origin.gety(),10,c,lifespan));
    }

    public void update(){
        if(!fireing)
            return;
        Random r = new Random();
        int k = r.nextInt(3);
        if(k < 2){
            c = Color.black;
        } else {
            c = Color.darkGray;
        }
        Particles.add(new Bullet(origin.getx(),origin.gety()+r.nextInt(5),10,c,lifespan));
        Particles.add(new Bullet(origin.getx(),origin.gety(),10,c,lifespan));
        for(int i =Particles.size()-1;i>=0;i--){
            Bullet p = (Bullet) Particles.get(i);
            p.aAcceleration = r.nextFloat();
            p.acceleration.add(new PVector(0,0));
            p.addforce(new PVector(0,0));
            p.update();
            if(p.isdead()){
                Particles.remove(p);
            } 
        }
    }

    public void draw(Graphics g){
        if(!fireing)
            return;
        Graphics2D gg = (Graphics2D) g.create();

        for(Bullet p : Particles){
            p.draw(gg);
        }
        gg.dispose();
    }
}