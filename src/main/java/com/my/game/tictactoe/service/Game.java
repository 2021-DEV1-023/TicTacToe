package com.my.game.tictactoe.service;

import com.my.game.tictactoe.io.UserInput;
import com.my.game.tictactoe.model.Board;
import com.my.game.tictactoe.model.GameOverStatus;
import com.my.game.tictactoe.model.Location;
import com.my.game.tictactoe.model.MoveStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.my.game.tictactoe.util.Constants.*;

/**
 * @author Vladimir
 * <p>
 * Game servise
 */
@Component
public class Game extends BaseGame {
    private static Logger logger = LoggerFactory.getLogger(Game.class);

    @Autowired
    Board board;

    @Autowired
    UserInput userInput;

    @Autowired
    RuleEngineImpl ruleEngine;

    private int turns = 0;

    /**
     * last played move coordinates
     */
    int inputRow, inputColumn;

    /**
     * Current player on the move
     **/
    char player = PLAYER_X;

    public void loadGame(Board board, char player, int turns) {
        this.board = board;
        this.player = player;
        this.turns = turns;
    }

    @Override
    public GameOverStatus start() {
        logger.info("The game has begun");
        return runMainLoop();
    }

    public int getLastPlayedRow() {
        return inputRow;
    }

    public int getLastPlayedColumn() {
        return inputColumn;
    }

    /**
     * The main game loop
     *
     * @return GameOverStatus
     */
    private GameOverStatus runMainLoop() {
        logger.info("Starting main loop...");

        while (1 == 1) {
            if (!ruleEngine.isGameOver(turns)) {
                player = playGame(player);
            } else {
                break; // game is over exit loop
            }
        }
        board.displayBoard();
        char winner = displayGameOverMessage();

        return new GameOverStatus(GAME_OVER_CODE, winner);
    }

    /**
     * Executing a play move for a given player
     *
     * @param player
     * @return next player to move
     */
    private char playGame(char player) {
        board.displayBoard();
        inputPlayerLocation(player);
        MoveStatus moveStatus = resolveMoveStatus();
        while (moveStatus.isInvalid()) {
            if (!moveStatus.isValid()) {
                System.out.println("That is not a valid location. Try again.");
            } else if (!moveStatus.isEmpty()) {
                System.out.println("That location is already full. Try again.");
            }
            inputPlayerLocation(player);
            moveStatus = resolveMoveStatus();
        } // end invalid move loop
        this.playerMove(player, inputRow, inputColumn); // perform move
        player = (player == PLAYER_X) ? PLAYER_O : PLAYER_X; // switch to next player
        return player;
    }

    /**
     * Getting a user input for a given player
     *
     * @param player
     */
    private void inputPlayerLocation(char player) {
        Location playedLocation = userInput.getLocationInput(player);
        inputRow = playedLocation.getRow();
        inputColumn = playedLocation.getColumn();
    }

    /**
     * Placing a move for a player at specified location
     *
     * @param p player
     * @param r row
     * @param c column
     */
    private void playerMove(char p, int r, int c) {
        if (turns <= MAX_TURNS) {
            board.putAt(p, r, c);
            turns++;
        }
    }

    /**
     * Validating the played move
     *
     * @return MoveStatus
     */
    private MoveStatus resolveMoveStatus() {
        return new MoveStatus(ruleEngine.isValid(inputRow, inputColumn),
                ruleEngine.playerAt(inputRow, inputColumn) == EMPTY_FIELD);
    }

    /**
     * Displaying the board
     *
     * @return the winners code or t for draw
     */
    private char displayGameOverMessage() {
        char winner;
        if (ruleEngine.isWinner(PLAYER_X)) {
            System.out.println("X is the winner!");
            winner = PLAYER_X;
        } else if (ruleEngine.isWinner(PLAYER_O)) {
            System.out.println("O is the winner!");
            winner = PLAYER_O;
        } else {
            System.out.println("The game is a tie.");
            winner = DRAW;
        }
        return winner;
    }

}
