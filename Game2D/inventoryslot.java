import java.awt.Graphics;
import java.awt.Graphics2D;


public class inventoryslot {
    public int Item=0,x,y;


    public inventoryslot(int n,int x,int y){
        this.Item = n;
        this.x=x;
        this.y=y;
    }

    public void drawslot(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        gg.drawRect(x, y, 100, 100);
        
        String imgPath = "C:\\Users\\hille\\OneDrive\\Documents\\gym 3g\\Digital Design\\Code\\DDU\\Game2D\\assets\\";

        switch (Item) {
            case 0:
                break;
            case 1:

                break;
            
            default:
                break;
        }

        

        gg.dispose();
    }
}
