package org.emp.gl.Launcher;

import org.emp.gl.core.Lookup.Lookup;
import org.emp.gl.gui.Maze;
import org.emp.gl.robotstate.MyRobot;

public class Launcher {
    public static void main(String[] args) throws InterruptedException {

        Timer.TimerServiceImplWithDelegation tim =  new Timer.TimerServiceImplWithDelegation();
        Maze maze = new Maze();
        tim.addTimeChangeListener(maze);

        Lookup.getInstance().register(MyRobot.class,new MyRobot());
        maze.initializeWindow();
    }

}
