import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Room {
    private Random r;
    private int Right;
    public int prevdoor;
    Room(){
        Right=0;
        r = new Random();
        prevdoor=0;
    }
    public void UpdateRoom(Player p,Weapon gun){
        if(b!=null)
        b.update();

        if(prevdoor !=3 && p.hitBox(new PVector(Display.WIDTH-37,Display.HEIGHT/2),
        new PVector(Display.WIDTH-27,Display.HEIGHT/2+20))){
            Right++;
            prevdoor=1;
            newRoom(gun);
            p.mov.location = new PVector( 15,p.mov.location.y);
        }else if(prevdoor !=2 && p.hitBox(new PVector(Display.WIDTH/2,11),
        new PVector(Display.WIDTH/2+20,21))){
            prevdoor=4;
            newRoom(gun);
            p.mov.location = new PVector( p.mov.location.x,Display.HEIGHT-15);
        } else if(prevdoor !=1 && p.hitBox(new PVector(10,Display.HEIGHT/2),
        new PVector(20,Display.HEIGHT/2+20))){
            Right--;
            prevdoor=3;
            newRoom(gun);
            p.mov.location = new PVector( Display.WIDTH-100,p.mov.location.y);
        }else if(prevdoor !=4 && p.hitBox(new PVector(Display.WIDTH/2,Display.HEIGHT-60),
        new PVector(Display.WIDTH/2+20,Display.HEIGHT-50))){
            prevdoor=2;
            newRoom(gun);
            p.mov.location = new PVector( p.mov.location.x,10);
        }
    }
    Barrel b;
    public void newRoom(Weapon gun){
       b=new Barrel(0, 0);
       b.setx(500);
       b.sety(500);
        gun.FB.resetBullets();
        System.out.println("new room");
        
    }

    public void drawRoom(Graphics g){
        if(b!=null)
        b.draw(g);

        if(Right==5){
        Graphics2D gg = (Graphics2D) g.create();
        gg.setColor(Color.blue);
        gg.fillRect(500, 400, 100, 100);
        }
    } 
    public void drawdoors(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        gg.setColor(Color.orange);

        if(prevdoor !=3){
            gg.fillRect(Display.WIDTH-37, Display.HEIGHT/2, 10, 20);
        }
        if(prevdoor !=2 ){
            gg.fillRect(Display.WIDTH/2, 11, 20, 10);
        } 
        if(prevdoor !=1){
            gg.fillRect(10, Display.HEIGHT/2, 10,20);
        }
        if(prevdoor !=4){
            gg.fillRect(Display.WIDTH/2, Display.HEIGHT-60, 20, 10);
        }
            gg.dispose();
    }
}
