import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class Box {
    private Random r = new Random();
    public BufferedImage img;
    public String imgpath="C:\\Users\\Tuff\\Documents\\GitHub\\DDU\\Game2D\\assets\\BOx.png";
    public int x,y;
    public boolean isalive;

    public Box(int x, int y){
        this.x = x*5;
        this.y = y*5;
        this.isalive = true;

        try {
            this.img = ImageIO.read(new File(imgpath));
        } catch (Exception e) {
           System.out.println("cant load box texture");
           System.exit(1);
        }

    }

    public void update(Weapon gun){
        if(isalive) {
            for (int i=0;i<=gun.FB.Particles.size()-1;i++) {
                if(gun.FB.Particles.get(i).hitBox(new PVector(x/5,y/5), new PVector(x/5+img.getWidth()/5,y/5+img.getHeight()/5))){ 
                    if(isalive){
                        gun.FB.Particles.remove(gun.FB.Particles.get(i));
                        //TODO --> make drop loot
                        isalive=false;
                    }
                }
            }
        } else{
            if(lootdropped){
                //detect loot and add to inv
            }
        }
    }

    public void draw(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        gg.scale(0.2, 0.2);
        if(isalive)
        gg.drawImage(img, x, y,null);

        if(!isalive && lootdropped)
            return;
            //drawloot     
    }

    private boolean lootdropped=false;

    public void droploot(){
        if(r.nextInt(2)>=0)
            return;
        //100 for %
        int drop = r.nextInt(100);
        lootdropped = true;
            switch (drop) {
                case 0:
                    //part 1
                    break;
                case 1:
                    //part 2
                    break;
                case 2:
                    //part 3
                    break;
                case 3:
                    //monner 1
                    break;
                case 4:
                    //monner 2
                    break;
                case 5: 
                    //monner 3
                    break;
                case 6:
                    //monner 4
                    break;
                default:
                    break;
            }
        
    }

}
