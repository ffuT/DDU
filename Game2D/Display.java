import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class Display extends Canvas implements Runnable  {

    public static final int WIDTH = 1280, HEIGHT = WIDTH / 16 * 9;
    private double UpdatesPrSec = 60;  
    private Thread thread;
    private boolean running = false;
    private int FPS;

    public Room room;
    public Player p;
    //temporary gun
    public Weapon gun;
    public float gunangle;
    public PVector MLocation;

    private InputHandler input;
    private Game game = new Game();
    private PVector move = new PVector();
    private float StartHP=100;

    //inventory ???
    private Inventory inven;

    public static void main(String[] args) {
        new Display();
    }

    public Display(){
        //setup values here
        MLocation = new PVector();
        room = new Room();
        p = new Player(StartHP,4f);
        inven = new Inventory(p);
        inven.addtoinventory(5);
        inven.addtoinventory(12);
        inven.addtoinventory(10);
        inven.gunslot.gun=new Weapon(new Part1(6), new Part2(6), new Part3(6));
        System.out.println(inven.gunslot.gun.rare);
        gun = inven.gunslotE.gun;
        new Window(WIDTH,HEIGHT,"SPRITE MAN ADVENTURES",this);

        input = new InputHandler();
        addKeyListener(input);
        addFocusListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop() {
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //game loop 
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = UpdatesPrSec;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        requestFocus();
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                delta--;
                Tick();
            }
            if(running)
                render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                FPS = frames;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    public float gametime=0;
    public void Tick(){
        game.tick(input.key);

        if(game.control.inventory){
            inven.update();
            PVector mouse = new PVector(input.mouseX,input.mouseY);
            inven.Selected(mouse,input.mclick,input.mdrag,input.mouseReleased);

            input.mclick=false;
            //stops game when in inventory
            return;
        }
        //game updates here
        gametime++;
        room.UpdateRoom(p,gun);

        gun = inven.gunslotE.gun;
        //gun movement
        gunangle = (float) Math.atan2(MLocation.y-p.mov.location.y-p.height/2,MLocation.x-p.mov.location.x-p.width/2);
        
        //mouseclicks
        if(input.mouseClicked){
            gun.shot(p,new PVector(MLocation.x-p.mov.location.x-p.width/2,MLocation.y-p.mov.location.y-p.height/2));
            gun.shooting = true;
        }        
        gun.update(new PVector(MLocation.x-p.mov.location.x-p.width/2,MLocation.y-p.mov.location.y-p.height/2),p);

        if(input.mouseReleased){
            gun.shooting=false;
        }
        
        input.mouseClicked=false;
        input.mouseReleased=false;

        //input.mouseClicked=false;
        //input.mouseReleased=false;

        //movement
        p.mov.velocity.mult(0f);
        move.y = game.control.ymove;
        move.x = game.control.xmove;
        p.mov.velocity.add(move);
        p.update();

        if(p.hitBox(new PVector(25,72),new PVector(271,107))){
            p.hitpoints-=0.01f;
        }
    }

    private void render(){
        MLocation = new PVector(input.mouseX,input.mouseY);
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(2);
            return;
        }
        Graphics g = bs.getDrawGraphics(); 
        
        //renders inventory
        if(game.control.inventory){
            Graphics2D gg = (Graphics2D) g.create();
            gg.setColor(Color.gray);
            
            gg.fillRect(0, 0, WIDTH, HEIGHT);
            //draw inventory object here
            inven.drawInventory(g,MLocation);
            DrawHealthbar(g);

            gg.setColor(Color.black);
            gg.drawString("inventory slots", 100, HEIGHT/4*3-5);
            gg.drawString("Crafting table", WIDTH/2+100, 95);
            gg.drawString("weapon slots", 800, HEIGHT/4*3-5);
            gg.drawString("equipped weapon", 1100, HEIGHT/4*3-5);
            gg.dispose();
        }else {
            //renders game when not in inventory
            try {
                Graphics2D gg = (Graphics2D) g.create();

                String imgpath = "C:\\Users\\Tuff\\Documents\\GitHub\\DDU\\Game2D\\assets\\Floor2.png";
                BufferedImage img = ImageIO.read(new File(imgpath));
                gg.drawImage(img, 0, 0, null);
                    
            } catch (Exception e) {
                System.out.println("cant load txture");
            }
        
            DrawHealthbar(g);

        //draw objects here
        room.drawRoom(g); 
        p.draw(g);
        gun.drawgun(g, p, gunangle);
        room.drawdoors(g);

        }

        //guide lines every 10pix
        /* 
        g.setColor(Color.gray);
        for(int i = 1; i<=WIDTH;i+=10){
            g.drawLine(i, 0, i, HEIGHT);
        }
        for(int i = 1; i<=WIDTH;i+=10){
            g.drawLine(0, i, WIDTH, i);
        }        
        */


            //Walls
            g.setColor(Color.black);
            //left
            g.fillRect(0, 0, 10, HEIGHT);
            //right
            g.fillRect(WIDTH-26,0, 10, HEIGHT);
            //top
            g.fillRect(0, 0, WIDTH, 10);
            //bott
            g.fillRect(0, HEIGHT-49, WIDTH, 10);
        
       
        //stats top left
        g.setColor(Color.white);
        g.drawString("FPS: " + FPS, 15, 25);
        //player stats
        g.drawString("VelX: " + p.mov.velocity.x , 15, 40);
        g.drawString("VelY: " + p.mov.velocity.y , 15, 55);
        g.drawString("HP: " + p.hitpoints , 15, 70);
        g.drawString("last door: " + room.prevdoor, 15, 85);
        String gametime2 = String.format( "%.2f", gametime/60);
        g.drawString("gametime " + gametime2, 15, 100);

       // g.drawString("location x,y: " + p.mov.location.getx() + " " + p.mov.location.gety(), 15, 100);
       // g.drawString("location x2,y2: " + (p.mov.location.getx()+p.width) + " " + (p.mov.location.gety()+p.height), 15, 115);

        g.drawString("Gun DMG: " + gun.DMG, 15, 100+50);
        g.drawString("Gun Spread: " + gun.Spread, 15, 115+50);
        g.drawString("Gun firerate: " + gun.FireRate, 15, 130+50);
        g.drawString("Gun Bulletspeed: " + gun.BulletSpeed, 15, 145+50);
        g.drawString("Gun fireing: " + gun.FB.fireing, 15, 160+50);
        g.drawString("Gun Bulletamount: " + gun.BulletAmount, 15, 175+50);

        g.dispose();
        bs.show();
    }
    public void DrawHealthbar(Graphics g){
        try{
            Graphics2D gg = (Graphics2D) g.create();
            
            String imgpath = "C:\\Users\\Tuff\\Documents\\GitHub\\DDU\\Game2D\\assets\\HEALTHVBAR OF DOOM.png";
            BufferedImage img = ImageIO.read(new File(imgpath));
            gg.setColor(Color.red);
            float HPWidth = (p.hitpoints/StartHP) * 246;
            gg.fillRect(25, 72, (int) HPWidth, 35);
            gg.drawImage(img, 20, 20, null);

            gg.dispose();
            } catch(IOException e){
                //background 
                g.setColor(Color.lightGray);
                g.fillRect(0, 0, WIDTH, HEIGHT);
            }
    }

}