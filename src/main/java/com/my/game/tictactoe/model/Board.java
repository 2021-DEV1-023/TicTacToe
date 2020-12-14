package com.my.game.tictactoe.model;

import com.my.game.tictactoe.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.my.game.tictactoe.util.Constants.BOARD_DIM;
import static com.my.game.tictactoe.util.Constants.EMPTY_FIELD;

/**
 * @author Vladimir
 * <p>
 * Board model
 */
@Component
public class Board {
    private static Logger logger = LoggerFactory.getLogger(Board.class);

    private char[][] board = new char[BOARD_DIM][BOARD_DIM];

    public Board() {
        init();
    }

    private void init() {
        logger.info("Starting board initialization");
        for (int row = 0; row < Constants.BOARD_DIM; row++) {
            for (int col = 0; col < Constants.BOARD_DIM; col++) {
                this.board[row][col] = EMPTY_FIELD;
            }
        }
        logger.info("The board is initialized.");
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public int getRowDim() {
        return this.board.length;
    }

    public int getColDim() {
        return this.board[0].length;
    }

    public char getAt(int r, int c) {
        return this.board[r][c];
    }

    public void putAt(char c, int row, int col) {
        this.board[row][col] = c;
    }

    public void displayBoard()
    {
        System.out.println(" " + board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println("--+-+--");
        System.out.println(" " + board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println("--+-+--");
        System.out.println(" " + board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
        System.out.println();
    }
}
