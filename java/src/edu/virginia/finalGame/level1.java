package edu.virginia.finalGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.*;
import edu.virginia.engine.util.mouseFinder;

public class level1 extends Game {

    /* Initialize the instance variables for the level */
    Sprite bg = new Sprite("bg","background.png");
    wireSprite wire1 = new wireSprite("wire1","unk_wire.png");
    gateSprite gate1 = new gateSprite("gate1","not_gate.png","not"); // We need to add a picture for all gates
    wireSprite wire2 = new wireSprite("wire2","unk_wire.png");
    gateSprite end = new gateSprite("end","end.png","end");

    String endVal = "None";
    String result = "";

    ArrayList<Sprite> sprites = new ArrayList();
    mouseFinder mf;

    public level1(){
        super("Logical Boom", 1300, 700);

        /* Add the sprites */
        sprites.add(wire1);
        sprites.add(wire2);
        sprites.add(gate1);
        sprites.add(end);

        bg.setScale(2);
        wire1.setPosition(new Point(100,350));
        wire1.updateHitBox(100,350);
        wire1.setScale(4);
        wire1.updateHitBoxScale();

        // Wires are approx 260 in width
        gate1.setPosition(new Point(360,235));
        gate1.setScale(4);
        gate1.addPrevWire(wire1);

        wire2.setPosition(new Point(620,350));
        wire2.setScale(4);
        wire2.setPrevGate(gate1);

        end.setPosition(new Point(880,235));
        end.setScale(4);
        end.addPrevWire(wire2);

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

        // We can change the key but on pressing G, figure out if the end is True!
        if (pressedKeys.contains(KeyEvent.VK_G)){
            endVal = end.calculateValue();
            if (endVal.equals("-"))
                result = "You forgot set a wire!";
            else if (endVal.equals("T"))
                result = "Nice job!";
            else if (endVal.equals("F"))
                result = "Not quite, try again!";
        }

        if (pressedKeys.contains(KeyEvent.VK_R)) {
            for (Sprite sp : sprites) {
                sp.reset();
            }
        }

        /* If a wire changes, make sure it's image linked is correct */
        if (wire1 != null)
            wire1.fixWireImg();

        if (wire2 != null)
            wire2.fixWireImg();

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

        if (gate1 != null && gate1.getVisible())
            gate1.draw(g);

        if (wire2 != null && wire2.getVisible())
            wire2.draw(g);

        if (end != null && end.getVisible())
            end.draw(g);

        if (endVal != null && !endVal.equals("None")) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g.drawString(result, 500, 50);
        }

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
