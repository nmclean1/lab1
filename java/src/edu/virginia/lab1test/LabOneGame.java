package edu.virginia.lab1test;

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
public class LabOneGame extends Game{

	/* Create a sprite object for our game. We'll use mario */
	AnimatedSprite mario = new AnimatedSprite("Mario", "mario", new Point(0,0));
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabOneGame() {
		super("Lab One Test Game", 500, 300);
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
		}

		if (pressedKeys.contains(KeyEvent.VK_DOWN)){
			mario.setPosition(new Point(mario.getPosition().x,
					mario.getPosition().y + 5));
		}

		if (pressedKeys.contains(KeyEvent.VK_LEFT)){
			mario.setPosition(new Point(mario.getPosition().x - 5,
					mario.getPosition().y));
		}

		if (pressedKeys.contains(KeyEvent.VK_RIGHT)){
			mario.setPosition(new Point(mario.getPosition().x + 5,
					mario.getPosition().y));
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
		}

		if (pressedKeys.contains(KeyEvent.VK_Q)){
			mario.setRotation(mario.getRotation() - 5);
		}

		if (pressedKeys.contains(KeyEvent.VK_V)){
			mario.setVisible(!mario.getVisible());
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
			}
		}

		if (pressedKeys.contains(KeyEvent.VK_S)){
			mario.setScaleX(mario.getScaleX() + 0.1);
			mario.setScaleY(mario.getScaleY() + 0.1);
		}
		
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if(mario != null && mario.getVisible())
			mario.draw(g);
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabOneGame game = new LabOneGame();
		game.start();

	}
}
