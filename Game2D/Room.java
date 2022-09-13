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
        int deadcount=0;
        if(e!=null)
        for (Enemy e : e) {
           e.update(gun);
            if(!e.isalive){
                deadcount++;
            }
        }

        if(b!=null)
        for (Barrel b : b) {
            b.update(gun);          
        }
        if(e==null){
            e=new Enemy[0];
        }
        if(deadcount>e.length-1 && prevdoor !=3 && p.hitBox(new PVector(Display.WIDTH-37,Display.HEIGHT/2),
        new PVector(Display.WIDTH-27,Display.HEIGHT/2+20))){
            Right++;
            prevdoor=1;
            newRoom(gun);
            p.mov.location = new PVector( 15,p.mov.location.y);
        }else if(deadcount>e.length-1 &&prevdoor !=2 && p.hitBox(new PVector(Display.WIDTH/2,11),
        new PVector(Display.WIDTH/2+20,21))){
            prevdoor=4;
            newRoom(gun);
            p.mov.location = new PVector( p.mov.location.x,Display.HEIGHT-15);
        } else if(deadcount>e.length-1 &&prevdoor !=1 && p.hitBox(new PVector(10,Display.HEIGHT/2),
        new PVector(20,Display.HEIGHT/2+20))){
            Right--;
            prevdoor=3;
            newRoom(gun);
            p.mov.location = new PVector( Display.WIDTH-100,p.mov.location.y);
        }else if(deadcount>e.length-1 &&prevdoor !=4 && p.hitBox(new PVector(Display.WIDTH/2,Display.HEIGHT-60),
        new PVector(Display.WIDTH/2+20,Display.HEIGHT-50))){
            prevdoor=2;
            newRoom(gun);
            p.mov.location = new PVector( p.mov.location.x,10);
        }
    }
    Barrel[] b;
    Enemy[] e;
    public void newRoom(Weapon gun){
        int barrelamount = r.nextInt(4)+2;
        b = new Barrel[barrelamount];
        for(int i = 0; i <= barrelamount-1; i++){
            b[i] = new Barrel(r.nextInt(Display.WIDTH-100),r.nextInt(Display.HEIGHT-100));
        }

        int enemyamount = r.nextInt(4)+2;
        e = new Enemy1[enemyamount];
        for(int i = 0; i <= enemyamount-1; i++){
            e[i] = new Enemy1(r.nextInt(Display.WIDTH-100),r.nextInt(Display.HEIGHT-100),5+Right*2);
        }

        gun.FB.resetBullets();
        System.out.println("new room");
    }

    public void drawRoom(Graphics g){
        //draws all barrels
        if(b!=null)
        for (Barrel b : b) {
            b.draw(g);
        }
        //should draw all enemies
        if(e!=null)
            for (Enemy e : e) {
                e.draw(g);
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
