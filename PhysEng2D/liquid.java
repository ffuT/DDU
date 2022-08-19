import java.awt.Graphics;
import java.awt.Color;

class Liquid {
      float x,y,w,h;
      float c;
     
      Liquid(float x_, float y_, float w_, float h_, float c_) {
        this.x = x_;
        this.y = y_;
        this.w = w_;
        this.h = h_;
        this.c = c_;
      }
     
      public void draw(Graphics g, Color k){
        g.setColor(k);
        g.fillRect((int) x, (int) y, (int) w, (int) h);
    }
     
    }
