import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;

class Display extends Canvas implements Runnable {

    public static final int WIDTH = 1024, HEIGHT = WIDTH / 16 * 9;
    private double UpdatesPrSec = 240;  
    private Thread thread;
    private boolean running = false;
    private int FPS;
    private PVector location,velocity,acceleration;
    private PVector gravity;
    private PVector wind;

    private TestSquare k,h,j;


    public static void main(String[] args) {
        new Display();
    }

    public Display(){
        gravity = new PVector(0, 0.05f);
        wind = new PVector(0.005f, 0);

        location = new PVector(20, 20);
        velocity = new PVector(1,1);
        acceleration = new PVector(0, 0.05f);

        k = new TestSquare(200, 200, 50, 50,10,gravity.y);
        h = new TestSquare(20, 200, 50, 50,1,gravity.y);
        j = new TestSquare(20, 20, 50, 50,50,gravity.y);
        new Window(WIDTH,HEIGHT,"processing er lort",this);
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
        //rect made as a new object
        PVector friction = k.mov.velocity.get();

        friction.mult(-1f);
        friction.normalize();
        friction.mult(0.01f);

        k.mov.addforce(new PVector(0, k.gravity));
        k.mov.addforce(wind);
        k.mov.addforce(friction);
        k.mov.update();

        h.mov.addforce(new PVector(0, h.gravity));
        h.mov.addforce(wind);
        h.mov.addforce(friction);
        h.mov.update();

        j.mov.addforce(new PVector(0, j.gravity));
        j.mov.addforce(wind);
        j.mov.addforce(friction);
        j.mov.update();

        //local rect
        velocity.add(acceleration);
        location.add(velocity);
        bounce();
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

        //switch color before draw (make function isnted of drawing in render)
        //local object make testSquare for more
        g.setColor(Color.black);
        g.fillRect(location.getx(), location.gety(), 50, 50);
        
        //test with an object (TestSquare seperate class that use mover for local values)
        k.draw(g, Color.pink);
        h.draw(g, Color.BLUE);
        j.draw(g, Color.green);


        //stats top left
        g.setColor(Color.black);
        g.drawString("FPS: " + FPS, 5, 15);
        
        /*  these stats are only for local rect (black)
        String velY= String.format("VelY: %.2f", velocity.y);
        String velX= String.format("VelX: %.2f", velocity.x);
        g.drawString(velX, 5, 30);
        g.drawString(velY , 5, 45);
        */

        g.dispose();
        bs.show();
    }

    //local movement
    public void bounce(){
        //bounce on wall x
        if(location.x > WIDTH-65 || location.x < 0){
            velocity.x *= -1;
        }
        //bounce on wall y
        if(location.y > HEIGHT-85 || location.y < 0){
            velocity.y *=-1;
        }
    }
}