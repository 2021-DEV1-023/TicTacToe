package com.my.game.tictactoe.service;

import com.my.game.tictactoe.io.UserInput;
import com.my.game.tictactoe.model.GameOverStatus;
import com.my.game.tictactoe.model.Location;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static com.my.game.tictactoe.util.Constants.GAME_OVER_CODE;
import static org.mockito.Mockito.doReturn;

/**
 * @author Vladimir
 *
 * Unit/Spring integration test for testing the game.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GameTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private UserInput userInput;

    @InjectMocks
    @Autowired
    private Game game;

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testXWinScenario() {
        /*
        xxx
        o o

         */
        doReturn( new Location(0,0),
                new Location(0,1),
                new Location(0,2)).when(userInput ).getLocationInput('X');

        doReturn( new Location(1,0),
                new Location(1,2)).when(userInput ).getLocationInput('O');

        GameOverStatus gameOverStatus = game.start();
        Assert.assertEquals("X is a winner",'X', gameOverStatus.getWinner());
        Assert.assertEquals("O game over",GAME_OVER_CODE, gameOverStatus.getExitCode());
    }

    @Test
    public void testOWinScenario() {
        /*
        xx
        ooo
          x
         */
        doReturn( new Location(0,0),
                new Location(0,1),
                new Location(2,2)).when(userInput ).getLocationInput('X');

        doReturn( new Location(1,0),
                new Location(1,1),
                new Location(1,2)).when(userInput ).getLocationInput('O');

        GameOverStatus gameOverStatus = game.start();
        Assert.assertEquals("O is a winner",'O', gameOverStatus.getWinner());
        Assert.assertEquals("0 game over",GAME_OVER_CODE, gameOverStatus.getExitCode());
    }

    @Test
    public void testDrawScenario() {
        /*
        xxo
        oox
        xox
         */
        doReturn( new Location(0,0),
                new Location(2,0),
                new Location(2,2),
                new Location(0,1),
                new Location(1,2)).when(userInput ).getLocationInput('X');

        doReturn( new Location(1,0),
                new Location(1,1),
                new Location(0,2),
                new Location(2, 1)).when(userInput ).getLocationInput('O');

        GameOverStatus gameOverStatus = game.start();
        Assert.assertEquals("draw",'t', gameOverStatus.getWinner());
        Assert.assertEquals("0 game over",GAME_OVER_CODE, gameOverStatus.getExitCode());
    }

}
