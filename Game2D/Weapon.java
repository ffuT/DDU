import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Weapon {
    private float DMG,FireRate,Spread,BulletAmount,BulletSpeed,Knockback,Special;
    private BufferedImage img;

    public Weapon(Part1 p1, Part2 p2, Part3 p3){
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
                String imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\gun1.png";
                img = ImageIO.read(new File(imgpath));                
            }

        } catch (Exception e) {
            System.out.println("cant get texture");
            System.exit(1);
        }
    }
    public void drawgun(Graphics g, Player p){
        Graphics2D gg = (Graphics2D) g.create();
        gg.drawImage(img, p.mov.location.getx()+20, p.mov.location.gety()+20, null);

    }
}
