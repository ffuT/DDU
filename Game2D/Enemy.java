import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Enemy {
    protected float health;
    protected int x;
    protected int y;
    protected boolean isalive;
    protected String imgpath = "C:\\Users\\Tuff\\Documents\\GitHub\\DDU\\Game2D\\assets\\";
    public BufferedImage img;
    public Enemy(int x, int y, float health,String spritename){
        this.health=health;
        this.x=x*4;
        this.y=y*4;
        this.isalive = true;
        this.imgpath += spritename;
    }

    public void update(Weapon gun){
        if(!isalive)
            return;
        
        if(health<=0)
            isalive=false;

        for (int i=0;i<=gun.FB.Particles.size()-1;i++) {
            if(gun.FB.Particles.get(i).hitBox(new PVector(x/4,y/4), new PVector(x/4+img.getWidth()/4,y/4+img.getHeight()/4))){ 
                if(isalive)
                    gun.FB.Particles.remove(gun.FB.Particles.get(i));
                //deal damage here
                health-=gun.DMG;
            }
        }
    }

    public void draw(Graphics g){
        Graphics2D gg =  (Graphics2D) g.create();
        gg.scale(0.25, 0.25);
        if(!isalive)
            return;
        try {
            img=ImageIO.read(new File(imgpath));
        } catch (Exception e) {
            System.out.println("enemy texture cant load");
        }
        gg.drawImage(img, x, y,null);
    }
}