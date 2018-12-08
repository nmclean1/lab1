package edu.virginia.engine.display;

import javax.swing.plaf.TabbedPaneUI;
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

    public String calculateValue() {
        if (value.equals("?"))
        {
            /* No need to check for null in previous wires, because you can't start with a gate
            *  But we do need to figure out the final output of the gate given inputs
            *  We use '-' as the error sign to pass back, if end returns an error, we fail
            */
            if (gateType.equals("and")) {
                boolean Fpresent = false;
                for (wireSprite w : prevWires) {
                    String val = w.calculateValue();
                    if (val.equals("-"))
                        return "-";
                    else if (val.equals("F"))
                        Fpresent = true;
                }
                if (Fpresent)
                    return "F";
                return "T";
            }
            else if (gateType.equals("or")) {
                boolean Tpresent = false;
                for (wireSprite w : prevWires) {
                    String val = w.calculateValue();
                    if (val.equals("-"))
                        return "-";
                    else if (val.equals("T"))
                        Tpresent = true;
                }
                if (Tpresent)
                    return "T";
                return "F";
            }
            else if (gateType.equals("not")){
                // Here since we design the levels we can assume only a single wire goes into a NOT gate
                String val = prevWires.get(0).calculateValue();
                if (val.equals("T"))
                    return "F";
                else if (val.equals("F"))
                    return "T";
                else
                    return "-";
            }
            else if (gateType.equals("xor")) {
                // There are different arguments for xor with more than 2 inputs, but we choose exclusivity
                int count = 0;
                for (wireSprite w : prevWires) {
                    String val = w.calculateValue();
                    if (val.equals("T")) {
                        count++;
                        continue;
                    }
                    else if (val.equals("-"))
                        return "-";
                }
                if (count == 1)
                    return "T";
                else
                    return "F";
            }
            else if (gateType.equals("end")) {
                // Again, we can assume that only one wire leads to the end
                return prevWires.get(0).calculateValue();
            }
            else
                throw new IllegalArgumentException("Invalid gate intialization");
        }
        else {
            return value;
        }
    }

    @Override
    public void reset() {
        value = "?";
    }
}
