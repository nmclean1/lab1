package edu.virginia.engine.display;

public class Animation {
    private String id;
    private int startFrame;
    private int endFrame;

    public Animation(String id, int startFrame, int endFrame) {
        this.id = id;
        this.startFrame = startFrame;
        this.endFrame = endFrame;
    }

    public void setEndFrame(int endFrame) {
        this.endFrame = endFrame;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStartFrame(int startFrame) {
        this.startFrame = startFrame;
    }

    public int getStartFrame() {
        return startFrame;
    }

    public int getEndFrame() {
        return endFrame;
    }

    public String getId(){
        return id;
    }
}
