import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class monster {
    private int type;
    public int x,y;
    public BufferedImage img;
    public String path = "C:\\Users\\Tuff\\Documents\\GitHub\\DDU\\Game2D\\assets\\"; 

    public monster(int type, int x, int y){
        this.type = type;
        this.x = x*10;
        this.y = y*10;

        switch (type) {
            case 1:
                path += "Monner_health.png";
                break;
        
            default:
            System.out.println("bad type");
                break;
        }

        try {
            img = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println("monster textures wont load");
            System.exit(1);
        }
    }

    public void draw(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        gg.scale(0.1, 0.1);
        gg.drawImage(img,x,y,null);
    }
}
