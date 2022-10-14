import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class Box {
    private Random r = new Random();
    public BufferedImage img;
    public String imgpath="C:\\Users\\hille\\Documents\\GitHub\\DDU\\Game2D\\assets\\BOx.png";
    public int x,y;
    public boolean isalive;

    public Box(int x, int y){
        this.x = x*5;
        this.y = y*5;
        this.isalive = true;

        try {
            this.img = ImageIO.read(new File(imgpath));
        } catch (Exception e) {
           System.out.println("cant load box texture");
           System.exit(1);
        }

    }

    public void update(Weapon gun,Player p,Inventory inven){
        if(isalive) {
            for (int i=0;i<=gun.FB.Particles.size()-1;i++) {
                if(gun.FB.Particles.get(i).hitBox(new PVector(x/5,y/5), new PVector(x/5+img.getWidth()/5,y/5+img.getHeight()/5))){ 
                    if(isalive){
                        gun.FB.Particles.remove(gun.FB.Particles.get(i));
                        droploot();
                        isalive=false;
                    }
                }
            }
        } else{
            if(lootdropped){
                if(p.hitBox(new PVector(x/5,y/5), new PVector(x/5+20,y/5+20))){
                    inven.addtoinventory(itemdropped);
                    lootdropped=false;
                }
            }
        }
    }

    public void draw(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        gg.scale(0.2, 0.2);
        if(isalive)
        gg.drawImage(img, x, y,null);

        if(!isalive && lootdropped){
            inventoryslot in = new inventoryslot(itemdropped, x, y);
            try {
                if(in.imgPath==null){
                    in.drawslot(g);
                }
                BufferedImage lootimg = ImageIO.read(new File(in.imgPath));
                if(itemdropped<20){
                gg.scale(5, 5);
                gg.drawImage(lootimg, x/5, y/5,null);
            } else{
                gg.scale(1, 1);
                gg.drawImage(lootimg, x, y,null);
            }                
            } catch (Exception e) {
                System.out.println("cant get loot texture");
                System.exit(1);
            }

        }
    }

    private boolean lootdropped=false;
    private int itemdropped;

    public void droploot(){
        //if(r.nextInt(6)>=1)
          //aa  return;
        System.out.println("loot dropped");
        int drop = r.nextInt(6);
        lootdropped = true;
            switch (drop) {
                case 0:
                    //part 1
                    int rare = getRarity();
                    switch (rare) {
                        case 1:
                            itemdropped = 2;
                            break;
                        case 2:
                            itemdropped = 5;
                            break;
                        case 3:
                            itemdropped = 8;
                            break;
                        case 4:
                            itemdropped = 11;
                            break;
                        case 5:
                            itemdropped = 14;
                            break;
                        case 6:
                            itemdropped = 17;
                            break;
                    }
                    break;
                case 1:
                rare = getRarity();
                switch (rare) {
                    case 1:
                        itemdropped = 2+1;
                        break;
                    case 2:
                        itemdropped = 5+1;
                        break;
                    case 3:
                        itemdropped = 8+1;
                        break;
                    case 4:
                        itemdropped = 11+1;
                        break;
                    case 5:
                        itemdropped = 14+1;
                        break;
                    case 6:
                        itemdropped = 17+1;
                        break;
                }
                    break;
                case 2:
                rare = getRarity();
                switch (rare) {
                    case 1:
                        itemdropped = 2+2;
                        break;
                    case 2:
                        itemdropped = 5+2;
                        break;
                    case 3:
                        itemdropped = 8+2;
                        break;
                    case 4:
                        itemdropped = 11+2;
                        break;
                    case 5:
                        itemdropped = 14+2;
                        break;
                    case 6:
                        itemdropped = 17+2;
                        break;
                }
                    break;
                case 3:
                    itemdropped =20;
                    break;
                case 4:
                itemdropped =21;
                    break;
                case 5: 
                itemdropped =22;
                    break;
                case 6:
                itemdropped =23;
                    break;
                default:
                    break;
            }

            
    }

    private int getRarity(){
        Part1 pp = new Part1();
        int rare = pp.Rarity;
        System.out.println(rare);
        return rare;
    }

}
