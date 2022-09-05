import java.util.Random;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Part3 {
    Random r;
    public int Rarity;
    float Spread;
    float BulletSpeed;
    int BulletAmount;

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

    public Part3(int i){
        r = new Random();
        this.Rarity = i;
        getstats();
    }

    public void drawpart(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();


        gg.dispose();
    }
    private void getstats(){
        switch (Rarity) {
            case 1:
                this.Spread = 0.09f;
                this.BulletSpeed = 6;
                this.BulletAmount = 1;
                break;
            case 2:
                this.Spread = 0.07f - r.nextInt(2)/1000;
                this.BulletSpeed = 7 + r.nextFloat()*2;
                this.BulletAmount = 1;
                break;
            case 3:
                this.Spread = 0.07f - r.nextInt(6)/1000;
                this.BulletSpeed = 8 + r.nextFloat()*3;
                this.BulletAmount = r.nextInt(1)+1;
                break;
            case 4:
                this.Spread = 0.04f - r.nextInt(8)/1000;
                this.BulletSpeed = 9 + r.nextFloat()*4;
                this.BulletAmount = r.nextInt(1)+2;
                break;
            case 5:
                this.Spread = 0.04f - r.nextInt(1)/100;
                this.BulletSpeed = 10 + r.nextFloat()*5;
                this.BulletAmount = r.nextInt(1)+2;
                break;
            case 6:
                this.Spread = 0.04f - r.nextInt(25)/1000;
                this.BulletSpeed = 10 + r.nextFloat()*10;
                this.BulletAmount = r.nextInt(2)+3;
                break;
            default:
                break;
        }
    }
}
