//cannonball blyat
import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;

import java.awt.Graphics;
import java.awt.Graphics2D;

class Display2 extends Canvas implements Runnable {

    public static final int WIDTH = 1024, HEIGHT = WIDTH / 16 * 9;
    private double UpdatesPrSec = 120;  
    private Thread thread;
    private boolean running = false;

    private spin s;
    private TestSquare2 t;
    private float rot=1;

    private float gravity=0.01f;
    private PVector wind = new PVector(0.01f, 0);


    private mover ball;



    public static void main(String[] args) {
        new Display2();
    }

    public Display2(){
        s = new spin();
        t = new TestSquare2(200, 200, 50, 50, 1, gravity);
        ball = new mover(250, 450, 100);
        ball.acceleration.add(new PVector(5, -1));
        ball.doesbounce = false;
        ball.staybounds = false;

        new Window(WIDTH,HEIGHT,"processing er lort 2",this);
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
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    public void Tick(){

        PVector friction = ball.velocity.get();
        friction.mult(-1f);
        friction.normalize();
        friction.mult(0.1f);


        rot++;
        t.mov.addforce(wind);
        t.mov.addforce(new PVector(0,t.gravity));
        t.mov.aAcceleration = t.mov.acceleration.x;
        t.mov.update();

        ball.addforce(wind);
        ball.addforce(friction);
        ball.addforce(new PVector(0,gravity * ball.mass));
        ball.aVelocity = 20f;
        ball.aAcceleration = -0.1f;
        ball.update();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        Graphics2D gg = (Graphics2D) g;
        //background 
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //drawgrid(g);

        s.draw(gg, Color.CYAN,rot);
        //t.draw(gg, Color.pink);

        Graphics2D barrel = (Graphics2D) g.create();
        barrel.setColor(Color.black);
        barrel.rotate(toRadians.toRad(-10));
        barrel.fillRect(20, HEIGHT-100, 150, 50);
        barrel.dispose();
        
        Graphics2D wheel = (Graphics2D) g.create();
        wheel.setColor(Color.ORANGE);
        wheel.fillOval(120, HEIGHT-100, 50,50);
        wheel.dispose();

        Graphics2D cball = (Graphics2D) g.create();
        cball.setColor(Color.red);
        cball.translate(ball.location.x, ball.location.y);
        cball.rotate(toRadians.toRad(ball.angle));
        cball.fillRect(0-15, 0-15, 30,30);
        cball.dispose();

        gg.dispose();
        g.dispose();
        bs.show();
    }

    public void drawgrid(Graphics g){
        for(int i =0;i<WIDTH;i+=10){
            g.setColor(Color.darkGray);
            if(i % 100 ==0)
                g.setColor(Color.red);
            g.drawLine(i,0,i,HEIGHT);
        }
        for(int j =0;j<HEIGHT;j+=10){
            g.setColor(Color.darkGray);
            if(j % 100 ==0)
                g.setColor(Color.red);
            g.drawLine(0,j,WIDTH,j); 
        }
    }
}