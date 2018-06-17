package com.johncorby.virtualredstone.table;

import com.johncorby.virtualredstone.util.Class;
import com.johncorby.virtualredstone.util.MultiIdentifiable;
import org.bukkit.Location;

public class Output extends MultiIdentifiable {
    private boolean powering = false;

    public Output(Location loc, Table tab, int inst, int num) {
        super(loc, tab, inst, num);
    }

    public static Output get(Location loc, Table tab, int inst, int num) {
        return (Output) get(Output.class, loc, tab, inst, num);
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

    public void set(boolean powering) {
        if (powering == this.powering) return;
        this.powering = powering;
        debug("Powering = " + powering);
    }
}
