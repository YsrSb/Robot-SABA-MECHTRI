package Timer;


import org.emp.gl.timer.service.TimerChangeListener;

import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

public class TimerServiceImplWithDelegation extends TimerTask {

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    final static String EVENT = "seconde" ;


    public TimerServiceImplWithDelegation() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 100, 1000);
    }

    @Override
    public void run() {
        timeChanged();

    }


    public void addTimeChangeListener(TimerChangeListener pl) {
        pcs.addPropertyChangeListener(pl);
    }


    public void removeTimeChangeListener(TimerChangeListener pl) {
        pcs.removePropertyChangeListener(pl);
    }


    private void timeChanged() {
        pcs.firePropertyChange(TimerChangeListener.EVENT, "t", "z");
    }
}
