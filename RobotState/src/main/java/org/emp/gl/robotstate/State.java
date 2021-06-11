package org.emp.gl.robotstate;

public abstract class  State
{

    public static  MyRobot robot;

    public State(MyRobot r){
        robot=r;
    }
    public abstract void goRight();

    public abstract void goleft();

    public abstract void goUp();
    public abstract void goDown();
    public abstract void move();
    public  abstract String getSate();

}
