package com.example.geektrust.commands;

import java.util.List;

import com.example.geektrust.services.IPlayerServices;

public class AddPlayerCommand implements ICommand {
    private IPlayerServices iPlayerServices;

    public AddPlayerCommand(IPlayerServices iPlayerServices) {
        this.iPlayerServices = iPlayerServices;
    }

    @Override
    public void execute(List<String> tokens) {
        try {
            iPlayerServices.addPlayer(tokens.get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
