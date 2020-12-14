package com.my.game.tictactoe.service;

import com.my.game.tictactoe.model.Board;
import com.my.game.tictactoe.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.my.game.tictactoe.util.Constants.*;

/**
 * @author Vladimir
 * Implementation of game rules
 */
@Component
public class RuleEngineImpl {
    private static Logger logger = LoggerFactory.getLogger(RuleEngineImpl.class);

    @Autowired
    Board board;

    /**
     * Checking row, column and diagonals winning conditions for a given player
     * @param p
     * @return boolen is a winner
     */
    public boolean isWinner(char p) {
        // check all rows
        for (int row = 0; row < Constants.BOARD_DIM; row++) {
            boolean horizontal = true;
            for (int col = 0; col < Constants.BOARD_DIM; col++) {
                horizontal = horizontal && (p == board.getAt(row, col));
            }
            if (horizontal) {
                return true;
            }
        }

        // check all columns
        for (int col = 0; col < Constants.BOARD_DIM; col++) {
            boolean vertical = true;
            for (int row = 0; row < Constants.BOARD_DIM; row++) {
                vertical = vertical && (p == board.getAt(row, col));
            }
            if (vertical) {
                return true;
            }
        }

        // diagonal top left- bottom right
        boolean diagonal = true;
        for (int i = 0; i < Constants.BOARD_DIM; i++) {
            diagonal = diagonal && (p == board.getAt(i, i));
        }
        if (diagonal) {
            return true;
        }

        //diagonal top right- bottom left
        diagonal = true;
        for (int i = 0; i < Constants.BOARD_DIM; i++) {
            diagonal = diagonal && (p == board.getAt(i, BOARD_DIM - 1 - i));
        }
        if (diagonal) {
            return true;
        }

        return false;
    }

    /**
     * Checking if all move are played
     * @param turns
     * @return is board full
     */
    public boolean isFull(int turns) {
        return (turns == MAX_TURNS);
    }

    /**
     * Checking is the game over
     *
     * @param turns
     * @return is game over condition met
     */
    public boolean isGameOver(int turns) {
        return (isWinner(PLAYER_X) || isWinner(PLAYER_O) || isFull(turns));
    }

    /**
     * Checking if the move is valid
     *
     * @param r row
     * @param c column
     * @return is valid move
     */
    public boolean isValid(int r, int c) {
        return (0 <= r && r <= BOARD_DIM - 1 && 0 <= c && c <= BOARD_DIM - 1);
    }


    /**
     * Getting the player at given location
     * @param r row
     * @param c column
     * @return player or error code if the location is not valid.
     */
    public char playerAt(int r, int c) {
        return isValid(r, c) ? board.getAt(r, c) : ERROR_STATE;
    }

}
