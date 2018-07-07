package com.johncorby.virtualredstone.sequencer;

import com.johncorby.virtualredstone.circuit.Static;
import com.johncorby.virtualredstone.util.Runnable;

public class Instance extends com.johncorby.virtualredstone.circuit.Instance {
    protected Task task;

    public Instance(Integer identity, Static parent) {
        super(identity, parent);
    }

    @Override
    protected boolean create(Integer identity, Static parent) {
        if (!super.create(identity, parent)) return false;
        task = new Task();
        return true;
    }

    protected void run() {

    }

    @Override
    public boolean dispose() {
        if (!task.isCancelled()) task.cancel();
        return stored();
    }

    protected final class Task extends Runnable {
        @Override
        public final void run() {
            Instance.this.run();
        }

        @Override
        public final synchronized void cancel() {
            super.cancel();
            Instance.super.dispose();
        }
    }
}
