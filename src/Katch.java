import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class Katch extends GameCollision implements Observer {
    private boolean Right, Left;
    private final int right, left;
    public Katch(Image img, int x, int y, int leftKey, int rightKey) {
        super(img, x, y);
        this.right = rightKey;
        this.Right = false;
        this.left = leftKey;
        this.Left =  false;

    }
    public void move() {
        if(this.Right) {
            this.xx += 9;
            Collision();
        }
        if(this.Left) {
            this.xx -= 9;
            Collision();
        }
    }
    public void Collision(){
        if(xx<0){
            xx=0;
        }
        if(xx>=560){
            xx=560;
        }

    }
    @Override
    public void update(Observable obj, Object arg){
        EventHandler GameEvents = (EventHandler) arg;
        var tmp = (KeyEvent) GameEvents.Events;
        if(tmp.getKeyCode() == left) {
            if (tmp.getID() == KeyEvent.KEY_PRESSED) {
                this.Left = true;
            } else if (tmp.getID() == KeyEvent.KEY_RELEASED) {
                this.Left = false;
            }
        }
        if(tmp.getKeyCode() == right){
            if(tmp.getID() == KeyEvent.KEY_PRESSED){
                this.Right = true;
            }else if (tmp.getID() == KeyEvent.KEY_RELEASED){
                this.Right = false;
            }
        }
    }
    public Rectangle getRect() {
        return new Rectangle(xx, yy, 80, 30);
    }
}