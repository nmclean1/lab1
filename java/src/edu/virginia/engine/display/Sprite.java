package edu.virginia.engine.display;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;

/**
 * Nothing in this class (yet) because there is nothing specific to a Sprite yet that a DisplayObject
 * doesn't already do. Leaving it here for convenience later. you will see!
 * */
public class Sprite extends DisplayObject {

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

public class AnimatedSprite extends Sprite {

	private ArrayList<animation> animations;
	private boolean playing;
	private String fileName;
	private ArrayList<BufferedImage> frames;
	private int currentFrame;
	private int startFrame;
	private int endFrame;
	private static final int DEFAULT_ANIMATION_SPEED = 1;
	private int animationSpeed;
	private Timer gameClock;

	public AnimatedSprite(String id, String fileName, int position) {
		super(id);
		this.setFileName(fileName);
		this.setPosition(position);
		this.setGameClock();
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void setPosition(Point pos) {
		super.setPosition(pos);
	}

	public void setGameClock() {
		this.gameClock = new Timer(DEFAULT_ANIMATION_SPEED, this);
	}

	public void initGameClock() {
		if (this.gameClock == null)
			setGameClock();
	}
}