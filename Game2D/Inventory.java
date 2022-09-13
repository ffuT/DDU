import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Inventory {
    public inventoryslot[] slots;
    private inventoryslot tempslot= new inventoryslot(0, 0, 0);
    private inventoryslot tempslot2= new inventoryslot(0, 0, 0);
    public inventoryslot[] craftslots;
    public Gunslot gunslot,gunslot2,gunslotE,tempGunslot = new Gunslot(0, 0, 0);

    public Inventory(Player p){
        gunslot = new Gunslot(1, 800, Display.HEIGHT/4*3);
        gunslot2 = new Gunslot(2, 900, Display.HEIGHT/4*3);
        gunslotE = new Gunslot(3, 1100, Display.HEIGHT/4*3,new Weapon(new Part1(1), new Part2(1), new Part3(1),p));
        craftslots = new inventoryslot[3];
        slots = new inventoryslot[6];
        for(int i =0;i<=5;i++){
            slots[i]= new inventoryslot(0, 100*(i+1), Display.HEIGHT/4*3);
        }
        for(int i =0;i<=2;i++){
            craftslots[i]= new inventoryslot(0, Display.WIDTH/2+100*(i+1), 100);
        }
    }
    public void update(){
        //checking if there is crafting components in craftslots
        if(craftslots[2].Item > 0 && craftslots[1].Item > 0 && craftslots[0].Item > 0){
            if(craftslots[0].imgPath.contains("part 1")){
                if(craftslots[1].imgPath.contains("part 2") && craftslots[2].imgPath.contains("part 3")){
                    //crafing now
                    System.out.println("crafted new gun");
                    Weapon newGun = new Weapon(craftslots[0].p1, craftslots[1].p2, craftslots[2].p3);
                    if(gunslot.gun==null){
                    gunslot.addweapon(newGun);
                    } else{
                    gunslot2.addweapon(newGun);
                    }
                    zeroslots();  
                } else if(craftslots[2].imgPath.contains("part 2") && craftslots[1].imgPath.contains("part 3")){
                    //crafing now
                    System.out.println("crafted new gun");
                    Weapon newGun = new Weapon(craftslots[0].p1, craftslots[2].p2, craftslots[1].p3);
                    if(gunslot.gun==null){
                        gunslot.addweapon(newGun);
                        } else{
                        gunslot2.addweapon(newGun);
                        }                    
                    zeroslots();
                }
            } else if(craftslots[1].imgPath.contains("part 1")){
                if(craftslots[0].imgPath.contains("part 2") && craftslots[2].imgPath.contains("part 3")){
                    //crafing now
                    System.out.println("crafted new gun");
                    Weapon newGun = new Weapon(craftslots[1].p1, craftslots[0].p2, craftslots[2].p3);
                    if(gunslot.gun==null){
                        gunslot.addweapon(newGun);
                        } else{
                        gunslot2.addweapon(newGun);
                        }                    
                    zeroslots();
                } else if(craftslots[2].imgPath.contains("part 2") && craftslots[0].imgPath.contains("part 3")){
                    //crafing now
                    System.out.println("crafted new gun");
                    Weapon newGun = new Weapon(craftslots[1].p1, craftslots[2].p2, craftslots[0].p3);
                    if(gunslot.gun==null){
                        gunslot.addweapon(newGun);
                        } else{
                        gunslot2.addweapon(newGun);
                        }                    
                    zeroslots();
                }
            } else if(craftslots[2].imgPath.contains("part 1")){
                if(craftslots[1].imgPath.contains("part 2") && craftslots[0].imgPath.contains("part 3")){
                    //crafing now
                    System.out.println("crafted new gun");
                    Weapon newGun = new Weapon(craftslots[2].p1, craftslots[1].p2, craftslots[0].p3);
                    if(gunslot.gun==null){
                        gunslot.addweapon(newGun);
                        } else{
                        gunslot2.addweapon(newGun);
                        }                    
                    zeroslots();
                } else if(craftslots[0].imgPath.contains("part 2") && craftslots[1].imgPath.contains("part 3")){
                    //crafing now
                    System.out.println("crafted new gun");
                    Weapon newGun = new Weapon(craftslots[2].p1, craftslots[0].p2, craftslots[1].p3);
                    if(gunslot.gun==null){
                        gunslot.addweapon(newGun);
                        } else{
                        gunslot2.addweapon(newGun);
                        }                    
                    zeroslots();
                }

            } else{
                return;
            }
        } else{
            return;
        }
    }
    public void zeroslots(){
        for(inventoryslot i: craftslots){
            i.Item=0;
        }
    }

    public void Selected(PVector mouse,boolean click, boolean drag,boolean release){
        tempslot.x = (int) mouse.x;
        tempslot.y = (int) mouse.y;

        //gunslots
        if(click && gunslot.gun !=null  && mouse.x > gunslot.x && mouse.x < gunslot.x+100 && mouse.y > gunslot.y && mouse.y < gunslot.y+100){
            if(gunslot.gun != null){
                tempGunslot.copyslot(gunslotE);
                gunslotE.copyslot(gunslot);
                gunslot.copyslot(tempGunslot);
            }
        }
        if(click && gunslot2.gun !=null  && mouse.x > gunslot2.x && mouse.x < gunslot2.x+100 && mouse.y > gunslot2.y && mouse.y < gunslot2.y+100){
            if(gunslot.gun != null){
                tempGunslot.copyslot(gunslotE);
                gunslotE.copyslot(gunslot2);
                gunslot2.copyslot(tempGunslot);
            }
        }

        //crafting table
        if(click && mouse.x > craftslots[0].x && mouse.x < craftslots[0].x+100 && mouse.y > craftslots[0].y && mouse.y < craftslots[0].y+100){
            if(tempslot.Item==0){
            tempslot.copyslot(craftslots[0]);
            craftslots[0]=new inventoryslot(0, craftslots[0].x, craftslots[0].y);
            } else {
                tempslot2.copyslot(tempslot);
                tempslot.copyslot(craftslots[0]);
                craftslots[0].copyslot(tempslot2);
            }
        }
        if(click && mouse.x > craftslots[1].x && mouse.x < craftslots[1].x+100 && mouse.y > craftslots[1].y && mouse.y < craftslots[1].y+100){
            if(tempslot.Item==0){
            tempslot.copyslot(craftslots[1]);
            craftslots[1]=new inventoryslot(0, craftslots[1].x, craftslots[1].y);
            } else {
                tempslot2.copyslot(tempslot);
                tempslot.copyslot(craftslots[1]);
                craftslots[1].copyslot(tempslot2);
            }
        }
        if(click && mouse.x > craftslots[2].x && mouse.x < craftslots[2].x+100 && mouse.y > craftslots[2].y && mouse.y < craftslots[2].y+100){
            if(tempslot.Item==0){
            tempslot.copyslot(craftslots[2]);
            craftslots[2]=new inventoryslot(0, craftslots[2].x, craftslots[2].y);
            } else {
                tempslot2.copyslot(tempslot);
                tempslot.copyslot(craftslots[2]);
                craftslots[2].copyslot(tempslot2);
            }
        }

        //inventory
        if(click && mouse.x > slots[0].x && mouse.x < slots[0].x+100 && mouse.y > slots[0].y && mouse.y < slots[0].y+100){
            if(tempslot.Item==0){
            tempslot.copyslot(slots[0]);
            slots[0]=new inventoryslot(0, slots[0].x, slots[0].y);
            } else {
                tempslot2.copyslot(tempslot);
                tempslot.copyslot(slots[0]);
                slots[0].copyslot(tempslot2);
            }
        }
        if(click && mouse.x > slots[1].x && mouse.x < slots[1].x+100 && mouse.y > slots[1].y && mouse.y < slots[1].y+100){
            if(tempslot.Item==0){
            tempslot.copyslot(slots[1]);
            slots[1]=new inventoryslot(0, slots[1].x, slots[1].y);
            } else {
                tempslot2.copyslot(tempslot);
                tempslot.copyslot(slots[1]);
                slots[1].copyslot(tempslot2);
            }
        }
        if(click && mouse.x > slots[2].x && mouse.x < slots[2].x+100 && mouse.y > slots[2].y && mouse.y < slots[2].y+100){
            if(tempslot.Item==0){
            tempslot.copyslot(slots[2]);
            slots[2]=new inventoryslot(0, slots[2].x, slots[2].y);
            } else {
                tempslot2.copyslot(tempslot);
                tempslot.copyslot(slots[2]);
                slots[2].copyslot(tempslot2);
            }
        }
        if(click && mouse.x > slots[3].x && mouse.x < slots[3].x+100 && mouse.y > slots[3].y && mouse.y < slots[3].y+100){
            if(tempslot.Item==0){
            tempslot.copyslot(slots[3]);
            slots[3]=new inventoryslot(0, slots[3].x, slots[3].y);
            } else {
                tempslot2.copyslot(tempslot);
                tempslot.copyslot(slots[3]);
                slots[3].copyslot(tempslot2);
            }
        }
        if(click && mouse.x > slots[4].x && mouse.x < slots[4].x+100 && mouse.y > slots[4].y && mouse.y < slots[4].y+100){
            if(tempslot.Item==0){
            tempslot.copyslot(slots[4]);
            slots[4]=new inventoryslot(0, slots[4].x, slots[4].y);
            } else {
                tempslot2.copyslot(tempslot);
                tempslot.copyslot(slots[4]);
                slots[4].copyslot(tempslot2);
            }
        }
        if(click && mouse.x > slots[5].x && mouse.x < slots[5].x+100 && mouse.y > slots[5].y && mouse.y < slots[5].y+100){
            if(tempslot.Item==0){
            tempslot.copyslot(slots[5]);
            slots[5]=new inventoryslot(0, slots[5].x, slots[5].y);
            } else {
                tempslot2.copyslot(tempslot);
                tempslot.copyslot(slots[5]);
                slots[5].copyslot(tempslot2);
            }
        }
    }

    public void drawInventory(Graphics g, PVector mouse){
        Graphics2D gg = (Graphics2D) g.create();
        gg.setColor(Color.black);
        for(inventoryslot p : slots){
            p.drawslot(gg);
        }
        for(inventoryslot p : craftslots){
            p.drawslot(gg);
        }
        if(tempslot.Item>0)
            tempslot.drawslot(gg);

        gunslot.drawslot(gg);
        gunslot2.drawslot(gg);
        gunslotE.drawslot(gg);
            gg.setColor(Color.white);
            //this can be a loop
        if(gunslot.gun !=null  && mouse.x > gunslot.x && mouse.x < gunslot.x+100 && mouse.y > gunslot.y && mouse.y < gunslot.y+100){
            String dmg = String.format("Gun Damage: %.3f", gunslot.gun.DMG);
            gg.drawString(dmg, mouse.x+10, mouse.y);
            gg.drawString("Gun Spread: " + gunslot.gun.Spread, mouse.x+10, mouse.y+15);
            gg.drawString("Gun firerate: " + gunslot.gun.FireRate, mouse.x+10, mouse.y+30);
            gg.drawString("Gun Bulletspeed: " + gunslot.gun.BulletSpeed, mouse.x+10, mouse.y+45);
            gg.drawString("Gun Bulletamount: " + gunslot.gun.BulletAmount, mouse.x+10, mouse.y+60);        }
        if(gunslot2.gun !=null  && mouse.x > gunslot2.x && mouse.x < gunslot2.x+100 && mouse.y > gunslot2.y && mouse.y < gunslot2.y+100){
            String dmg = String.format("Gun Damage: %.3f", gunslot2.gun.DMG);
            gg.drawString(dmg, mouse.x+10, mouse.y);
            gg.drawString("Gun Spread: " + gunslot2.gun.Spread, mouse.x+10, mouse.y+15);
            gg.drawString("Gun firerate: " + gunslot2.gun.FireRate, mouse.x+10, mouse.y+30);
            gg.drawString("Gun Bulletspeed: " + gunslot2.gun.BulletSpeed, mouse.x+10, mouse.y+45);
            gg.drawString("Gun Bulletamount: " + gunslot2.gun.BulletAmount, mouse.x+10, mouse.y+60);        }
        if(gunslotE.gun !=null  && mouse.x > gunslotE.x && mouse.x < gunslotE.x+100 && mouse.y > gunslotE.y && mouse.y < gunslotE.y+100){
            String dmg = String.format("Gun Damage: %.3f", gunslotE.gun.DMG);
            gg.drawString(dmg, mouse.x+10, mouse.y);
            gg.drawString("Gun Spread: " + gunslotE.gun.Spread, mouse.x+10, mouse.y+15);
            gg.drawString("Gun firerate: " + gunslotE.gun.FireRate, mouse.x+10, mouse.y+30);
            gg.drawString("Gun Bulletspeed: " + gunslotE.gun.BulletSpeed, mouse.x+10, mouse.y+45);
            gg.drawString("Gun Bulletamount: " + gunslotE.gun.BulletAmount, mouse.x+10, mouse.y+60);        }
        for (inventoryslot p : slots) {
            if(p.p1 != null && mouse.x > p.x && mouse.x < p.x+100 && mouse.y > p.y && mouse.y < p.y+100){
                String dmg = String.format("Damage: %.3f", p.p1.DMG);
                gg.drawString(dmg, mouse.x+10, mouse.y);
            }
            if(p.p2 != null && mouse.x > p.x && mouse.x < p.x+100 && mouse.y > p.y && mouse.y < p.y+100){
                gg.drawString("Firerate "+p.p2.FireRate , mouse.x+10, mouse.y);
                gg.drawString("Knockback "+p.p2.Knockback , mouse.x+10, mouse.y+15);
            }
            if(p.p3 != null && mouse.x > p.x && mouse.x < p.x+100 && mouse.y > p.y && mouse.y < p.y+100){
                gg.drawString("BulletAmount "+p.p3.BulletAmount , mouse.x+10, mouse.y);
                gg.drawString("BulletSpeed "+p.p3.BulletSpeed , mouse.x+10, mouse.y+15);
                gg.drawString("BulletSpread "+p.p3.Spread , mouse.x+10, mouse.y+30);
            }
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
