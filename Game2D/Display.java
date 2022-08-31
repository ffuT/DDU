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

    private Player p;


    private InputHandler input;
    private Game game = new Game();
    private PVector move = new PVector();


    public static void main(String[] args) {
        new Display();
    }

    public Display(){
        //setup values here
        p = new Player();

        new Window(WIDTH,HEIGHT,"2D Roguelike topdown",this);

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

        //movement
        game.tick(input.key);
        move.y = game.control.ymove;
        move.x = game.control.xmove;
        p.mov.velocity.add(move);
        System.out.println(move.x +" "+ move.y);
        p.update();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(2);
            return;
        }

     
        Graphics g = bs.getDrawGraphics();
        //background 
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, WIDTH, HEIGHT);

    
               
        try{
            Graphics2D gg = (Graphics2D) g.create();
            String imgpath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\Floor.png";
            BufferedImage img = ImageIO.read(new File(imgpath));
            gg.drawImage(img, 0, 0, null);
            gg.dispose();
            } catch(IOException e){}

            p.draw(g);

        //Walls
        g.setColor(Color.black);
        g.fillRect(0, 0, 10, HEIGHT);
        g.fillRect(WIDTH-26,0, 10, HEIGHT);
        g.fillRect(0, 0, WIDTH, 10);
        g.fillRect(0, HEIGHT-49, WIDTH, 10);


        //draw objects here

       
        //stats top left
        g.setColor(Color.white);
        g.drawString("FPS: " + FPS, 10, 25);

        g.dispose();
        bs.show();
    }
}