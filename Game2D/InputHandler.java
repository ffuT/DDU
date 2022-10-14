import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener{
    public boolean[] key = new boolean[68836];
    public int mouseX=0; 
    public int mouseY=0;
    public boolean mouseClicked;
    public boolean mouseReleased;
    public boolean mclick;
    public boolean mdrag;
    public boolean mpres;
    
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mouseClicked = true;
        mdrag=true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();     
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = true;
        mclick=true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseReleased = true;
        mclick=false;
        mdrag=false;
        mpres=false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mpres = true;
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void focusGained(FocusEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        for(int i =0;i<key.length;i++){
            key[i]=false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode>0 && keyCode<key.length){
            key[keyCode] = true;
        }    
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int KeyCode = e.getKeyCode();
        if(KeyCode > 0 && KeyCode < key.length){
            key[KeyCode] = false;
        }
    }
    
}