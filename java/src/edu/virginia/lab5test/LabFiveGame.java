package edu.virginia.lab5test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.*;
import edu.virginia.engine.util.GameClock;

import org.w3c.dom.css.Rect;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabFiveGame extends Game{

    /* Create a sprite object for our game. We'll use mario */
    Sprite mario = new Sprite("Mario", "Mario.png");
    Sprite door = new Sprite("Door","Door.png");
    Sprite goomba = new Sprite("Goomba","Goomba.png");
    Sprite floor = new Sprite("Floor","Floor.png");
    private GameClock gameClock;
    private double visibilityTimer = 0;
    SoundManager SM = new SoundManager();
    boolean hitPlayed = false;
    int score = 1000;
    boolean youWin = false;
    double oldTime = 0;
    double newTime = 0;
    double deltaTime = 0;
    double g = 0.1;

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabFiveGame() {
        super("Game", 1300, 700);
        this.initGameClock();

        /* Sprite configuration */
        door.setPosition(new Point(700,300));
        goomba.setPosition(new Point(210,250));
        door.updateHitBox(700,300);
        goomba.updateHitBox(210,250);
        floor.setPosition(new Point(0,600));
        floor.updateHitBox(0,600);
        mario.setPhysics(true);
        mario.setUpVelocity(0.0);

        /* Sound effects/music */
        SM.LoadSoundEffect("Hit","Hit.wav");
        SM.LoadMusic("Music","Music.wav");
        SM.PlayMusic("Music");
    }

    public void setGameClock() {
        this.gameClock = new GameClock();
    }

    public void initGameClock() {
        if (this.gameClock == null)
            setGameClock();
    }

    // Is mario colliding with the object?
    public boolean collidesWith(DisplayObject other) {
        // Make sure the math is done in the same point system
        Shape hb = mario.getHitbox();
        Shape ohb = other.getHitbox();
        Point phb = this.localToGlobal(mario.getPosition());
        Point pohb = this.localToGlobal(other.getPosition());

    /*
        if (phb.x + hb.width > pohb.x && phb.x < pohb.x+ohb.width)
            System.out.println("WIDTH");

        if (phb.y + hb.height > pohb.y && phb.y < pohb.y+ohb.height)
            System.out.println("HEIGHT");
        */

        // Width is overlapping - height is overlapping
        return hb.intersects(pohb.x,pohb.y,other.getUnscaledWidth(),other.getUnscaledHeight());

        //System.out.println(phb.toString() + " Mario--goomba " + pohb.toString());
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);

        /* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if(mario != null) mario.update(pressedKeys);

        if (pressedKeys.contains(KeyEvent.VK_UP)){
            mario.setPosition(new Point(mario.getPosition().x,
                    mario.getPosition().y - 5));
            mario.updateHitBox(0,-5);
        }

        if (pressedKeys.contains(KeyEvent.VK_DOWN)){
            mario.setPosition(new Point(mario.getPosition().x,
                    mario.getPosition().y + 5));
            mario.updateHitBox(0,5);
        }

        if (pressedKeys.contains(KeyEvent.VK_LEFT)){
            mario.setPosition(new Point(mario.getPosition().x - 5,
                    mario.getPosition().y));
            mario.updateHitBox(-5,0);
        }

        if (pressedKeys.contains(KeyEvent.VK_RIGHT)){
            mario.setPosition(new Point(mario.getPosition().x + 5,
                    mario.getPosition().y));
            mario.updateHitBox(5,0);
        }

        if (pressedKeys.contains(KeyEvent.VK_I)){
            mario.setPivotPoint(new Point(mario.getPivotPoint().x,
                    mario.getPivotPoint().y - 5));
        }

        if (pressedKeys.contains(KeyEvent.VK_K)){
            mario.setPivotPoint(new Point(mario.getPivotPoint().x,
                    mario.getPivotPoint().y + 5));
        }

        if (pressedKeys.contains(KeyEvent.VK_J)){
            mario.setPivotPoint(new Point(mario.getPivotPoint().x - 5,
                    mario.getPivotPoint().y));
        }

        if (pressedKeys.contains(KeyEvent.VK_L)){
            mario.setPivotPoint(new Point(mario.getPivotPoint().x + 5,
                    mario.getPivotPoint().y));
        }

        if (pressedKeys.contains(KeyEvent.VK_W)){
            mario.setRotation(mario.getRotation() + 5);
            mario.updateHitBoxRotate(5);
        }

        if (pressedKeys.contains(KeyEvent.VK_Q)){
            mario.setRotation(mario.getRotation() - 5);
            mario.updateHitBoxRotate(-5);
        }

        if (pressedKeys.contains(KeyEvent.VK_V)){
            if(this.gameClock.getElapsedTime() - this.visibilityTimer > 300) {
                mario.setVisible(!mario.getVisible());
                this.visibilityTimer = this.gameClock.getElapsedTime();
            }
        }

        if (pressedKeys.contains(KeyEvent.VK_Z)){
            if(mario.getAlpha() > 0.05f) {
                mario.setAlpha(mario.getAlpha() - 0.05f);
            }
        }

        if (pressedKeys.contains(KeyEvent.VK_X)){
            if(mario.getAlpha() < 1.0f) {
                mario.setAlpha(mario.getAlpha() + 0.05f);
            }
        }

        if (pressedKeys.contains(KeyEvent.VK_A)){
            if(mario.getScaleX() > 0.2) {
                mario.setScaleX(mario.getScaleX() - 0.1);
                mario.setScaleY(mario.getScaleY() - 0.1);
                mario.updateHitBoxScale();
            }
        }

        if (pressedKeys.contains(KeyEvent.VK_S)){
            mario.setScaleX(mario.getScaleX() + 0.1);
            mario.setScaleY(mario.getScaleY() + 0.1);
            mario.updateHitBoxScale();
        }

        /* Physics */

        if(gameClock != null) {
            newTime = this.gameClock.getElapsedTime();
            deltaTime = newTime - oldTime;

            if (mario.hasPhysics && oldTime != 0) {
                mario.setUpVelocity(mario.getUpVelocity() + g * deltaTime);
                mario.setPosition(new Point(mario.getPosition().x,
                        (int) (mario.getPosition().y + mario.getUpVelocity())));
            }

            oldTime = newTime;
        }

        /* Collision-handling */


        if (mario != null && goomba != null && door != null) {
            if (collidesWith(goomba) && !hitPlayed && !youWin) {
                hitPlayed = true;
                SM.PlaySoundEffect("Hit");
                score -= 100;
            }

            if(!collidesWith(goomba)){
                hitPlayed = false;
            }


            if (collidesWith(door)) {
                youWin = true;
            }

            if (collidesWith(floor)) {
                mario.setUpVelocity(mario.getUpVelocity() * -0.75);
                System.out.println(mario.getUpVelocity());
            }

        }

        if (youWin)
            SM.getMusicHashmap().get("Music").stop();

    }

    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     * */
    @Override
    public void draw(Graphics g){
        super.draw(g);

        if (!youWin) {
            /* Same, just check for null in case a frame gets thrown in before Mario is initialized */
            if (mario != null && mario.getVisible())
                mario.draw(g);

            if (floor != null && floor.getVisible())
                floor.draw(g);

            if (goomba != null && goomba.getVisible())
                goomba.draw(g);

            if (door != null && door.getVisible())
                door.draw(g);

            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString(Integer.toString(score), 100, 100);
        }
        else {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g.drawString("Congratulations! You won with " + score, 500, 500);
        }

        // Graphics2D g2d = (Graphics2D)g;
    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        LabFiveGame game = new LabFiveGame();
        game.start();

    }
}
