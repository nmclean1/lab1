package edu.virginia.finalGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.io.*;

import edu.virginia.engine.display.*;
import edu.virginia.engine.util.GameClock;
import edu.virginia.engine.util.mouseFinder;

public class level2 extends Game {

    /* Initialize the instance variables for the level */
    Sprite bg = new Sprite("bg","background.png");
    Sprite bolt1 = new Sprite("bolt1", "bolt.png");
    Sprite bolt2 = new Sprite("bolt2", "bolt.png");
    Sprite bolt3 = new Sprite("bolt3", "bolt.png");
    wireSprite wire1 = new wireSprite("wire1","unk_wire_.png","");
    wireSprite wire2 = new wireSprite("wire2","unk_wire_.png","");
    wireSprite wire3 = new wireSprite("wire3","unk_wire_.png","");
    wireSprite wire4 = new wireSprite("wire4","unk_wire_.png","down");
    wireSprite wire5 = new wireSprite("wire5","unk_wire_.png","up");
    wireSprite wire6 = new wireSprite("wire6","unk_wire_.png","up");
    wireSprite wire7 = new wireSprite("wire7","unk_wire_.png","");
    gateSprite gate1 = new gateSprite("gate1","not_gate.png","not");
    gateSprite gate2 = new gateSprite("gate2","or_gate.png","or");
    gateSprite gate3 = new gateSprite("gate3","not_gate.png","not");
    gateSprite gate4 = new gateSprite("gate4","and_gate.png","and");
    gateSprite end = new gateSprite("end","end.png","end");
    Sprite end_finished = new Sprite("end_finished","end_finished.png");
    Sprite textbox1 = new Sprite("txtbx1","black.png");
    Sprite textbox2 = new Sprite("txtbx2","black.png");
    Sprite explosion = new Sprite("explosion", "explosion.png");

    String endVal = "None";
    String result = "Press G to test your solution!";

    int lives = 3;
    double keyBufferTimer = 0;
    private GameClock gameClock;
    boolean over = false;
    double totalTime = 33;
    int timeLeft = 33;
    double resetTime = 0;
    boolean explosionPlayed = false;
    double wireScale = 2;
    double gateScale = 1.5;

    SoundManager SM = new SoundManager();

    ArrayList<Sprite> sprites = new ArrayList();
    mouseFinder mf;

    public level2(){
        super("Logical Boom", 1300, 700);
        this.initGameClock();

        SM.LoadMusic("Music","GameMusic.wav");
        SM.LoadSoundEffect("Click","Click.wav");
        SM.LoadSoundEffect("Win","Win.wav");
        SM.LoadSoundEffect("LoseLife","LoseLife.wav");
        SM.LoadSoundEffect("Explosion","Explosion.wav");
        SM.PlayMusic("Music");

        /* Add the sprites */
        sprites.add(wire1);
        sprites.add(wire2);
        sprites.add(wire3);
        sprites.add(wire4);
        sprites.add(wire5);
        sprites.add(wire6);
        sprites.add(wire7);
        sprites.add(gate1);
        sprites.add(gate2);
        sprites.add(gate3);
        sprites.add(gate4);
        sprites.add(bolt1);
        sprites.add(bolt2);
        sprites.add(bolt3);
        sprites.add(end);

        bg.setScaleY(0.98);

        wire1.setPosition(new Point(297,200));
        wire1.updateHitBox(100,350);
        wire1.setScale(wireScale);
        wire1.updateHitBoxScale();

        wire2.setPosition(new Point(297,455));
        wire2.updateHitBox(100,350);
        wire2.setScale(wireScale);
        wire2.updateHitBoxScale();

        wire3.setPosition(new Point(150,600));
        wire3.updateHitBox(100,350);
        wire3.setScale(wireScale);
        wire3.updateHitBoxScale();

        wire4.setPosition(new Point(510,200));
        wire4.setScale(wireScale);
        wire4.setPrevGate(gate1);

        wire5.setPosition(new Point(510,340));
        wire5.setScale(wireScale);
        wire5.setPrevGate(gate2);

        wire6.setPosition(new Point(350,485));
        wire6.setScale(wireScale);
        wire6.setPrevGate(gate3);

        wire7.setPosition(new Point(680,327));
        wire7.setScale(wireScale);
        wire7.setPrevGate(gate4);

        // Wires are approx 260 in width
        gate1.setPosition(new Point(425,160));
        gate1.setScale(gateScale);
        gate1.addPrevWire(wire1);

        gate2.setPosition(new Point(425,415));
        gate2.setScale(gateScale);
        gate2.addPrevWire(wire2);
        gate2.addPrevWire(wire6);

        gate3.setPosition(new Point(278,570));
        gate3.setScale(gateScale);
        gate3.addPrevWire(wire3);

        gate4.setPosition(new Point(584,285));
        gate4.setScale(gateScale);
        gate4.addPrevWire(wire4);
        gate4.addPrevWire(wire5);

        end.setPosition(new Point(808,285));
        end.setScale(gateScale);
        end.addPrevWire(wire7);

        end_finished.setPosition(new Point(808,285));
        end_finished.setScale(gateScale);

        bolt1.setPosition(new Point(-30,-20));
        bolt2.setPosition(new Point(40,-20));
        bolt3.setPosition(new Point(110,-20));
        bolt1.setScale(0.5);
        bolt2.setScale(0.5);
        bolt3.setScale(0.5);

        textbox1.setPosition(new Point(240,10));
        textbox2.setPosition(new Point(240,62));
        textbox1.setScaleY(48);
        textbox2.setScaleY(50);
        textbox1.setAlpha(0.5f);
        textbox2.setAlpha(0.5f);

        explosion.setPosition(new Point(200, -200));
        explosion.setScale(10);

        // Which wires can we click?
        ArrayList<wireSprite> clickables = new ArrayList<>();
        clickables.add(wire1);
        clickables.add(wire2);
        clickables.add(wire3);

        // Pass those to the mouseListener to check if they're clicked
        mf = new mouseFinder(clickables);
        this.getScenePanel().addMouseListener(mf);


    }

