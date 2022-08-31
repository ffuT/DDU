public class mover {
    public PVector location,velocity,acceleration;
    public float mass;
    public boolean doesbounce = true;
    public boolean staybounds = true;

    float angle = 0;
    float aVelocity = 0;
    float aAcceleration = 0;

    //the mover calculates movement for object check TestSquare for example
    public mover(int x, int y, float mass) {
        this.mass = mass;
        this.location = new PVector(x,y);
        this.velocity = new PVector();
        this.acceleration = new PVector();
      }
    public void update(){
        //x and y movement
        velocity.add(acceleration);
        location.add(velocity);

        //rotaion
        aVelocity += aAcceleration;
        angle += aVelocity;        

        acceleration.mult(0f);
        bounce();
        checkedge();
    }
    //changes acceleration
    public void addforce(PVector v2){
        PVector f = v2.get();
        f.div(mass);
        acceleration.add(f);
    }

    //bounce reverse velocity when hitting wall
    public void bounce(){
        if(!doesbounce)
            return;
        //bounce on wall x
        if(location.x >= Display.WIDTH-65 || location.x <= 0){
            velocity.x *= -1;
        }
        //bounce on wall y
        if(location.y >= Display.HEIGHT-85 || location.y <= 0){
            velocity.y *=-1;
        }
    }
    public void checkedge(){
        if(!staybounds)
            return;
        if(location.x > Display.WIDTH-65)
            location.x = Display.WIDTH-65;
        if(location.x < 0)
            location.x = 0;
        if(location.y > Display.HEIGHT-85)
            location.y =Display.HEIGHT-85;
        if(location.y < 0)
            location.y=0;
    }
    /* 
    public boolean inInLiquid(Liquid l){
        if(location.x > l.x && location.x < l.x+l.w && location.y > l.y && location.y < l.y + l.h){
        return true;
        }else {
            return false;
        }
    }
    public void drag(Liquid l){
        float speed = velocity.mag();
        float dragMag = l.c * speed * speed;

        PVector drag = velocity.get();
        drag.mult(-1f);
        drag.normalize();
        drag.mult(dragMag);
        addforce(drag);
    }
    */
}
