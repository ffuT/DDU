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
    private Part1 p1 = new Part1();
    private Part2 p2 = new Part2();
    private Part3 p3 = new Part3();
    private Weapon gun = new Weapon(p1, p2, p3);

    private InputHandler input;
    private Game game = new Game();
    private PVector move = new PVector();
    private float StartHP=100;

    public static void main(String[] args) {
        new Display();
    }

    public Display(){
        //setup values here
        room = new Room();
        p = new Player(StartHP,5f);

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
    public void Tick(){
        //game updates here
        room.UpdateRoom(p);

        //movement
        game.tick(input.key);
        p.mov.velocity.mult(0f);
        move.y = game.control.ymove;
        move.x = game.control.xmove;
        p.mov.velocity.add(move);
        p.update();

        if(p.hitBox(new PVector(500,500))){
            p.hitpoints--;
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();    
               
        try{
            Graphics2D gg = (Graphics2D) g.create();
            String imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\Floor.png";
            BufferedImage img = ImageIO.read(new File(imgpath));
            gg.drawImage(img, 0, 0, null);
            
            imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\HEALTHVBAR OF DOOM.png";
            img = ImageIO.read(new File(imgpath));
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

        //draw objects here
        p.draw(g);
        room.drawRoom(g);
        gun.drawgun(g, p);


        /* guide lines
        g.setColor(Color.gray);
        for(int i = 1; i<=WIDTH;i+=10){
            g.drawLine(i, 0, i, HEIGHT);
        }
        for(int i = 1; i<=WIDTH;i+=10){
            g.drawLine(0, i, WIDTH, i);
        }        
        */


        g.setColor(Color.pink);
        g.fillRect(500, 500, 10, 10);
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

        g.dispose();
        bs.show();
    }
}