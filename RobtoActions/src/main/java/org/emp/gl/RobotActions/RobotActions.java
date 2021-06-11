package org.emp.gl.RobotActions;
import org.emp.gl.timer.service.TimerChangeListener;
public interface RobotActions extends TimerChangeListener {
    public abstract void goRight();

    public abstract void goleft();

    public abstract void goUp();
    public abstract void goDown();
    public abstract void move();

}
