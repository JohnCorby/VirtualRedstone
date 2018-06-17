package com.johncorby.virtualredstone.table;

import com.johncorby.virtualredstone.util.Identifiable;

public class Table extends Identifiable<String> {
    public Table(String identity) {
        super(identity);
    }

    public static Table get(String identity) {
        return (Table) get(Table.class, identity);
    }
}
