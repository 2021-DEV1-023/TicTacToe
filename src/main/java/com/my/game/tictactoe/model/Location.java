package com.my.game.tictactoe.model;

public class Location {
    private final int row;
    private final int column;

    private Location() {
        this.row = -1;
        this.column = -1;
    }

    public Location(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
