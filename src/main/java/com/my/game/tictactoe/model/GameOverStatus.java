package com.my.game.tictactoe.model;

public class GameOverStatus {
    final int exitCode;
    final char winner;

    public GameOverStatus(int exitCode, char winner) {
        this.exitCode = exitCode;
        this.winner = winner;
    }

    public int getExitCode() {
        return exitCode;
    }

    public char getWinner() {
        return winner;
    }
}
