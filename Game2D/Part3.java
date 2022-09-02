import java.util.Random;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Part3 {
    Random r;
    public int Rarity;
    float Spread;
    float BulletSpeed;

    public Part3(){
        r = new Random();
        int temp =r.nextInt(100);
        //30 % chance for common
        if( temp <= 30){
            Rarity=1;
        //25% chance for uncommon
        } else if(temp <= 55){
            Rarity = 2;
        } else if(temp <= 75){
            Rarity = 3;
        } else if(temp <= 90){
            Rarity = 4;
        } else if(temp <= 99){
            Rarity = 5;
        } else{
            Rarity = 6;
        }
        getstats();
    }
    public void drawpart(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();


        gg.dispose();
    }
    private void getstats(){
        switch (Rarity) {
            case 1:
                this.Spread = 10;
                this.BulletSpeed = 10;
                break;
            case 2:
                this.Spread = 10 - r.nextFloat();
                this.BulletSpeed = 10 + r.nextFloat();
                break;
            case 3:
                this.Spread = 10 - r.nextFloat()*2;
                this.BulletSpeed = 10 + r.nextFloat()*2;
                break;
            case 4:
                this.Spread = 9 - r.nextFloat()*3;
                this.BulletSpeed = 10 + r.nextFloat()*3;
                break;
            case 5:
                this.Spread = 9 - r.nextFloat()*4;
                this.BulletSpeed = 10 + r.nextFloat()*4;
                break;
            case 6:
                this.Spread = 9 - r.nextFloat()*10;
                this.BulletSpeed = 10 + r.nextFloat()*10;
                break;
            default:
                break;
        }
    }
}
