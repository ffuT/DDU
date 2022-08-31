import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;

public class Player {
    private mover mov;
    private Controller con;

    Player(){
        mov = new mover(15, Display.HEIGHT/2, 10);
        con = new Controller();
    }
    public void draw(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        gg.setColor(Color.green);
        gg.translate(Display.WIDTH/2, Display.HEIGHT/2);
        gg.fillRect(0, 0, 32, 32);

        gg.dispose();
    }
    public void update(){
        mov.update();
        mov.velocity.x = con.xmove;
        mov.velocity.y= con.ymove;
    }
}
