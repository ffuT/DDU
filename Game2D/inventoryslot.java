import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class inventoryslot {
    public int Item=0,x,y;
    public String imgPath;

    public Part1 p1;
    public Part2 p2;
    public Part3 p3;

    public inventoryslot(int n,int x,int y){
        this.p1 = null;
        this.p2 = null;
        this.p3 = null; 

        this.Item = n;
        this.x=x;
        this.y=y;
    }

    public void drawslot(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        gg.scale(1, 1);
        gg.drawRect(x, y, 100, 100);
        
        imgPath = "C:\\Users\\Tuff\\Documents\\GitHub\\DDU\\Game2D\\assets\\";
        try {
            BufferedImage img;
        switch (Item) {
            case 0:

                break;
            case 1:
            
                break;
            case 2:
                imgPath += "Common part 1.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p1==null)
                    p1 = new Part1(1);
                break;
            case 3:
                imgPath += "Common part 2.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p2==null)
                    p2 = new Part2(1);
                break;
            case 4:
                imgPath += "Common part 3.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p3==null)
                    p3 = new Part3(1);
                break;
            case 5:
                imgPath += "UNCommon part 1.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p1==null)
                    p1 = new Part1(2);
                break;
            case 6:
                imgPath += "UNCommon part 2.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p2==null)
                    p2 = new Part2(2);
                break;
            case 7:
                imgPath += "UNCommon part 3.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p3==null)
                    p3 = new Part3(2);
                break;
            case 8:
                imgPath += "RARE part 1.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p1==null)
                    p1 = new Part1(3);
                break;
            case 9:
                imgPath += "RARE part 2.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p2==null)
                    p2 = new Part2(3);
                break;
            case 10:
                imgPath += "RARE part 3.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p3==null)
                    p3 = new Part3(3);
                break;
            case 11:
                imgPath += "EPIC part 1.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p1==null)
                    p1 = new Part1(4);
                break;
            case 12:
                imgPath += "EPIC part 2.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p2==null)
                    p2 = new Part2(4);
                break;
            case 13:
                imgPath += "EPIC part 3.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p3==null)
                    p3 = new Part3(4);
                break;
            case 14:
                imgPath += "Legendary part 1.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p1==null)
                    p1 = new Part1(5);
                break;
            case 15:
                imgPath += "Legendary part 2.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p2==null)
                    p2 = new Part2(5);
                break;
            case 16:
                imgPath += "Legendary part 3.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p3==null)
                    p3 = new Part3(5);
                break;
            case 17:
                imgPath += "ULTRA part 1.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p1==null)
                    p1 = new Part1(6);
                break;
            case 18:
                imgPath += "ULTRA part 2.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p2==null)
                    p2 = new Part2(6);
                break;
            case 19:
                imgPath += "ULTRA part 3.png";
                img = ImageIO.read(new File(imgPath));
                gg.scale(2, 2);
                gg.drawImage(img, x/2+15, y/2+20,null);
                if(p3==null)
                    p3 = new Part3(6);
                break;
            default:
                break;
        }
        gg.dispose();

        } catch (Exception e) {
        System.out.println("inventory textures cant load");
        System.exit(1);
        }
        
        gg.dispose();
    }
    public void copyslot(inventoryslot slot2){
        this.Item = slot2.Item;
        this.imgPath = slot2.imgPath;
        this.p1 = slot2.p1;
        this.p2 = slot2.p2;
        this.p3 = slot2.p3;
    }
}
