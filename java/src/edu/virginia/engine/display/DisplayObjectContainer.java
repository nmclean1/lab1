package edu.virginia.engine.display;

import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject {

    private ArrayList<DisplayObject> children;

    public DisplayObjectContainer(String id) {
        super(id);
    }

    public DisplayObjectContainer(String id, String imageFileName) {
        super(id,imageFileName);
    }

    public void addChild(DisplayObject child) {
        this.children.add(child);
    }

    public void addChildAtIndex(DisplayObject child, int index) {
        this.children.add(index, child);
    }

    public void removeChild(DisplayObject child) {
        this.children.remove(child);
    }

    public void removeByIndex(int index) {
        this.children.remove(index);
    }

    public void removeAll() {
        this.children = new ArrayList<DisplayObject>();
    }

}