    public void setGameClock() {
        this.gameClock = new GameClock();
    }

    public void initGameClock() {
        if (this.gameClock == null)
            setGameClock();
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

        if(!over) {

            if(timeLeft > 0) {
                timeLeft = (int) Math.floor(totalTime + resetTime - this.gameClock.getElapsedTime() / 1000);
            }

            // We can change the key but on pressing G, figure out if the end is True!
            if (pressedKeys.contains(KeyEvent.VK_G)) {
                if (this.gameClock.getElapsedTime() - this.keyBufferTimer > 300) {
                    endVal = end.calculateValue();
                    if (endVal.equals("-"))
                        result = "You forgot set a wire!";
                    else if (endVal.equals("T")) {
                        result = "Nice! You defused the bomb with " + timeLeft + " seconds\nleft!";
                        over = true;
                        SM.PlaySoundEffect("Win");
                    }
                    else if (endVal.equals("F")) {
                        result = "Not quite, try again!";
                        lives--;
                        SM.PlaySoundEffect("LoseLife");
                        if(lives == 0){
                            over = true;
                            result = "Oh no! Press R to try again.";
                        }
                    }

                    this.keyBufferTimer = this.gameClock.getElapsedTime();
                }
            }

            if (timeLeft <= 0){
                over = true;
                endVal = "F";
            }

            /* If a wire changes, make sure it's image linked is correct */
            if (wire1 != null)
                wire1.fixWireImg();

            if (wire2 != null)
                wire2.fixWireImg();

            if (wire3 != null)
                wire3.fixWireImg();

            if (wire4 != null)
                wire4.fixWireImg();

            if (wire5 != null)
                wire5.fixWireImg();

            if (wire6 != null)
                wire6.fixWireImg();

            if (wire7 != null)
                wire7.fixWireImg();
        }

        if (pressedKeys.contains(KeyEvent.VK_R)) {
            for (Sprite sp : sprites) {
                sp.reset();
                endVal = "None";
                lives = 3;
                over = false;
                timeLeft = 30;
                result = "Press G to test your solution!";
                resetTime = this.gameClock.getElapsedTime() / 1000;
                explosionPlayed = false;
            }
        }

        if(over && endVal.equals("F")){
            result = "Oh no, the bomb exploded! Press R to retry.";
            if(!explosionPlayed){
                SM.PlaySoundEffect("Explosion");
                explosionPlayed = true;
            }
        }

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

        if (wire2 != null && wire2.getVisible())
            wire2.draw(g);

        if (wire3 != null && wire3.getVisible())
            wire3.draw(g);

        if (wire4 != null && wire4.getVisible())
            wire4.draw(g);

        if (wire5 != null && wire5.getVisible())
            wire5.draw(g);

        if (wire6 != null && wire6.getVisible())
            wire6.draw(g);

        if (wire7 != null && wire7.getVisible())
            wire7.draw(g);

        if (gate1 != null && gate1.getVisible())
            gate1.draw(g);

        if (gate2 != null && gate2.getVisible())
            gate2.draw(g);

        if (gate3 != null && gate3.getVisible())
            gate3.draw(g);

        if (gate4 != null && gate4.getVisible())
            gate4.draw(g);

        if (end != null && end.getVisible()) {
            if(!endVal.equals("T"))
                end.draw(g);
            else
                end_finished.draw(g);
        }

        if (lives >= 1 && bolt1 != null && bolt1.getVisible())
            bolt1.draw(g);

        if (lives >= 2 && bolt2 != null && bolt2.getVisible())
            bolt2.draw(g);

        if (lives >= 3 && bolt3 != null && bolt3.getVisible())
            bolt3.draw(g);

        if(over && endVal.equals("F") && explosion != null && explosion.getVisible()){
            explosion.draw(g);
        }

        if (textbox1 != null && textbox1.getVisible()) {
            textbox1.draw(g);
            textbox1.setScaleX(("Time Until Detonation: " + timeLeft).length() * 21);
        }

        if (textbox2 != null && textbox2.getVisible()) {
            textbox2.draw(g);
            if(over)
                textbox2.setScaleX(result.length() * 21);
            else
                textbox2.setScaleX(result.length() * 20);
        }

        g.setColor(Color.WHITE);

        if (endVal != null) {
            g.setFont(new Font("Arial", Font.PLAIN, 45));
            g.drawString(result, 250, 100);
        }

        g.drawString("Time Until Detonation: " + timeLeft, 250, 50);

        // Graphics2D g2d = (Graphics2D)g;
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        level2 game = new level2();
        game.start();

    }
}
