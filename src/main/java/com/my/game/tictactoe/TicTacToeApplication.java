package com.my.game.tictactoe;

import com.my.game.tictactoe.service.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TicTacToeApplication {
    private static Logger logger = LoggerFactory.getLogger(TicTacToeApplication.class);
    public static void main(String[] args) {
        logger.info("The main application is started.");
        ApplicationContext ctx = SpringApplication.run(TicTacToeApplication.class, args);
        Game game = (Game) ctx.getBean("game");
        game.start();
    }
}