package com.johncorby.virtualredstone.circuit;

import com.johncorby.coreapi.util.Config;
import com.johncorby.coreapi.util.MessageHandler;
import com.johncorby.coreapi.util.storedclass.ConfigIdentNode;
import com.johncorby.coreapi.util.storedclass.IdentNode;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.johncorby.coreapi.CoreApiPlugin.PLUGIN;
import static com.johncorby.coreapi.util.Conversions.toInt;

public abstract class RedstoneSign extends ConfigIdentNode<Integer, Instance, IdentNode> {
    protected Sign sign;
    protected boolean active;

    public RedstoneSign(Sign sign, int identity, Instance parent) {
        super(identity, parent);
        this.sign = sign;
        create();
    }

    public RedstoneSign(@NotNull Map<String, Object> map) {
        super(map);
    }

    public static RedstoneSign newInstance(SignType type, Sign sign, int identity, Instance parent) {
        if (type == SignType.INPUT)
            return new Input(sign, identity, parent);
        return new Output(sign, identity, parent);
    }

    @Nullable
    public static RedstoneSign get(SignType type, int identity, @NotNull Instance parent) {
        if (type == SignType.INPUT)
            return get(Input.class, identity, parent);
        return get(Output.class, identity, parent);
    }

    // Get RedstoneSign from sign
    public static RedstoneSign get(@NotNull Sign sign) {
        SignType type = SignType.get(sign);
        if (type == null) {
            MessageHandler.warn("Line 1: invalid sign type");
            return null;
        }

        Instance inst = Instance.get(sign);
        if (inst == null) {
            MessageHandler.warn("Line 3: inst not found");
            return null;
        }

        Integer signNum = toInt(sign.getLine(3));
        if (signNum == null) {
            MessageHandler.warn("Line 4: invalid sign num");
            return null;
        }

        RedstoneSign redstoneSign = get(type, signNum, inst);
        if (redstoneSign == null) redstoneSign = newInstance(type, sign, signNum, inst);
        return redstoneSign;
    }

    public static void signPlace(@NotNull Sign sign) {
        get(sign);
    }

    public static void signBreak(@NotNull Sign sign) {
        // So you can break non-rs signs
        try {
            Objects.requireNonNull(get(sign)).configRemove();

            Instance inst = Objects.requireNonNull(Instance.get(sign));
            if (inst.getChildren().isEmpty())
                inst.dispose();
        } catch (NullPointerException e) {
            MessageHandler.warn(e);
        }
    }

    public static void signActivate(@NotNull Sign sign, boolean active) {
        Objects.requireNonNull(get(sign)).setActive(active);
    }

    public boolean isActive() {
        return active;
    }

    public abstract void setActive(boolean active);

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = super.serialize();
        map.put("Location", sign.getLocation());
        return map;
    }

    @Override
    public void deserialize(@NotNull Map<String, Object> map) {
        super.deserialize(map);
        Location l = (Location) map.get("Location");
        this.sign = (Sign) l.getBlock().getState();
    }

    @Override
    public void configAdd() {
        Config.addSet("Signs", this);
        PLUGIN.saveConfig();
    }

    @Override
    public boolean configRemove() {
        Config.removeSet("Signs", this);
        PLUGIN.saveConfig();
        return dispose();
    }

    @Override
    public List<String> getDebug() {
        List<String> r = super.getDebug();
        r.add("Active: " + isActive());
        return r;
    }


}
