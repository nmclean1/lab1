package edu.virginia.engine.display;

import com.sun.prism.shader.DrawCircle_ImagePattern_Loader;

import java.util.ArrayList;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DisplayObjectContainer extends DisplayObject {

    private ArrayList<DisplayObject> children;

    public DisplayObjectContainer(String id) {
        super(id);
        this.children = new ArrayList<DisplayObject>();
    }

    public DisplayObjectContainer(String id, String imageFileName) {
        super(id,imageFileName);
        this.children = new ArrayList<DisplayObject>();
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

    public boolean contains(DisplayObject obj) {
        return this.children.contains(obj);
    }

    // Can return null if there is no child with that id!
    public DisplayObject getChild(String id) {
        int i;
        for (i=0;i<this.children.size();i++) {
            if (this.children.get(i).getId() == id) {
                return this.children.get(i);
            }
        }

        return null;
    }

    public DisplayObject getChild(int index) {
        return this.children.get(index);
    }

    public ArrayList<DisplayObject> getChildren() {
        return this.children;
    }

    public Point localToGlobal(Point p){
        DisplayObject parent = this.getParent();
        if(parent.getId() == "Game"){
            return p;
        }
        else{
            Point parentPosition = parent.getPosition();
            return new Point((int) (p.getX() + localToGlobal(parentPosition).getX()), (int) (p.getY() + localToGlobal(parentPosition).getY()));
        }
    }

    public Point globalToLocal(Point p){
        DisplayObject parent = this.getParent();
        if(parent.getId() == "Lab Three Test Game"){
            return p;
        }
        else{
            Point parentPosition = parent.getPosition();
            return new Point((int) (p.getX() - localToGlobal(parentPosition).getX()), (int) (p.getY() - localToGlobal(parentPosition).getY()));
        }
    }

    /* Draw and update DO NOT protect against infinite loops, you MAY put yourself inside your children!
     * Don't do this though, that's dumb */

    @Override public void draw(Graphics g) {
        super.draw(g);

        Graphics2D g2d = (Graphics2D) g;

        applyTransformations(g2d);
        int i;

        for (i=0;i < this.children.size();i++) {
            this.children.get(i).draw(g);
        }

        reverseTransformations(g2d);
    }

    @Override public void update(ArrayList<Integer> pressedKeys) {
        super.update(pressedKeys); // Update myself

        int i;
        for (i=0;i <this.children.size();i++) {
            this.children.get(i).update(pressedKeys); // Update my children
        }
    }

}
