package edu.virginia.engine.display;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.awt.image.BufferedImage;
import edu.virginia.engine.display.Animation;
import edu.virginia.engine.util.GameClock;

/**
 * Nothing in this class (yet) because there is nothing specific to a Sprite yet that a DisplayObject
 * doesn't already do. Leaving it here for convenience later. you will see!
 * */
public class Sprite extends DisplayObjectContainer {

	public Sprite(String id) {
		super(id);
	}

	public Sprite(String id, String imageFileName) {
		super(id, imageFileName);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
	}
}