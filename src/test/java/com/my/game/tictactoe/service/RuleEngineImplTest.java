package com.my.game.tictactoe.service;

import com.my.game.tictactoe.model.Board;
import com.my.game.tictactoe.util.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static com.my.game.tictactoe.util.Constants.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RuleEngineImplTest {

    @Mock
    Board board;

    @InjectMocks
    @Autowired
    private RuleEngineImpl ruleEngine;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
        reset(board);
    }

    @Test
    public void isNotFullTest() {
        Assert.assertFalse("Must be full",ruleEngine.isFull(1));
    }

    @Test
    public void isFullTest() {
        Assert.assertTrue("Must be full",ruleEngine.isFull(Constants.MAX_TURNS));
    }

    @Test
    public void isValidMoveTest() {
        Assert.assertTrue("Must be full",ruleEngine.isValid(1,1));
    }

    @Test
    public void isNotRowValidMoveTest() {
        Assert.assertFalse("Must be full",ruleEngine.isValid(10,1));
    }

    @Test
    public void isNotColValidMoveTest() {
        Assert.assertFalse("Must be full",ruleEngine.isValid(1,10));
    }

    @Test
    public void isWinnerXRowTest() {
        for (int row = 0; row < Constants.BOARD_DIM; row++) {
            for (int col = 0; col < Constants.BOARD_DIM; col++) {
                when(board.getAt(row,col)).thenReturn(PLAYER_X);
            }
            Assert.assertTrue("X is winner", ruleEngine.isWinner(PLAYER_X));
            verifyColCalls(row);
            reset(board);
        }// end loop


    }

    @Test
    public void isWinnerORowTest() {
        for (int row = 0; row < Constants.BOARD_DIM; row++) {
            for (int col = 0; col < Constants.BOARD_DIM; col++) {
                when(board.getAt(row,col)).thenReturn(PLAYER_O);
            }
            Assert.assertTrue("O is winner", ruleEngine.isWinner(PLAYER_O));
            verifyColCalls(row);
            reset(board);
        }// end loop
    }

    @Test
    public void isWinnerXColTest() {
        for (int col = 0; col < Constants.BOARD_DIM; col++) {
            for (int row = 0; row < Constants.BOARD_DIM; row++) {
                when(board.getAt(row,col)).thenReturn(PLAYER_X);
            }
            Assert.assertTrue("X is winner", ruleEngine.isWinner(PLAYER_X));
            verifyRowCalls(col);
            reset(board);
        }// end loop
    }

    @Test
    public void isWinnerOColTest() {
        for (int col = 0; col < Constants.BOARD_DIM; col++) {
            for (int row = 0; row < Constants.BOARD_DIM; row++) {
                when(board.getAt(row,col)).thenReturn(PLAYER_O);
            }
            Assert.assertTrue("O is winner", ruleEngine.isWinner(PLAYER_O));
            verifyRowCalls(col);
            reset(board);
        }// end loop
    }

    @Test
    public void isWinerXDiagonalTest() {
        initBoardMock();
        for (int i = 0; i < Constants.BOARD_DIM; i++) {
            when(board.getAt(i,i)).thenReturn(PLAYER_X);
        }
        Assert.assertTrue("X is winner", ruleEngine.isWinner(PLAYER_X));
        //TO-DO verify calls
    }

    @Test
    public void isWinerODiagonalTest() {
        reset(board);
        initBoardMock();
        for (int i = 0; i < Constants.BOARD_DIM; i++) {
            when(board.getAt(i,i)).thenReturn(PLAYER_O);
        }
        Assert.assertTrue("X is winner", ruleEngine.isWinner(PLAYER_O));
        //TO-DO verify calls
    }

    @Test
    public void isWinerXRevertedDiagonalTest() {
        initBoardMock();
        for (int i = 0; i < Constants.BOARD_DIM; i++) {
            when(board.getAt(i,BOARD_DIM-1 - i)).thenReturn(PLAYER_X);
        }

        Assert.assertTrue("X is winner", ruleEngine.isWinner(PLAYER_X));
        //TO-DO verify calls
    }

    @Test
    public void isWinerORevertedDiagonalTest() {
        initBoardMock();
        for (int i = 0; i < Constants.BOARD_DIM; i++) {
            when(board.getAt(i,BOARD_DIM-1 - i)).thenReturn(PLAYER_O);
        }

        Assert.assertTrue("X is winner", ruleEngine.isWinner(PLAYER_O));
        //TO-DO verify calls
    }

    @Test
    public void isGameOverMaxTurns() {
        Assert.assertTrue(ruleEngine.isGameOver(MAX_TURNS));
    }

    @Test
    public void isGameOverWinnerXTurns() {
        isWinerXDiagonalTest(); // make x win
        Assert.assertTrue(ruleEngine.isGameOver(4));
    }

    @Test
    public void isGameOverWinnerOTurns() {
        isWinerODiagonalTest(); // make x win
        Assert.assertTrue(ruleEngine.isGameOver(4));
    }

    @Test
    public void isGameNotOverWinnerOTurns() {
        Assert.assertFalse(ruleEngine.isGameOver(4));
    }

    private void verifyColCalls(int dim) {
        for (int col = 0; col < Constants.BOARD_DIM; col++) {
                verify(board).getAt(dim,col);
        }// end loop
    }

    private void verifyRowCalls(int dim) {
        for (int row = 0; row < Constants.BOARD_DIM; row++) {
            verify(board, atLeast(1)).getAt(row,dim);
        }// end loop
    }

    private void initBoardMock() {
        for (int col = 0; col < Constants.BOARD_DIM; col++) {
            for (int row = 0; row < Constants.BOARD_DIM; row++) {
                when(board.getAt(row, col)).thenReturn(EMPTY_FIELD);
            }
        }
    }

}
