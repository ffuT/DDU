import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Enemy {
    protected float health;
    protected float x;
    protected float y;
    protected float moveSpeed;
    protected boolean isalive;

    protected String imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\";
    public BufferedImage img;
    public Enemy(int x, int y, float health,String spritename,float movementspeed){
        this.health=health;
        this.x=x*4;
        this.y=y*4;
        this.isalive = true;
        this.moveSpeed = movementspeed;
        this.imgpath += spritename;
        try {
            img=ImageIO.read(new File(imgpath));
        } catch (Exception e) {
            System.out.println("enemy texture cant load");
        }
    }

    public void update(Weapon gun, Player p){
        if(!isalive)
            return;
        
        PVector Plocation = new PVector(p.mov.location.x,p.mov.location.y);
        PVector Enemlocation = new PVector(x/4,y/4);
        
        Plocation.sub(Enemlocation);
        Plocation.normalize();
        Plocation.mult(moveSpeed);

        x += Plocation.x;
        y += Plocation.y;


        if(p.hitBox(new PVector(x/4+10,y/4+10), new PVector(x/4+img.getWidth()/4-10,y/4+img.getHeight()/4-10))){
            p.hitpoints -= 1;
        }
        

        for (int i=0;i<=gun.FB.Particles.size()-1;i++) {
            if(gun.FB.Particles.get(i).hitBox(new PVector(x/4,y/4), new PVector(x/4+img.getWidth()/4,y/4+img.getHeight()/4))){ 
                if(isalive)
                    gun.FB.Particles.remove(gun.FB.Particles.get(i));
                //deal damage here
                health-=gun.DMG;
            }
        }
        if(health<=0)
            isalive=false;
    }

    public void draw(Graphics g){
        Graphics2D gg =  (Graphics2D) g.create();
        gg.scale(0.25, 0.25);
        if(!isalive)
            return;
        gg.drawImage(img,(int) x,(int) y,null);
    }
}