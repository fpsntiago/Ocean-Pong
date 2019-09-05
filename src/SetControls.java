import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SetControls extends KeyAdapter {

    private EventHandler events;
    @Override
    public void keyPressed(KeyEvent e){
        events.setKey(e);
    }
    @Override
    public void keyReleased(KeyEvent e){
        events.setKey(e);
    }
    protected SetControls(EventHandler events){
        this.events = events;
    }

}