public class Enemy1projectile extends Enemy1 {

    
    public Enemy1projectile(int x, int y, float health) {
        super(x, y, health);
        this.moveSpeed = 30;
    }


    public void update(Weapon gun, Player p, PVector dir){
        PVector Plocation = dir.get();

        Plocation.normalize();
        Plocation.mult(moveSpeed);
        
        x+=Plocation.x;
        y+=Plocation.y;

        if(p.hitBox(new PVector(x/4+10,y/4+10), new PVector(x/4+img.getWidth()/4-10,y/4+img.getHeight()/4-10))){
            p.hitpoints -= 1;
        }
        

        for (int i=0;i<=gun.FB.Particles.size()-1;i++) {
            if(gun.FB.Particles.get(i).hitBox(new PVector(x/4,y/4), new PVector(x/4+img.getWidth()/4,y/4+img.getHeight()/4))){ 
                if(isalive)
                    gun.FB.Particles.remove(gun.FB.Particles.get(i));
                //deal damage here
                health-=gun.DMG;
            }
        }
        if(health<=0)
            isalive=false;

        health-=1;

    }
}
