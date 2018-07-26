package com.johncorby.virtualredstone.sequencer;

import com.johncorby.coreapi.util.Runnable;
import com.johncorby.virtualredstone.circuit.Static;

public class Instance extends com.johncorby.virtualredstone.circuit.Instance {
    private Task task = new Task();

    public Instance(Integer identity, Static parent) {
        super(identity, parent);
    }

    private void run() {
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
