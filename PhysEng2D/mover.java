public class mover {
    public PVector location,velocity,acceleration;
    public float mass;

    //the mover calculates movement for object check TestSquare for example
    public mover(int x, int y, float mass) {
        this.mass = mass;
        this.location = new PVector(x,y);
        this.velocity = new PVector(0,0);
        this.acceleration = new PVector(0, 0);
      }
    public void update(){
        velocity.add(acceleration);
        location.add(velocity);
        acceleration.mult(0f);
        bounce();
    }
    //changes acceleration
    public void addforce(PVector v2){
        PVector f = v2.get();
        f.div(mass);
        acceleration.add(f);
    }

    //bounce reverse velocity when hitting wall
    public void bounce(){
        //bounce on wall x
        if(location.x >= Display.WIDTH-65 || location.x <= 0){
            velocity.x *= -1;
        }
        //bounce on wall y
        if(location.y >= Display.HEIGHT-85 || location.y <= 0){
            velocity.y *=-1;
        }
    }
}
