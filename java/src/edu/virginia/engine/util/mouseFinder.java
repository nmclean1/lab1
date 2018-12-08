package edu.virginia.engine.util;

import edu.virginia.engine.display.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class mouseFinder implements MouseListener {
    // All clickable wires in the level
    ArrayList<wireSprite> clickables;
    SoundManager SM = new SoundManager();

    // Constructor
    public mouseFinder(ArrayList<wireSprite> clickables) {
        this.clickables = clickables;
        SM.LoadSoundEffect("Click", "Click.wav");
    }

    // Run every time the mouse is clicked
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        // Iterate through the ArrayList, checking if the click is within any hitbox
        for (wireSprite wire : clickables) {
            // If it is, advance that wire
            if (wire.getHitbox().contains(x,y)) {
                SM.PlaySoundEffect("Click");
                wire.advValue();
            }
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
