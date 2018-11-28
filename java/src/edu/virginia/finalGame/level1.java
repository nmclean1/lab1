package edu.virginia.finalGame;

import java.awt.*;
import java.util.ArrayList;

import edu.virginia.engine.display.*;
import edu.virginia.engine.util.mouseFinder;

public class level1 extends Game {

    /* Initialize the instance variables for the level */
    Sprite bg = new Sprite("bg","background.png");
    wireSprite wire1 = new wireSprite("wire1","unk_wire.png");

    mouseFinder mf;

    public level1(){
        super("Logical Boom", 1300, 700);

        /* Add the sprites */
        wire1.setPosition(new Point(100,100));
        wire1.updateHitBox(100,100);
        wire1.setScale(4);
        wire1.updateHitBoxScale();
        bg.setScale(2);

        System.out.println(wire1.getHitbox().getBounds());

        // Which wires can we click?
        ArrayList<wireSprite> clickables = new ArrayList<>();
        clickables.add(wire1);

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

        /* If a wire changes, make sure it's image linked is correct */
        if (wire1 != null)
            wire1.fixWireImg();

    }

    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        /* Always draw the bg first so other stuff is drawn on top */
        if (bg != null && bg.getVisible())
            bg.draw(g);

        if (wire1 != null && wire1.getVisible())
            wire1.draw(g);

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
