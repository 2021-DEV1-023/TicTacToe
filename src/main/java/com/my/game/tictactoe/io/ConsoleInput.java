package com.my.game.tictactoe.io;

import com.my.game.tictactoe.model.Location;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput implements UserInput {
    Scanner keyboard = new Scanner(System.in);
    @Override
    public Location getLocationInput(char player) {
        System.out.print("'" + player + "', choose your placement row: ");
        int inputRow = keyboard.nextInt();
        System.out.print("'" + player + "', choose your placement column: ");
        int inputColumn = keyboard.nextInt();

        return new Location(inputRow, inputColumn);
    }
}
