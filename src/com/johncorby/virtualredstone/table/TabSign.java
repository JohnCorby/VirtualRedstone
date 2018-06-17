package com.johncorby.virtualredstone.table;

import com.johncorby.virtualredstone.block.SignLink;
import org.bukkit.Location;

public class TabSign extends SignLink {
    public TabSign(Location identity) {
        super(identity);
    }

    public static TabSign get(Location identity) {
        return (TabSign) get(TabSign.class, identity);
    }
}
