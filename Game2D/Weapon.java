import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;

public class Weapon {
    private float DMG,FireRate,Spread,BulletAmount,BulletSpeed,Knockback,Special;
    private BufferedImage img;
    private PVector location;

    public Weapon(Part1 p1, Part2 p2, Part3 p3){
        this.location = new PVector();
        //part1
        this.Special = p1.Special;
        this.DMG = p1.DMG;

        //part2
        this.FireRate = p2.FireRate;
        this.Knockback = p2.Knockback;
        this.BulletAmount = p2.BulletAmount;

        //part3
        this.Spread = p3.Spread;
        this.BulletSpeed = p3.BulletSpeed;

        getSprite();
    }
    public void getSprite(){
        try {
            Random r = new Random();
            int temp = r.nextInt(10);
            if(temp <11){
                String imgpath = "C:\\Users\\Tuff\\Documents\\GitHub\\DDU\\Game2D\\assets\\gun1.png";
                img = ImageIO.read(new File(imgpath));                
            }

        } catch (Exception e) {
            System.out.println("cant get texture");
            System.exit(1);
        }
    }
    public void drawgun(Graphics g, Player p,float angle){
        Graphics2D gg = (Graphics2D) g.create();
        gg.translate(p.mov.location.getx()+p.width/2, p.mov.location.gety()+p.height/2+10);
        gg.rotate(angle);
        gg.drawImage(img, 0,0, null);
    }
    public void shoot(Player p){
        //fuck idk man
    }
}
