import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Barrel {
    public BufferedImage img;
    public int x,y;
    public Barrel(int x, int y){
        this.x=x*4;
        this.y=y*4;
        try {
            img = ImageIO.read(new File("C:\\Users\\Tuff\\Documents\\GitHub\\DDU\\Game2D\\assets\\Exsplosive_barrel.png"));   
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
        gg.drawImage(img, x, y,null);

    }
    public void update(){
        /* 
         * if hitbox bullet
         * draw explos
         * stop draw barrel
         * deal damage
         */
    }


    //damage and destroy!!
    public void explode(){

    }
}
