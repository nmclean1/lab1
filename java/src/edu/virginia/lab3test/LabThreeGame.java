package edu.virginia.lab3test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.AnimatedSprite;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabThreeGame extends Game{

    /* Create a sprite object for our game. We'll use sun */
    Sprite sun = new Sprite("Sun", "Sun_0.png");

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabThreeGame() {
        super("Lab One Test Game", 500, 300);
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
        }

        if (pressedKeys.contains(KeyEvent.VK_DOWN)){
            sun.setPosition(new Point(sun.getPosition().x,
                    sun.getPosition().y + 5));
        }

        if (pressedKeys.contains(KeyEvent.VK_LEFT)){
            sun.setPosition(new Point(sun.getPosition().x - 5,
                    sun.getPosition().y));
        }

        if (pressedKeys.contains(KeyEvent.VK_RIGHT)){
            sun.setPosition(new Point(sun.getPosition().x + 5,
                    sun.getPosition().y));
        }

        if (pressedKeys.contains(KeyEvent.VK_W)){
            sun.setRotation(sun.getRotation() + 5);
        }

        if (pressedKeys.contains(KeyEvent.VK_Q)){
            sun.setRotation(sun.getRotation() - 5);
        }

        if (pressedKeys.contains(KeyEvent.VK_A)){
            if(sun.getScaleY() > 0.2) {
                sun.setScaleX(sun.getScaleX() - 0.1);
                sun.setScaleY(sun.getScaleY() - 0.1);
            }
        }

        if (pressedKeys.contains(KeyEvent.VK_S)){
            sun.setScaleX(sun.getScaleX() + 0.1);
            sun.setScaleY(sun.getScaleY() + 0.1);
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
        LabThreeGame game = new LabThreeGame();
        game.start();

    }
}
