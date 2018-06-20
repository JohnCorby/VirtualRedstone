package com.johncorby.virtualredstone.table;

import com.johncorby.virtualredstone.util.Class;
import com.johncorby.virtualredstone.util.MultiIdentifiable;
import org.bukkit.Location;

import java.util.Objects;

public class Output extends MultiIdentifiable {
    private boolean powering = false;

    public Output(Location loc, Static tab, int inst, int num) {
        super(loc, tab, inst, num);
    }

    public static Output get(Location loc, Static tab, int inst, int num) {
        return (Output) get(Output.class, loc, tab, inst, num);
    }

    @Override
    protected boolean create(Object... identities) {
        for (Class c : classes) {
            if (!getClass().equals(c.getClass())) continue;
            Object[] i = ((Output) c).get();
            if (Objects.equals(i[1], identities[1]) &&
                    Objects.equals(i[2], identities[2]) &&
                    Objects.equals(i[3], identities[3])) return false;
        }
        return super.create(identities);
    }

    public void set(boolean powering) {
        if (powering == this.powering) return;
        this.powering = powering;
        debug("Powering = " + powering);
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
