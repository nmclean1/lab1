package edu.virginia.engine.display;

import java.util.ArrayList;

public class gateSprite extends Sprite {
    // What're the previous wires - for backwards computation
    private ArrayList<wireSprite> prevWires;
    // or? xor? and? not?
    private String gateType;
    // This can't be boolean, since some wires/gates will need to be unknown
    String value;


    public gateSprite(String id, String imgFileName, String gateType) {
        super(id,imgFileName);
        this.gateType = gateType;
        this.value = "?"; // Automatically set it to an unknown value
        this.prevWires = new ArrayList<>();
    }

    public ArrayList<wireSprite> getPrevWires() {
        return prevWires;
    }

    public void addPrevWire(wireSprite next) {
        this.prevWires.add(next);
    }

    public String getGateType() {
        return gateType;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
