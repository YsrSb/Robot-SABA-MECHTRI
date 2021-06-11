package org.emp.gl.robotstate;

public class Gauche  extends State{
    public Gauche(MyRobot r) {
        super(r);
    }

    @Override
    public void goRight() {

    }

    @Override
    public void goleft() {

    }

    @Override
    public void goUp() {
        robot.changeState(new Haut(robot));
    }

    @Override
    public void goDown() {
        robot.changeState(new Bas(robot));
    }
    @Override
    public void move() {

        robot.setOrientation(robot.STATE_LEFT);

        robot.setPosition_x(robot.getPosition_x()-1);

    }
    @Override
    public String getSate() {
        return String.valueOf(this);
    }
}
