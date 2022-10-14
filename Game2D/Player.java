import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Player {
    public mover mov;
    public float hitpoints;
    public float maxhp;
    public float MSpeed;
    public int width = 61, height = 70;

    private BufferedImage Topimg,Botimg;
    private String imgpath = "C:\\Users\\hille\\Documents\\GitHub\\DDU\\Game2D\\assets\\SPRITE_SOLO_MAN.png";
    private String imgpath2 = "C:\\Users\\hille\\Documents\\GitHub\\DDU\\Game2D\\assets\\SPRITE_MANS_SKATEBOARD.png";

    Player(float HP, Float movementspeed){
        mov = new mover(50, Display.HEIGHT/2, 10);
        this.hitpoints = HP;
        this.maxhp = HP;
        this.MSpeed = movementspeed;
        mov.objh = height;
        mov.objw = width;

        try {
            Topimg = ImageIO.read(new File(imgpath));
            Botimg = ImageIO.read(new File(imgpath2));
        } catch (Exception e) {
            System.out.println("player texture cant load");
            System.exit(1);
        }


    }
    public void draw(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        gg.setColor(Color.green);
        //gg.translate(Display.WIDTH/2, Display.HEIGHT/2);
        //gg.fillRect(mov.location.getx(), mov.location.gety(), width, height);
        if(mov.velocity.x<0){
            gg.scale(-1, 1);
            gg.drawImage(Topimg, -mov.location.getx()-width, mov.location.gety(), null);
            gg.drawImage(Botimg, -mov.location.getx()-width, mov.location.gety()+60, null);
        } else{
        gg.drawImage(Topimg, mov.location.getx(), mov.location.gety(), null);
        gg.drawImage(Botimg, mov.location.getx(), mov.location.gety()+60, null);
        }


        gg.dispose();
    }
    public void update(){
        if(hitpoints <= 0){
            System.out.println("dead");
            System.exit(0);
        }
        if(hitpoints > maxhp){
            hitpoints = maxhp;
        }

        mov.velocity.mult(MSpeed);
        mov.update();
        
    }
    public boolean hitBox(PVector Hlocation){
        if(Hlocation.x>=mov.location.x && Hlocation.x<=mov.location.x+width && Hlocation.y>=mov.location.y && Hlocation.y<=mov.location.y+height){
            return true;
        } else{
            return false;
        }
    }
    public boolean hitBox(PVector point1, PVector point2){
        //player
        float playerX1=mov.location.x, playerX2=mov.location.x+width;
        float playerY1=mov.location.y, playerY2=mov.location.y+height;
        if(playerX1 > point2.x || playerX2 < point1.x || playerY1 > point2.y || playerY2 < point1.y){
            return false;
        } 
        return true;
    }
}
