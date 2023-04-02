package com.example.geektrust.commands;

import java.util.List;

import com.example.geektrust.entities.Game;
import com.example.geektrust.entities.GameStatus;
import com.example.geektrust.services.IGameServices;

public class StartGameAndPrintResultCommand implements ICommand {

    private IGameServices iGameServices;

    public StartGameAndPrintResultCommand(IGameServices iGameServices) {
        this.iGameServices = iGameServices;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            iGameServices.setupGame();
            iGameServices.distributeCards();
            Game game = iGameServices.playGame();
            if (game.getGameStatus().equals(GameStatus.FINISHED_DRAW)) {
                System.out.println("Game ended in draw");
            } else {
                System.out.println(game.getWinner().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
