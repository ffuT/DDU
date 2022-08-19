//car driving
import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Graphics2D;

class Display3 extends Canvas implements Runnable {

    public static final int WIDTH = 1024, HEIGHT = WIDTH / 16 * 9;
    private double UpdatesPrSec = 60;  
    private Thread thread;
    private boolean running = false;

    private mover car;

    public static void main(String[] args) {
        new Display3();
    }

    public Display3(){
        car=new mover(WIDTH/2, HEIGHT/2, 200);
        car.acceleration.x = 1;
    
        new Window(WIDTH,HEIGHT,"processing er lort 3",this);
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
        car.update();
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
        Graphics2D carg = (Graphics2D) g.create();
        carg.translate(car.location.getx(), car.location.gety());
        carg.setColor(Color.red);

        if(car.velocity.x > 0){
            carg.scale(-1, 1);
        } else {
            carg.scale(1, 1);
        }

        carg.fillRect(0, 0, 150,50);
        carg.fillRect(50, -50, 75,50);
        carg.dispose();

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