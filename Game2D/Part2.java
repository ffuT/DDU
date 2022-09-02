import java.util.Random;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Part2 {
    Random r;
    public int Rarity;

    float Knockback;
    int FireRate;
    int BulletAmount;

    public Part2(){
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
                this.FireRate = r.nextInt(10)+100;
                this.Knockback = r.nextInt(10);
                this.BulletAmount = 1;
                break;
            case 2:
                this.FireRate = r.nextInt(20)+100;
                this.Knockback = r.nextInt(20);
                this.BulletAmount = r.nextInt(1)+1;
                break;
            case 3:
                this.FireRate = r.nextInt(30)+100;
                this.Knockback = r.nextInt(30);
                this.BulletAmount = r.nextInt(2)+1;
                break;
            case 4:
                this.FireRate = r.nextInt(50)+100;
                this.Knockback = r.nextInt(40);
                this.BulletAmount = r.nextInt(3)+2;
                break;
            case 5:
                this.FireRate = r.nextInt(75)+100;
                this.Knockback = r.nextInt(50);
                this.BulletAmount = r.nextInt(5)+2;
                break;
            case 6:
                this.FireRate = r.nextInt(100)+100;
                this.Knockback = r.nextInt(80);
                this.BulletAmount = r.nextInt(5)+5;
            break;
            default:
                break;
        }
    }
}
