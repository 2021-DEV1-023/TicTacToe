package com.my.game.tictactoe.model;


import com.my.game.tictactoe.util.Constants;
import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    @Test
    public void inititalizationTest() {
        Board b = new Board();
        Assert.assertEquals(3, b.getColDim());
        Assert.assertEquals(3, b.getRowDim());
    }

    @Test
    public void inititalizationFieldsTest() {
        Board b = new Board();
        for (int row = 0; row < Constants.BOARD_DIM; row++) {
            for (int col = 0; col < Constants.BOARD_DIM; col++) {
                Assert.assertEquals(" ".charAt(0), b.getAt(row, col));
            }
        }
    }

    @Test
    public void putTest() {
        Board b = new Board();
        b.putAt('X', 1, 1);
        Assert.assertEquals('X', b.getAt(1,1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void putWrongTest() {
        Board b = new Board();
        b.putAt('X', 10, 1);
    }

}
