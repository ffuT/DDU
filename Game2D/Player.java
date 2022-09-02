import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Player {
    public mover mov;
    public float hitpoints;
    public float MSpeed;
    public int width = 61, height = 70;

    Player(float HP, Float movementspeed){
        mov = new mover(50, Display.HEIGHT/2, 10);
        this.hitpoints = HP;
        this.MSpeed = movementspeed;
        mov.objh = height;
        mov.objw = width;
    }
    public void draw(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        gg.setColor(Color.green);
        //gg.translate(Display.WIDTH/2, Display.HEIGHT/2);
        //gg.fillRect(mov.location.getx(), mov.location.gety(), width, height);
        //C:\\Users\\Tuff\\Documents\\GitHub\\DDU\\Game2D\\assets\\'
        //C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\
        try {
            String imgpath = "C:\\Users\\Tuff\\Documents\\GitHub\\DDU\\Game2D\\assets\\SPRITE_SOLO_MAN.png";
            BufferedImage Topimg = ImageIO.read(new File(imgpath));
            String imgpath2 = "C:\\Users\\Tuff\\Documents\\GitHub\\DDU\\Game2D\\assets\\SPRITE_MANS_SKATEBOARD.png";
            BufferedImage Botimg = ImageIO.read(new File(imgpath2));
            if(mov.velocity.x<0){
                gg.scale(-1, 1);
                gg.drawImage(Topimg, -mov.location.getx()-width, mov.location.gety(), null);
                gg.drawImage(Botimg, -mov.location.getx()-width, mov.location.gety()+60, null);
            } else{
            gg.drawImage(Topimg, mov.location.getx(), mov.location.gety(), null);
            gg.drawImage(Botimg, mov.location.getx(), mov.location.gety()+60, null);
            }

        } catch (Exception e) {
           System.out.println("files not found");
           System.exit(1);
        }

        gg.dispose();
    }
    public void update(){
        if(hitpoints <= 0){
            System.out.println("dead");
            System.exit(0);
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
    public boolean hitBox(PVector point, PVector point2){
        if(point.x >= mov.location.x && point.x <= mov.location.x+width &&
        point.y >= mov.location.y && point.y <= mov.location.y+height){
            return true;
        } else if(point2.x >= mov.location.x && point2.x <= mov.location.x+width &&
        point2.y >= mov.location.y && point2.y <= mov.location.y+height){
            return true;
        }else{
            return false;
        }
    }
}
