package com.example.geektrust.config;

import com.example.geektrust.commands.AddPlayerCommand;
import com.example.geektrust.commands.CommandInvoker;
import com.example.geektrust.commands.ICommand;
import com.example.geektrust.commands.LoadDataCommand;
import com.example.geektrust.commands.StartGameAndPrintResultCommand;
import com.example.geektrust.repositories.CardRepository;
import com.example.geektrust.repositories.GameRepository;
import com.example.geektrust.repositories.ICardRepository;
import com.example.geektrust.repositories.IGameRepository;
import com.example.geektrust.repositories.IPlayerRepository;
import com.example.geektrust.repositories.PlayerRepository;
import com.example.geektrust.services.GameServices;
import com.example.geektrust.services.IGameServices;
import com.example.geektrust.services.IPlayerServices;
import com.example.geektrust.services.PlayerServices;

public class ApplicationConfig {
    private ICardRepository iCardRepository = new CardRepository();
    private IPlayerRepository iPlayerRepository = new PlayerRepository();
    private IGameRepository iGameRepository = new GameRepository();

    private IPlayerServices iPlayerServices = new PlayerServices(iPlayerRepository);
    private IGameServices iGameServices = new GameServices(iGameRepository, iPlayerRepository, iCardRepository);

    private ICommand loadDataCommand = new LoadDataCommand(iCardRepository);
    private ICommand addPlayerCommand = new AddPlayerCommand(iPlayerServices);
    private ICommand startGameAndPrintResultCommand = new StartGameAndPrintResultCommand(iGameServices);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("LOAD_DATA", loadDataCommand);
        commandInvoker.register("ADD_PLAYER", addPlayerCommand);
        commandInvoker.register("START_GAME", startGameAndPrintResultCommand);
        return commandInvoker;
    }

}
