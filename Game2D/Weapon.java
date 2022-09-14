import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;

public class Weapon {
    public float DMG,FireRate,Spread,BulletAmount,BulletSpeed,Knockback,Special;
    private BufferedImage img;

    public String imgpath="";
    private Bullet b;
    public FireingBullet FB;
    public boolean shooting;
    public boolean rare;

    public Weapon(Part1 p1, Part2 p2, Part3 p3, Player p){
        shooting=false;

        if(p1.Rarity >= 6 || p2.Rarity >= 6 || p3.Rarity >= 6){
            this.rare=true;
        }

        //part1
        this.Special = p1.Special;
        this.DMG = p1.DMG;

        //part2
        this.FireRate = p2.FireRate;
        this.Knockback = p2.Knockback;

        //part3
        this.BulletAmount = p3.BulletAmount;
        this.Spread = p3.Spread;
        this.BulletSpeed = p3.BulletSpeed;

        FB = new FireingBullet(500, new PVector(p.mov.location.getx()+p.width/2, p.mov.location.gety()+p.height/2+10),this);

        getSprite();
    }
    public Weapon(Part1 p1, Part2 p2, Part3 p3){
        shooting=false;

        if(p1.Rarity >= 6 || p2.Rarity >= 6 || p3.Rarity >= 6){
            this.rare=true;
        }
        //part1
        this.Special = p1.Special;
        this.DMG = p1.DMG;

        //part2
        this.FireRate = p2.FireRate;
        this.Knockback = p2.Knockback;

        //part3
        this.BulletAmount = p3.BulletAmount;
        this.Spread = p3.Spread;
        this.BulletSpeed = p3.BulletSpeed;
    
        getSprite();
    }
    public void getSprite(){
        Random r = new Random();
        if(!rare){
            try {
                int temp = r.nextInt(7);
                
                switch (temp) {
                    case 1:
                        imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\Normalgun1.png";
                        img = ImageIO.read(new File(imgpath));                
                        break;

                    case 2:
                        imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\Normalgun2.png";
                        img = ImageIO.read(new File(imgpath));  
                        break;

                    case 3:
                        imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\Normalgun3.png";
                        img = ImageIO.read(new File(imgpath));  
                        break;
                    case 4:
                        imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\Normalgun4.png";
                        img = ImageIO.read(new File(imgpath));  
                        break;
                    case 5:
                        imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\Normalgun5.png";
                        img = ImageIO.read(new File(imgpath));  
                        break;
                    case 6:
                        imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\Normalgun6.png";
                        img = ImageIO.read(new File(imgpath));  
                        break;
                    case 7:
                        imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\Normalgun7.png";
                        img = ImageIO.read(new File(imgpath));  
                        break;
                    default:
                        imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\gun1.png";
                        img = ImageIO.read(new File(imgpath));
                        break;
                }
            } catch (Exception e) {
                System.out.println("cant get texture");
                System.exit(1);
            }
        }else {
            try {
                int temp = r.nextInt(2);
                switch (temp) {
                    case 0:
                        imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\Ultragun1.png";
                        img = ImageIO.read(new File(imgpath));                
                        break;

                    case 1:
                        imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\Ultragun2.png";
                        img = ImageIO.read(new File(imgpath));  
                        break;

                    case 2:
                        imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\Ultragun3.png";
                        img = ImageIO.read(new File(imgpath));  
                        break;
                    }
            } catch (Exception e) {
                System.out.println("cant get texture");
                System.exit(1);
            }
        }
    }

    public void drawgun(Graphics g, Player p,float angle){
        Graphics2D gg = (Graphics2D) g.create();
        gg.translate(p.mov.location.getx()+p.width/2, p.mov.location.gety()+p.height/2+10);
        gg.rotate(angle);
        gg.drawImage(img, 0,0, null);

        if(b!=null && !b.isdead())
            b.draw(g);

        FB.draw(g);
        gg.dispose();
    }
    public void update(PVector mouse,Player p){
        if(FB == null)
            FB = new FireingBullet(500, new PVector(p.mov.location.getx()+p.width/2, p.mov.location.gety()+p.height/2+10),this);

        FB.update(this,mouse,p);
        if(b!=null && !b.isdead())
        b.update();
    }
    public void shot(Player p,PVector mouse){
    }

}
