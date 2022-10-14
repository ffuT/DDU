import java.awt.event.KeyEvent;

public class Game {
    public Controller control;

    public Game(){
        control = new Controller();
    } 
    
    public void tick(boolean[] Key){
        boolean forward = Key[KeyEvent.VK_W];
        boolean back = Key[KeyEvent.VK_S];
        boolean left = Key[KeyEvent.VK_A];
        boolean right = Key[KeyEvent.VK_D];
        boolean turnleft = Key[KeyEvent.VK_LEFT];
        boolean turnright = Key[KeyEvent.VK_RIGHT];
        boolean jump = Key[KeyEvent.VK_SPACE];
        boolean sprint = Key[KeyEvent.VK_SHIFT];
        boolean Inventory = Key[KeyEvent.VK_I];
        
        control.tick(forward, back, left, right, turnleft, turnright, jump, sprint, Inventory);
    }
}

