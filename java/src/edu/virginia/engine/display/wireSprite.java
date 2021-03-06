package edu.virginia.engine.display;

import edu.virginia.engine.display.Sprite;

public class wireSprite extends Sprite {
    // To compute the final value backwards, we have to record the previous gate
    private gateSprite prevGate;
    // This can't be boolean, since some wires/gates will need to be unknown
    String value;
    String bend;

    public wireSprite(String id, String imgFileName, String bend) {
        super(id,imgFileName);
        this.value = "?";
        this.bend = bend;
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
        if (value.equals("T")) {
            this.setImage("true_wire_" + bend + ".png");
        }
        else if (value.equals("F")) {
            this.setImage("false_wire_" + bend + ".png");
        }
        else {
            this.setImage("unk_wire_" + bend + ".png");
        }
    }

    public String calculateValue() {
        if (prevGate == null) {
            // This is one of the clickable wires
            if (value.equals("?"))
                return "-";
            else
                return value;
        }
        else {
            value = prevGate.calculateValue();
            return value;
        }
    }

    @Override
    public void reset() {
        value = "?";
    }
}
