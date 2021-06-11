package org.emp.gl.gui;

import org.emp.gl.core.Lookup.Lookup;
import org.emp.gl.robotstate.MyRobot;
import org.emp.gl.timer.service.TimerChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;

import static org.emp.gl.robotstate.State.robot;


public class Maze<processKey> extends JFrame implements TimerChangeListener, ActionListener { // see java naming conventions https://www.geeksforgeeks.org/java-naming-conventions/

    int x,y;

    static char[][] puzzle = {
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 1, 0, 0, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},};
    JFrame frame;
    JButton up;
    JButton down;
    JButton left;
    JButton right;
    JLabel xl;
    JLabel yl;
    public void initializeWindow() {

         MyRobot robot = Lookup.getInstance().getService(MyRobot.class);
        JFrame mainFrame = new JFrame("Maze Solver");
         up = new JButton("up button");
         down = new JButton("down button");
         left = new JButton("left buton");
         right = new JButton("right button");
         yl = new JLabel("y");
         yl.setBounds(70,140,100,100);
        xl = new JLabel("x");
        xl.setBounds(70,70,100,100);
         mainFrame.add(xl);
        mainFrame.add(yl);

        mainFrame.add(up);
        mainFrame.add(down);
        mainFrame.add(left);
        mainFrame.add(right);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new GridLayout(puzzle.length, puzzle[0].length));// avoid null layouts
        //mainFrame.setSize(1920, 1080); //use preferred size and let layout manager set the size
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(150,150);
        up.addActionListener(this);
        down.addActionListener(this);
        left.addActionListener(this);
        right.addActionListener(this);


        for (int row = 0; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle[0].length-1; col++) {
                JLabel label = makeLabel(puzzle[row][col]);
                mainFrame.add(label);

            }
        }
        mainFrame.pack();
        mainFrame.setVisible(true);

        puzzle[robot.getPosition_x()][robot.getPosition_y()] = 2;
       makeLabel( puzzle[robot.getPosition_x()][robot.getPosition_y()]);

    }


    private JLabel makeLabel(char c) {

        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.HORIZONTAL);
        label.setPreferredSize(new Dimension(10, 10));
        switch (c) {
            case 1:
                label.setBackground(Color.BLACK);
                break;
            case 2:
                label.setBackground(Color.GREEN);
                break;
            default:
                label.setBackground(Color.WHITE);
                break;

        }
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        return label;
    }

    public Maze(){

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(robot.getSate());
        xl.setText(String.valueOf(robot.getPosition_x()));
        yl.setText(String.valueOf(robot.getPosition_y()));
          if(robot.getSate().contains("Droite")){
              System.out.println("droiteeeeeeeeeeee");
              x = robot.getPosition_x();
              if(x+1>-1 && y>-1 && x<puzzle.length && y<puzzle.length && puzzle[x+1][y]!= 1){
                  robot.setPosition_x(x+1);
                  System.out.println("x" + robot.getPosition_x() + "y " + robot.getPosition_y());
                  robot.goRight();

              }

          }
          if(robot.getSate().contains("Bas")){
                    System.out.println("Bassssssss");
              y = robot.getPosition_y();
              if(y-1>-1 && x>-1 && y<puzzle.length && x<puzzle.length && puzzle[x][y-1]!= 1){
                  robot.setPosition_y(y-1);
                  System.out.println("x" + robot.getPosition_x() + "y " + robot.getPosition_y());
                  robot.goDown();}

          }
        if(robot.getSate().contains("Gauche")){

            x = robot.getPosition_x();
            if(x-1>-1 && y>-1 && x<puzzle.length && y<puzzle.length && puzzle[x-1][y]!= 1){
                robot.setPosition_x(x-1);
                System.out.println("x" + robot.getPosition_x() + "y " + robot.getPosition_y());
                robot.goleft();}

        }

        if(robot.getSate().contains("Haut")){

            y = robot.getPosition_y();
            if(y+1>-1 && x>-1 && y<puzzle.length && x<puzzle.length && puzzle[x][y+1]!= 1){
                robot.setPosition_y(y+1);
                System.out.println("x" + robot.getPosition_x() + "y " + robot.getPosition_y());
                robot.goUp();
            }


        }


        }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        if (e.getID() != KeyEvent.KEY_PRESSED)
            return;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("go Right");
            robot.goRight();

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("go left");
            robot.goleft();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("go up");
            robot.goUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("go down");
            robot.goDown();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == up ){

            y = robot.getPosition_y();
            if(y+1>-1 && x>-1 && y<puzzle.length && x<puzzle.length && puzzle[x][y+1]!= 1){
            robot.setPosition_y(y+1);
            System.out.println("x" + robot.getPosition_x() + "y " + robot.getPosition_y());
            robot.goUp();
            }

        }

        if(e.getSource() == down ){
            y = robot.getPosition_y();
            if(y-1>-1 && x>-1 && y<puzzle.length && x<puzzle.length && puzzle[x][y-1]!= 1){
            robot.setPosition_y(y-1);
            System.out.println("x" + robot.getPosition_x() + "y " + robot.getPosition_y());
            robot.goDown();}

        }
        if(e.getSource() == left ){
            x = robot.getPosition_x();
            if(x-1>-1 && y>-1 && x<puzzle.length && y<puzzle.length && puzzle[x-1][y]!= 1){
                robot.setPosition_x(x-1);
                System.out.println("x" + robot.getPosition_x() + "y " + robot.getPosition_y());
                robot.goleft();}

        }
        if(e.getSource() == right ){
            x = robot.getPosition_x();
            if(x+1>-1 && y>-1 && x<puzzle.length && y<puzzle.length && puzzle[x+1][y]!= 1){
            robot.setPosition_x(x+1);
            System.out.println("x" + robot.getPosition_x() + "y " + robot.getPosition_y());
            robot.goRight();
            }

        }
    }
}
