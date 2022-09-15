import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Room {
    private Random r;
    private int Right;
    public int prevdoor;
    Room(){
        Right=4;
        r = new Random();
        prevdoor=0;
    }
    public void UpdateRoom(Player p,Weapon gun){
        if(bb!=null){
            bb.update(p, gun);
            if(bb.isalive)
                return;
        }

        int deadcount=0;
        if(e!=null)
        for (Enemy e : e) {
            e.update(gun,p);
            if(!e.isalive){
                deadcount++;
            }
        }

        if(bo!=null)
        for (Box bo : bo) {
            bo.update(gun);
        }
           

        if(b!=null)
        for (Barrel b : b) {
            b.update(gun);
            if(b.explode &&  b.hitBoxSPH(p.mov.location, new PVector(p.mov.location.x+p.width,p.mov.location.y+p.height))){
                p.hitpoints-=1;
            }
            for (Enemy e : e) {
                if(b.explode &&  b.hitBoxSPH(new PVector(e.x/4,e.y/4), new PVector(e.x/4+e.img.getWidth()/4,e.y/4+e.img.getHeight()/4)))
                    e.health-=3;
            }   

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
    Bossman1 bb;
    Box[] bo;
    boolean bossmandeafeated=false;
    public void newRoom(Weapon gun){     
        switch (Right) {
            case 5:
            if(bossmandeafeated)
                return;    
            
                bb = new Bossman1(0,0,200);
                bb.x = Display.WIDTH/2-bb.img.getWidth()/2;
                bb.y = Display.HEIGHT/2-bb.img.getHeight()/2;   
                break;
        
            default:

                int boxamount = r.nextInt(3)+2;
                bo = new Box[boxamount];
                for(int i = 0; i <= boxamount-1; i++){
                    bo[i] = new Box(r.nextInt(Display.WIDTH-100)+10,r.nextInt(Display.HEIGHT-100)+10);
                }

                //bo=new Box(r.nextInt(Display.WIDTH)-100, r.nextInt(Display.HEIGHT)-100);

                //makes random number of barrels
                int barrelamount = r.nextInt(4)+2;
                b = new Barrel[barrelamount];
                for(int i = 0; i <= barrelamount-1; i++){
                    b[i] = new Barrel(r.nextInt(Display.WIDTH-100)+10,r.nextInt(Display.HEIGHT-100)+10);
                }
                //make random number of enemies
                int enemyamount = r.nextInt(4)+2+Right/4;
                e = new Enemy[enemyamount];
                for(int i = 0; i <= enemyamount-1; i++){
                    if(r.nextBoolean()){
                        e[i] = new Enemy1(r.nextInt(Display.WIDTH-100),r.nextInt(Display.HEIGHT-100),5+Right*2); 
                    } else{
                        e[i] = new Enemy2(r.nextInt(Display.WIDTH-100),r.nextInt(Display.HEIGHT-100),10+Right*2); 
                    }
                }
                break;
        }

        //TODO make a switch case for boss rooms dependen on int "Right"


        //TODO make a random amount of boxes to load on screen
       


        //resets bullets on screen
        gun.FB.resetBullets();
        System.out.println("new room");
    }

    public void drawRoom(Graphics g){
        if(bb!=null)
            bb.draw(g);

        if(bo!=null)
        for (Box bo : bo) {
                bo.draw(g);
        }

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
