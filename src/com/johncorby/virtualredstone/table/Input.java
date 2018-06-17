package com.johncorby.virtualredstone.table;

import com.johncorby.virtualredstone.util.Class;
import com.johncorby.virtualredstone.util.MultiIdentifiable;
import org.bukkit.Location;

public class Input extends MultiIdentifiable {
    private boolean powered = false;

    public Input(Location loc, Table tab, int inst, int num) {
        super(loc, tab, inst, num);
    }

    public static Input get(Location loc, Table tab, int inst, int num) {
        return (Input) get(Input.class, loc, tab, inst, num);
    }

    @Override
    protected boolean create(Object... identities) {
        for (Class c : classes) {
            if (!getClass().equals(c.getClass())) continue;
            Object[] i = ((MultiIdentifiable) c).get();
            if (i[1] == identities[1] &&
                    i[2] == identities[2] &&
                    i[3] == identities[3]) return false;
        }
        return super.create(identities);
    }

    public void set(boolean powered) {
        if (powered == this.powered) return;
        this.powered = powered;
        debug("Powered = " + powered);
    }
}
