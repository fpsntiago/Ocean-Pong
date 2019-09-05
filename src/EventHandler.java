import java.awt.event.KeyEvent;
import java.util.Observable;
public class EventHandler extends Observable {
    public Object Events;

    public void setKey(KeyEvent event){
        Events = event;
        setChanged();
        notifyObservers(this);
    }
}
