package edu.virginia.engine.display;

import edu.virginia.engine.display.Sprite;

public class wireSprite extends Sprite {
    // To compute the final value backwards, we have to record the previous gate
    private gateSprite prevGate;
    // This can't be boolean, since some wires/gates will need to be unknown
    String value;

    public wireSprite(String id, String imgFileName) {
        super(id,imgFileName);
        this.value = "?";
    }

    public gateSprite getPrevGate() {
        return this.prevGate;
    }

    public void setPrevGate(gateSprite gate) {
        this.prevGate = gate;
    }

    // To be called on clicking a clickable wire, advances the value
    public void advValue() {
        if (value.equals("T"))
            value = "F";
        else // Iterate unknown wires to true arbitrarily
            value = "T";
    }

    public void fixWireImg() {
        if (value.equals("T"))
            this.setImage("true_wire.png");
        else if (value.equals("F"))
            this.setImage("false_wire.png");
        else
            this.setImage("unk_wire.png");
    }
}
