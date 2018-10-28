package edu.virginia.lab3test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabThreeSimulator extends Game{

    /* Create a sprite object for our game. We'll use sun */
    Sprite sun = new Sprite("Sun", "Sun_0.png");

    /* All the planets are children of this sun object, we'll treat them as non-animated sprites too */
    Sprite mercury = new Sprite("Mercury","Mercury.png");
    Sprite mercuryMoon = new Sprite("MercuryMoon","Mercury's Moon.png");
    Sprite earth = new Sprite("Earth","Earth_0.png");
    Sprite moon = new Sprite("Moon","Moon_0.png");



    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabThreeSimulator() {
        super("Lab One Test Game", 500, 300);

        // Initialize the inheritance structure for planets
        sun.addChild(earth);
        sun.addChild(mercury);
        earth.addChild(moon);
        mercury.addChild(mercuryMoon);

        // Place planets at their proper relational distance
        earth.setPosition(new Point(300,0));
        earth.setScale(0.75);
        mercury.setPosition(new Point(0,150));
        mercury.setScale(0.75);

        moon.setPosition( new Point(100,0));
        moon.setScale(0.5);
        mercuryMoon.setPosition(new Point(0, 100));
        mercuryMoon.setScale(0.5);

        // Edit pivot points to be at the center of the planet
        Point sunPP = new Point(sun.getPosition().x+sun.getUnscaledWidth(),sun.getPosition().y+sun.getUnscaledHeight());

        earth.setPivotPoint(new Point(sun.getUnscaledWidth()/2-earth.getPosition().x,sun.getUnscaledHeight()/2-earth.getPosition().y));
        mercury.setPivotPoint(new Point(sun.getUnscaledWidth()/2-mercury.getPosition().x,sun.getUnscaledHeight()/2-mercury.getPosition().y));
        moon.setPivotPoint(new Point(earth.getUnscaledWidth()/2-(moon.getPosition().x),earth.getUnscaledHeight()/2-(moon.getPosition().y)));
        mercuryMoon.setPivotPoint(new Point(mercury.getUnscaledWidth()/2-mercuryMoon.getPosition().x,mercury.getUnscaledHeight()/2-mercuryMoon.getPosition().y));
        sun.setPivotPoint(new Point(sun.getPosition().x+sun.getUnscaledWidth()/2,sun.getPosition().y+sun.getUnscaledHeight()/2));
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

        /* Make sure sun is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if(sun != null) {
            sun.update(pressedKeys);
        }

        if (pressedKeys.contains(KeyEvent.VK_UP)){
            sun.setPosition(new Point(sun.getPosition().x,
                    sun.getPosition().y - 5));

            // Pivot point has to change with movement to center on sun
            sun.setPivotPoint(new Point((int)(sun.getUnscaledWidth()/2 * sun.getScaleX())
                    ,(int)(sun.getUnscaledHeight()/2 * sun.getScaleY())));
        }

        if (pressedKeys.contains(KeyEvent.VK_DOWN)){
            sun.setPosition(new Point(sun.getPosition().x,
                    sun.getPosition().y + 5));

            // Pivot point has to change with movement to center on sun
            sun.setPivotPoint(new Point((int)(sun.getUnscaledWidth()/2 * sun.getScaleX())
                    ,(int)(sun.getUnscaledHeight()/2 * sun.getScaleY())));
        }

        if (pressedKeys.contains(KeyEvent.VK_LEFT)){
            sun.setPosition(new Point(sun.getPosition().x - 5,
                    sun.getPosition().y));

            // Pivot point has to change with movement to center on sun
            sun.setPivotPoint(new Point((int)(sun.getUnscaledWidth()/2 * sun.getScaleX())
                    ,(int)(sun.getUnscaledHeight()/2 * sun.getScaleY())));
        }

        if (pressedKeys.contains(KeyEvent.VK_RIGHT)){
            sun.setPosition(new Point(sun.getPosition().x + 5,
                    sun.getPosition().y));

            // Pivot point has to change with movement to center on sun
            sun.setPivotPoint(new Point((int)(sun.getUnscaledWidth()/2 * sun.getScaleX())
                    ,(int)(sun.getUnscaledHeight()/2 * sun.getScaleY())));
        }

        if (pressedKeys.contains(KeyEvent.VK_A)){
            sun.setRotation(sun.getRotation() + 5);
        }

        if (pressedKeys.contains(KeyEvent.VK_S)){
            sun.setRotation(sun.getRotation() - 5);
        }

        if (pressedKeys.contains(KeyEvent.VK_W)){
            if(sun.getScaleY() > 0.2) {
                sun.setScaleX(sun.getScaleX() - 0.1);
                sun.setScaleY(sun.getScaleY() - 0.1);
            }

            // Pivot point has to change with scaling
            sun.setPivotPoint(new Point(sun.getPosition().x + (int)(sun.getUnscaledWidth()/2 * sun.getScaleX())
                    ,sun.getPosition().y + (int)(sun.getUnscaledHeight()/2 * sun.getScaleY())));
        }

        if (pressedKeys.contains(KeyEvent.VK_Q)){
            sun.setScaleX(sun.getScaleX() + 0.1);
            sun.setScaleY(sun.getScaleY() + 0.1);

            // Pivot point has to change with scaling
            sun.setPivotPoint(new Point(sun.getPosition().x + (int)(sun.getUnscaledWidth()/2 * sun.getScaleX())
                    ,sun.getPosition().y + (int)(sun.getUnscaledHeight()/2 * sun.getScaleY())));
        }

        /* Automatic rotation of major planets */


        if(earth != null) {
            earth.setRotation(earth.getRotation() + 3);
        }

        if(moon != null) {
            moon.setRotation(moon.getRotation() + 5);
        }

        if (mercury != null) {
            mercury.setRotation(mercury.getRotation() + 7);
        }

        if (mercuryMoon != null) {
            mercuryMoon.setRotation((mercuryMoon.getRotation() + 5));
        }
    }

    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure sun gets drawn to
     * the screen, we need to make sure to override this method and call sun's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        /* Same, just check for null in case a frame gets thrown in before Sun is initialized */
        if(sun != null && sun.getVisible())
            sun.draw(g);
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        LabThreeSimulator game = new LabThreeSimulator();
        game.start();
    }
}
