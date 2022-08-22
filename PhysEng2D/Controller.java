
public class Controller {
    public double x , y, z , rotation , xa , za , rotationa;
    public static boolean rotLeft = false;
    public static boolean rotRight = false;
    public static boolean walk = false;
    public static boolean isSprinting=false;
    public static double rotationspeed = 0.01;
    public static double walkspeed = 0.75;
    public float xmove = 0;
    public float ymove = 0;

    public void tick(boolean forward, Boolean back,Boolean left,Boolean right,Boolean turnleft,Boolean turnright, boolean jump, boolean sprint, boolean crouch){
        walkspeed = 0.75;
        if(!walk)
            walkspeed=0;
        double jumpheight = 1;
        double crouchheight = 0.6;
        xmove = 0;
        ymove = 0;

        isSprinting=false;

        if(forward){
            ymove = -1;
            walk=true;
        }
        if(back){
            ymove = 1;
            walk=true;
        }
        if(left){
            xmove = -1;
            walk=true;
        }
        if(right){
            xmove = 1;
            walk=true;
        }
        if(turnleft){

        }
        if(turnright){

        }
        if(rotLeft){

        }
        if(rotRight){

        }
        if(jump){
        }
        if(crouch){

        }
        if(sprint){

        }
    }
}