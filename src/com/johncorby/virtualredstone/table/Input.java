package com.johncorby.virtualredstone.table;

import com.johncorby.virtualredstone.util.Class;
import com.johncorby.virtualredstone.util.MultiIdentifiable;
import org.bukkit.Location;

import java.util.Objects;

public class Input extends MultiIdentifiable {
    private boolean powered = false;

    public Input(Location loc, Static tab, int inst, int num) {
        super(loc, tab, inst, num);
    }

    public static Input get(Location loc, Static tab, int inst, int num) {
        return (Input) get(Input.class, loc, tab, inst, num);
    }

    @Override
    protected boolean create(Object... identities) {
        for (Class c : classes) {
            if (!getClass().equals(c.getClass())) continue;
            Object[] i = ((Input) c).get();
            if (Objects.equals(i[1], identities[1]) &&
                    Objects.equals(i[2], identities[2]) &&
                    Objects.equals(i[3], identities[3])) return false;
        }
        return super.create(identities);
    }

    public void set(boolean powered) {
        if (powered == this.powered) return;
        this.powered = powered;
        debug("Powered = " + powered);
    }

    public Location getLoc() {
        return (Location) get()[0];
    }

    public Static getTab() {
        return (Static) get()[1];
    }

    public int getInst() {
        return (int) get()[2];
    }

    public int getNum() {
        return (int) get()[3];
    }
}
