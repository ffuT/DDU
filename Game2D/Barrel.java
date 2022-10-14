import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class Barrel {
    private Random r = new Random();
    public BufferedImage img;
    public int x,y;
    public boolean isalive;
    public boolean explode;
    public int explosion=10;

    public Barrel(int x, int y){
        isalive=true;
        explode=false;
        this.x=x*4;
        this.y=y*4;
        try {
            img = ImageIO.read(new File("C:\\Users\\hille\\Documents\\GitHub\\DDU\\Game2D\\assets\\Exsplosive_barrel.png"));   
        } catch (Exception e) {
            System.out.println("cant get barrel texture");
        }
    }

    //setters cus it has to be 4x larger
    public void setx(int x){
        this.x = x*4;
    }
    public void sety(int y){
        this.y = y*4;
    }

    public void draw(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        gg.scale(0.25, 0.25); 
        if(isalive){
            gg.drawImage(img, x, y,null);
        }
        if(explode){
            for(int i =0;i<=10;i++){
                gg.setColor(Color.red);
                int rn= r.nextInt(50);
                gg.fillOval(x+img.getWidth()/2-(400-rn)/2, y+img.getHeight()/2-(400-rn)/2, 400-rn, 400-rn);            
                if(explosion>=5){
                    int rn2 = r.nextInt(100);
                    gg.setColor(Color.orange);
                    gg.fillOval(x+img.getWidth()/2-(750-rn2)/2, y+img.getHeight()/2-(750-rn2)/2, 750-rn2, 750-rn2);
                }
            }
            if(explosion<=0)
            explode=false;
        }
    }

    public void update(Weapon gun){
        if(explode)
            explosion--;

        for (int i=0;i<=gun.FB.Particles.size()-1;i++) {
            if(gun.FB.Particles.get(i).hitBox(new PVector(x/4,y/4), new PVector(x/4+img.getWidth()/4,y/4+img.getHeight()/4))){ 
                if(isalive)
                    gun.FB.Particles.remove(gun.FB.Particles.get(i));
                Explode();
                isalive=false;
                //deal damage here
            }
        }
    }

    //damage and destroy!!
    public void Explode(){
        if(!isalive)
            return;
        explode=true;
    }

    //it dont work
    public Boolean hitBoxSPH(PVector p1, PVector p2){
        PVector origin = new PVector(x/4+img.getWidth()/4/2,y/4+img.getHeight()/4/2);
        if(Math.sqrt((origin.x-p1.x)*(origin.x-p1.x)+(origin.y-p1.y)*(origin.y-p1.y))<375/4 ||
        Math.sqrt((origin.x-p2.x)*(origin.x-p2.x)+(origin.y-p1.y)*(origin.y-p1.y))<375/4 ||
        Math.sqrt((origin.x-p2.x)*(origin.x-p2.x)+(origin.y-p2.y)*(origin.y-p2.y))<375/4 ||
        Math.sqrt((origin.x-p1.x)*(origin.x-p1.x)+(origin.y-p2.y)*(origin.y-p2.y))<375/4){
            return true;
        }
        return false;
        
    }
}
