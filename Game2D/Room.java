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
    public void UpdateRoom(Player p){
        if(prevdoor !=3 && p.mov.location.x>=Display.WIDTH-95 && p.mov.location.y == Display.HEIGHT/2 ){
            Right++;
            prevdoor=1;
            newRoom();
            p.mov.location = new PVector( 15,p.mov.location.y);
        }
        if(prevdoor !=2 && p.mov.location.y<=15 && p.mov.location.x == Display.WIDTH/2){
            prevdoor=4;
            newRoom();
            p.mov.location = new PVector( p.mov.location.x,Display.HEIGHT-15);
        }
        if(prevdoor !=1 && p.mov.location.x<20 && p.mov.location.y == Display.HEIGHT/2){
            Right--;
            prevdoor=3;
            newRoom();
            p.mov.location = new PVector( Display.WIDTH-100,p.mov.location.y);
        }
        if(prevdoor !=4 && p.mov.location.y>Display.HEIGHT-124 && p.mov.location.x == Display.WIDTH/2){
            prevdoor=2;
            newRoom();
            p.mov.location = new PVector( p.mov.location.x,10);
        }
    }
    public void newRoom(){
        System.out.println("blyat");
    }

    public void drawRoom(Graphics g){
        if(Right==5){
        Graphics2D gg = (Graphics2D) g.create();
        gg.setColor(Color.blue);
        gg.fillRect(1, 1, 100, 100);
        }

    }
    
}
