package com.johncorby.virtualredstone.table;

import com.johncorby.virtualredstone.util.Identifiable;

import javax.annotation.Nonnull;

public class Table extends Identifiable<String> {
    public Table(@Nonnull String identity) {
        super(identity);
    }
}
