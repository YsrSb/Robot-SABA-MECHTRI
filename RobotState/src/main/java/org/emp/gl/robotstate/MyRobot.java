package org.emp.gl.robotstate;
import org.emp.gl.RobotActions.RobotActions;
import org.emp.gl.timer.service.TimerChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;


public class MyRobot implements RobotActions  {


    private static MyRobot Instance = null;
    public MyRobot(){};
    public static MyRobot getInstance(){
        if(Instance==null)
            Instance = new MyRobot();
        return Instance;
    }
    State state=new Bas(this);
    int position_x=1;
    int position_y=1;

    String Orientation ;
    final static  public String STATE_RIGHT="State is right";
    final static  public String STATE_LEFT="State is left";
    final static  public String STATE_UP="State is up";
    final static  public String STATE_DOWN="State is down";



    public PropertyChangeSupport pce=new PropertyChangeSupport(this);

    public int getPosition_x() {
        return position_x;
    }

    public void setPosition_x(int position_x) {
        this.position_x = position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    public void setPosition_y(int position_y) {
        this.position_y = position_y;
    }

    public void changeState(State newState){
        state= newState;
    }

    @Override
    public void goRight() {
        System.out.println("im right");
        state.goRight();
    }

    @Override
    public void goleft() {
        System.out.println("im left");
        state.goleft();
    }

    @Override
    public void goUp() {
        System.out.println("im up");
        state.goUp();
    }

    @Override
    public void goDown() {
        System.out.println("im down");
        state.goDown();
    }

    @Override
    public void move() {
        System.out.println("im movig");
        state.move();
    }

    public void up() {
    }
    public void down() {
    }
    public void left() {
    }
    public void right() {
    }

    public String getOrientation() {
        return Orientation;
    }

    public void setOrientation(String orientation) {
        this.Orientation = orientation;
    }

    public String getSate() {
        return String.valueOf(state.getSate());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {

            case TimerChangeListener.SECONDE_PROP:
                state.move();
                pce.firePropertyChange(this.Orientation,null,null);
                break;

            default:
                break;

        }
    }
}
