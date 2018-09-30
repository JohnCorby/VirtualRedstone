package com.johncorby.virtualredstone.circuit;

import com.johncorby.coreapi.util.Common;
import com.johncorby.coreapi.util.MessageHandler;
import com.johncorby.coreapi.util.storedclass.ConfigIdentNode;
import org.bukkit.block.Sign;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;

import static com.johncorby.coreapi.util.Common.*;

public class Instance extends ConfigIdentNode<Integer, Circuit, RedstoneSign> {
    public Instance(int identity, Circuit parent) {
        super(identity, parent);
        create();
    }

    public Instance(@NotNull Map<String, Object> map) {
        super(map);
    }

    @Nullable
    public static Instance get(int identity, @NotNull Circuit parent) {
        return get(Instance.class, identity, parent);
    }

    // Get Instance from sign
    @Nullable
    public static Instance get(@NotNull Sign sign) {
        Circuit circuit = Circuit.get(sign);
        if (circuit == null) {
            MessageHandler.warn("Line 2: circuit not found");
            return null;
        }

        Integer instNum = Common.toInt(sign.getLine(2));
        if (instNum == null) {
            MessageHandler.warn("Line 3: invalid inst num");
            return null;
        }

        Instance inst = Instance.get(instNum, circuit);
        if (inst == null) inst = new Instance(instNum, circuit);
        return inst;
    }

    @Override
    public void configAdd() {
    }

    @Override
    public boolean configRemove() {
        return dispose();
    }

    @NotNull
    public Set<Input> getInputs() {
        return toSet(map(filter(getChildren(), c -> c instanceof Input), c -> (Input) c));
    }

    @NotNull
    public Set<Output> getOutputs() {
        return toSet(map(filter(getChildren(), c -> c instanceof Output), c -> (Output) c));
    }

    public void onActiveStateChanged(SignType type, int signNum, boolean active) {
        if (type != SignType.INPUT) return;
        getOutputs().forEach(o -> o.setActive(active));
    }
}
