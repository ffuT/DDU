import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class Bossman1 {
    public float health,movementspeed,damage,x,y;
    public BufferedImage img;
    public boolean isalive;

    private String imgpath = "C:\\Users\\Tuff\\Documents\\GitHub\\DDU\\Game2D\\assets\\BOSSMAN.png";
    private Random r = new Random();
    private Enemy1projectile projectile;

    public Bossman1(int x, int y, float health) {
        this.x = x;
        this.y = y;
        this.health = health;
        this.isalive = true;

        try {
            img = ImageIO.read(new File(imgpath));
        } catch (Exception e) {
            System.out.println("cant load bossman texture");
            System.exit(1);
        }
    }
    PVector dir;

    public void update(Player p,Weapon gun){
        if(!isalive){
            return;
        }
        if(health<=0){
            isalive = false;
        }
        
         if(projectile!=null && projectile.isalive){
            projectile.update(gun, p, dir);
        } else{
            projectile = new Enemy1projectile((int) x+img.getWidth()/4,(int) (y+img.getHeight()/2.5f), 120);
            dir = new PVector(p.mov.location.x,p.mov.location.y);
            dir.sub(new PVector(projectile.x/4, projectile.y/4));
        }

        for (int i=0;i<=gun.FB.Particles.size()-1;i++) {
            if(gun.FB.Particles.get(i).hitBox(new PVector(x,y), new PVector(x+img.getWidth(),y+img.getHeight()))){ 
                if(isalive)
                    gun.FB.Particles.remove(gun.FB.Particles.get(i));
                //deal damage here
                health-=gun.DMG;
            }
        }

        PVector Plocation = new PVector(p.mov.location.x,p.mov.location.y);
        PVector Enemlocation = new PVector(x,y);
        
        Plocation.sub(Enemlocation);
        Plocation.normalize();
        Plocation.mult(1.25f);

        x += Plocation.x;
        y += Plocation.y;


        if(p.hitBox(new PVector(x+10,y+10), new PVector(x+img.getWidth()-10,y+img.getHeight()-10))){
            p.hitpoints -= 2;
        }
    }

    public void draw(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        if(!isalive)
            return;
        gg.drawImage(img, (int) x, (int) y, null);
        if(projectile!=null)
        projectile.draw(g);
        
        gg.dispose();
    }

    public void shoot(){
         
    }
    
}
