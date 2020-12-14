package com.my.game.tictactoe.service;

import com.my.game.tictactoe.model.GameOverStatus;
import org.springframework.stereotype.Component;

public abstract class BaseGame {
    abstract GameOverStatus start();

}
