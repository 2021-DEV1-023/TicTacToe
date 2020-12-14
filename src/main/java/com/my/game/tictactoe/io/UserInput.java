package com.my.game.tictactoe.io;

import com.my.game.tictactoe.model.Location;

public interface UserInput {
    Location getLocationInput(char player);
}
