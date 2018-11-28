package edu.virginia.engine.util;

import edu.virginia.engine.display.Sprite;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class mouseFinder implements MouseListener {
    // All clickable wires in the level
    ArrayList<Sprite> clickables;

    // Constructor
    public mouseFinder(ArrayList<Sprite> clickables) {
        this.clickables = clickables;
    }

    // Run every time the mouse is clicked
    public void mouseClicked(MouseEvent e) {
        System.out.println("clicking!");

        int x = e.getX();
        int y = e.getY();

        // Iterate through the ArrayList, checking if the click is within any hitbox
        for (Sprite wire : clickables) {
            if (wire.getHitbox().contains(x,y))
                System.out.println("within bounds");
        }
    }

    /* Likely unused */

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
