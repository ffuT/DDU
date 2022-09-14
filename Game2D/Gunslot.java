import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Gunslot extends inventoryslot {
    Weapon gun;
    public Gunslot(int n, int x, int y, Weapon weapon) {
        super(n, x, y);
        this.gun = weapon;
    }
    
    public Gunslot(int n, int x, int y) {
        super(n, x, y);
    }

    public void addweapon(Weapon weapon){
        this.gun = weapon;
    }
    
    @Override
    public void drawslot(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        gg.scale(1, 1);
        gg.drawRect(x, y, 100, 100);
        gg.setColor(Color.red);
        gg.drawLine(x+40, y+100, x+60, y+120);
        gg.drawLine(x+60, y+100, x+40, y+120);
        
        if(gun != null)
        try {
            BufferedImage img =ImageIO.read(new File(gun.imgpath));
            gg.drawImage(img, x+50-img.getWidth()/2, y+50-img.getHeight()/2,null);
        } catch (Exception e) {
           System.out.println("no gun texture (inv)");
        }
    }
    public void copyslot(Gunslot g){
        this.Item = g.Item;
        this.imgPath = g.imgPath;
        this.gun = g.gun;
        //this.x = g.x;
        //this.y = g.y;
    }
}
