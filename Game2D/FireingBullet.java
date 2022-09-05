import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class FireingBullet{

    private float DMG,FireRate,Spread,BulletAmount,BulletSpeed,Knockback,Special;

    private Color c = Color.black;
    private ArrayList<Bullet> Particles;
    private float lifespan;
    public boolean fireing =  false;
    public PVector origin;
    
    public FireingBullet(float lifespan, PVector origin, Weapon gun){

        this.Special = gun.Special;
        this.DMG = gun.DMG;
        this.FireRate = gun.FireRate;
        this.Knockback = gun.Knockback;
        this.BulletAmount = gun.BulletAmount;
        this.Spread = gun.Spread;
        this.BulletSpeed = gun.BulletSpeed;

        this.lifespan = lifespan; 
        this.origin = origin;
        Particles = new ArrayList<Bullet>(); 
    }
    public void resetBullets(){
        Particles = new ArrayList<Bullet>();
    }

    public int UpdateCount = 0;
    public void update(Weapon gun, PVector mouse, Player pl){
        UpdateCount++;
        this.origin = new PVector(pl.mov.location.getx()+pl.width/2, pl.mov.location.gety()+pl.height/2+10);
        fireing=gun.shooting;
            
        Random r = new Random();
        int k = r.nextInt(3);
        if(k < 2){
            c = Color.black;
        } else {
            c = Color.darkGray;
        }
        if(fireing && UpdateCount % FireRate==0){
            for(int i=0;i<BulletAmount;i++){
            mouse = mouse.get();
            mouse.normalize();
            mouse.mult(BulletSpeed);
            Particles.add(new Bullet(origin.getx(),origin.gety(),10,c,lifespan,mouse.get(),DMG));
            }
        } 
            for(int i =Particles.size()-1;i>=0;i--){
            Bullet p = (Bullet) Particles.get(i);
            if(r.nextBoolean()){
                p.velocity.y += Spread*r.nextFloat();
            } else{
                p.velocity.y -= Spread*r.nextFloat();
            }
            p.update();
            if(p.isdead()){
                Particles.remove(p);
            } 
        }
    }

    public void draw(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();

        for(Bullet p : Particles){
            p.draw(gg);
        }
        gg.dispose();
    }
    
}