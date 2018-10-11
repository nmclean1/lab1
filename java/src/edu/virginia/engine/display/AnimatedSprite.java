package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite {

    private ArrayList<Animation> animations;
    private boolean playing;
    private String fileName;
    private ArrayList<BufferedImage> frames;
    private int currentFrame;
    private int startFrame;
    private int endFrame;
    private static final int DEFAULT_ANIMATION_SPEED = 1;
    private int animationSpeed;
    private GameClock gameClock;

    public AnimatedSprite(String id, String fileName, Point position) {
        super(id);
        this.setFileName(fileName);
        this.setPosition(position);
        this.setGameClock();
        this.setAnimationSpeed(DEFAULT_ANIMATION_SPEED);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void setPosition(Point pos) {
        super.setPosition(pos);
    }

    public void setGameClock() {
        this.gameClock = new GameClock();
    }

    public void setAnimationSpeed(int speed) {
        this.animationSpeed = speed;
    }

    public void setCurrentFrame(int frame) {
        this.currentFrame = frame;
    }

    public void setAnimations(ArrayList<Animation> anims) {
        this.animations = anims;
    }

    public void initGameClock() {
        if (this.gameClock == null)
            setGameClock();
    }

    private void initializeFrames(int numSprites) {
        for (int i = 0; i < numSprites; i++) {
            frames.add(readImage(fileName + "_" + Integer.toString(i) + ".png"));
        }
    }

    public Animation getAnimation(String id) {
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i).getId() == id) {
                return animations.get(i);
            }
        }
        return null;
    }

    private void animate(Animation a) {
        this.startFrame = a.getStartFrame();
        this.endFrame = a.getEndFrame();
    }

    private void animate(String id) {
        Animation a = getAnimation(id);
        this.startFrame = a.getStartFrame();
        this.endFrame = a.getEndFrame();
    }

    private void animate(int startFrame, int endFrame) {
        this.startFrame = startFrame;
        this.endFrame = endFrame;
    }

    private void stopAnimation(int frameNumber) {
        if (this.playing) {
            this.setCurrentFrame(frameNumber);
            this.playing = false;
        }
    }

    private void stopAnimation() {
        stopAnimation(this.startFrame);
    }

    @Override
    public void draw(Graphics g) {
        // If it's playing and the right amount of time has passed, iterate through the arrayList
        if (!(this.playing && (this.gameClock.getElapsedTime() >= this.animationSpeed)))
            return;
        else if (this.currentFrame == this.endFrame)
            this.setCurrentFrame(this.startFrame);
        else
            this.setCurrentFrame(this.currentFrame++);

        // Reset the gameClock only on image change
        this.gameClock.resetGameClock();

        // Find the current frame's animation's ID, then get the proper image from that
        super.draw(g);
    }

}