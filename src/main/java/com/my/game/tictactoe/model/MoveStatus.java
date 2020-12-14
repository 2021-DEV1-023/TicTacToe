package com.my.game.tictactoe.model;

public class MoveStatus {
    private final boolean isValid;
    private final boolean isEmpty;

    private MoveStatus() {
        //no-op
        this.isEmpty = false;
        this.isValid = false;
    }

    public MoveStatus(boolean isValid, boolean isEmpty) {
        this.isEmpty = isEmpty;
        this.isValid = isValid;
    }
    public boolean isValid() {
        return isValid;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isInvalid() {
        return !isValid || !isEmpty;
    }
}
