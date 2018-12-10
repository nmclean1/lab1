package edu.virginia.finalGame;

import edu.virginia.engine.display.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


public class finalGame {
    public static Integer level = null;
    JButton button = new JButton("Level 1");
    JButton button2 = new JButton("Level 2");
    JButton button3 = new JButton("Level 3");

    public finalGame(){

        class Button1Listener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                System.out.println("hi");
                level = 1;
            }
        }
        class Button2Listener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                level = 2;
            }
        }
        class Button3Listener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                level = 3;
            }
        }

        ActionListener listener1 = new Button1Listener();
        button.addActionListener(listener1);
        ActionListener listener2 = new Button2Listener();
        button2.addActionListener(listener2);
        ActionListener listener3 = new Button3Listener();
        button3.addActionListener(listener3);


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel containerPanel = new JPanel(new GridLayout(3,1));

        panel1.add(button);
        panel2.add(button2);
        panel3.add(button3);
        containerPanel.add(panel1);
        containerPanel.add(panel2);
        containerPanel.add(panel3);



        frame.add(containerPanel,BorderLayout.NORTH);


        frame.setSize(300,300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        finalGame menu = new finalGame();

        while (true) {
            if (level == null) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            else if (level == 1) {
                level1.main(args);
                break;
            }
            else if (level == 2) {
                level2.main(args);
                break;
            }
            else if (level == 3) {
                level3.main(args);
                break;
            }
        }
    }
}
