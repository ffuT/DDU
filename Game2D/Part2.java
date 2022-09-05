import java.util.Random;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Part2 {
    Random r;
    public int Rarity;

    float Knockback;
    int FireRate;

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
    public Part2(int i){
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
                this.FireRate = r.nextInt(15)+25;
                this.Knockback = r.nextInt(10);
                break;
            case 2:
                this.FireRate = r.nextInt(10)+25;
                this.Knockback = r.nextInt(20);
                break;
            case 3:
                this.FireRate = r.nextInt(10)+20;
                this.Knockback = r.nextInt(30);
                break;
            case 4:
                this.FireRate = r.nextInt(5)+20;
                this.Knockback = r.nextInt(40);
                break;
            case 5:
                this.FireRate = r.nextInt(5)+10;
                this.Knockback = r.nextInt(50);
                break;
            case 6:
                this.FireRate = r.nextInt(5)+5;
                this.Knockback = r.nextInt(80);
            break;
            default:
                break;
        }
    }
}
