import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Inventory {
    public inventoryslot[] slots;

    public Inventory(){
        slots = new inventoryslot[6];
        for(int i =0;i<=5;i++){
            slots[i]= new inventoryslot(0, 100*(i+1), Display.HEIGHT/2);
        }
    }
    public void drawInventory(Graphics g){
        Graphics2D gg = (Graphics2D) g.create();
        gg.setColor(Color.black);
        for(inventoryslot p : slots){
            p.drawslot(gg);
        }

        gg.dispose();
    }

    public void addtoinventory(int item){
        for(int i =0;i<=5;i++){
            if(slots[i].Item == 0){
                slots[i].Item = item;
                return;
            }
        }
    }
}
