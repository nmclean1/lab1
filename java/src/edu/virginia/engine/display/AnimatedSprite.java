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
        this.initGameClock();
        this.setPlaying(true);
        this.setAnimationSpeed(DEFAULT_ANIMATION_SPEED);
        this.initializeFrames(4);
        this.setAnimations(id,0,3);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void setPosition(Point pos) {
        super.setPosition(pos);
    }

    public void setPlaying(Boolean playing) {
        this.playing = playing;
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

    public String getFileName() {
        return this.fileName;
    }

    public void setAnimations(String id, int startFrame, int endFrame) {
        ArrayList<Animation> anims = new ArrayList<Animation>();
        this.animations = anims;
        this.animations.add(new Animation(id,startFrame,endFrame));
    }

    public void initGameClock() {
        if (this.gameClock == null)
            setGameClock();
    }

    private void initializeFrames(int numSprites) {
        this.frames = new ArrayList<BufferedImage>();
        for (int i = 0; i < numSprites; i++) {
            frames.add(readImage(this.fileName + "_" + Integer.toString(i) + ".png"));
        }
        this.startFrame = 0;
        this.currentFrame = 0;
        this.endFrame = numSprites;
    }

    public Animation getAnimation(String id) {
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i).getId() == id) {
                return animations.get(i);
            }
        }
        return animations.get(0);
    }

    private void animate(Animation a) {
        this.startFrame = a.getStartFrame();
        this.endFrame = a.getEndFrame();
    }

    private void animate(String id) {
        Animation a = this.getAnimation(id);
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
        if (this.playing && (this.gameClock.getElapsedTime() >= this.animationSpeed)) {
            if (this.currentFrame == (this.endFrame - 1))
                this.setCurrentFrame(this.startFrame);
            else
                this.setCurrentFrame(this.currentFrame + 1);
        }
        else
            return;

        // Reset the gameClock only on image change
        this.gameClock.resetGameClock();

        // Updates the image to the current frame
        super.setImage(this.getFileName() + "_" + this.currentFrame + ".png");

        //System.out.println(this.currentFrame + "-" + this.playing);

        // Find the current frame's animation's ID, then get the proper image from that
        super.draw(g);
    }

}