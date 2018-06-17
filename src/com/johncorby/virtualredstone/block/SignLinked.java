package com.johncorby.virtualredstone.block;

import com.johncorby.virtualredstone.util.IdentifiableArray;
import org.bukkit.block.Block;
import org.bukkit.material.Sign;

public class SignLinked extends IdentifiableArray<Block> {
    public SignLinked(Block block) {
        super(block);
    }

    public static SignLinked get(Block... identities) {
        return (SignLinked) get(SignLinked.class, identities);
    }

    @Override
    protected boolean create(Block... identities) {
        Block b = identities[0];
        Sign s = (Sign) b.getState().getData();
        Block a = b.getRelative(s.getAttachedFace());
        return super.create(b, a);
    }

    public org.bukkit.block.Sign getSign() {
        return (org.bukkit.block.Sign) get()[0];
    }

    public Block getAttached() {
        return get()[1];
    }

    @Override
    protected int getUnavailable(Block[] identities) {
        if (!(identities[0].getState().getData() instanceof Sign)) return 0;
        return super.getUnavailable(identities);
    }
}
