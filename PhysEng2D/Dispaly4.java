import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;

class Display4 extends Canvas implements Runnable {

    public static final int WIDTH = 1024, HEIGHT = WIDTH / 16 * 9;
    private double UpdatesPrSec = 60;  
    private Thread thread;
    private boolean running = false;
    private int FPS;

    public static void main(String[] args) {
        new Display4();
    }

    public Display4(){

        new Window(WIDTH,HEIGHT,"partikler!",this);
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





        //stats top left
        g.setColor(Color.black);
        g.drawString("FPS: " + FPS, 5, 15);

        g.dispose();
        bs.show();
    }

}