import java.util.Random;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Part1 {
    Random r;
    public int Rarity;
    public float DMG;
    public int Special;

    public Part1(){
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

    public Part1(int i){
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
                this.DMG = r.nextFloat()+1;
                this.Special = 0;
                break;
            case 2:
                this.DMG = r.nextFloat()+2;
                this.Special = 0;
                break;
            case 3:
                this.DMG = r.nextFloat()+3;
                this.Special = r.nextInt(3);
                break;
            case 4:
                this.DMG = r.nextFloat()+4;
                this.Special = r.nextInt(6);

                break;
            case 5:
                this.DMG = r.nextFloat()+5;
                this.Special = r.nextInt(8);

                break;
            case 6:
                this.DMG = r.nextFloat()+10;
                this.Special = r.nextInt(10);
                break;
            default:
                break;
        }
    }
}
