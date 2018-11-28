package edu.virginia.finalGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.*;
import edu.virginia.engine.util.GameClock;
import edu.virginia.engine.util.mouseFinder;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class level1 extends Game {

    /* Initialize the instance variables for the level */
    Sprite bg = new Sprite("bg","background.png");
    Sprite tw = new Sprite("Truth","true_wire.png");

    mouseFinder mf;

    public level1(){
        super("Game", 1300, 700);

        /* Add the sprites */
        tw.setPosition(new Point(100,100));
        tw.updateHitBox(100,100);
        tw.setScale(4);
        tw.updateHitBoxScale();
        bg.setScale(2);

        System.out.println(tw.getHitbox().getBounds());

        // Which wires can we click?
        ArrayList<Sprite> clickables = new ArrayList<>();
        clickables.add(tw);

        // Pass those to the mouseListener to check if they're clicked
        mf = new mouseFinder(clickables);
        this.getScenePanel().addMouseListener(mf);

        /* Add the sounds and music */
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

    }

    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        if (bg != null && bg.getVisible())
            bg.draw(g);

        if (tw != null && tw.getVisible())
            tw.draw(g);

        // Graphics2D g2d = (Graphics2D)g;
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        level1 game = new level1();
        game.start();

    }
}
