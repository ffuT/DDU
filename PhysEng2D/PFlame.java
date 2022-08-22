import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;


public class PFlame{
    private Color c = Color.orange;
    private ArrayList<Particle2> Particles;
    private float lifespan;
    public boolean fireing =  true;
    public PVector origin;

    public PFlame(float lifespan, PVector origin){
        this.lifespan = lifespan; 
        this.origin = origin;
        Particles = new ArrayList<Particle2>(); 
        Particles.add(new Particle2(origin.getx(),origin.gety(),10,c,lifespan));
    }

    public void update(){
        if(!fireing)
            return;
        Random r = new Random();
        int k = r.nextInt(3);
        if(k < 2){
            c = Color.red;
        } else {
            c = Color.orange;
        }
        Particles.add(new Particle2(origin.getx(),origin.gety()+r.nextInt(-5,5),10,c,lifespan));
        Particles.add(new Particle2(origin.getx(),origin.gety(),10,c,lifespan));
        for(int i =Particles.size()-1;i>=0;i--){
            Particle2 p = (Particle2) Particles.get(i);
            p.aAcceleration = r.nextFloat();
            p.acceleration.add(new PVector(r.nextFloat(0.002f),r.nextFloat(0.002f)));
            p.addforce(new PVector(0,r.nextFloat(0.03f)));
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

        for(Particle2 p : Particles){
            p.draw(gg);
        }
        gg.dispose();
    }
}
