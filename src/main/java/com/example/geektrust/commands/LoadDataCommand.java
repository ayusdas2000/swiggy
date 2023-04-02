package com.example.geektrust.commands;

import java.util.List;

import com.example.geektrust.repositories.ICardRepository;
import com.example.geektrust.repositories.data.LoadData;

public class LoadDataCommand implements ICommand {
    private final ICardRepository iCardRepository;

    public LoadDataCommand(ICardRepository iCardRepository) {
        this.iCardRepository = iCardRepository;
    }

    @Override
    public void execute(List<String> tokens) {
        LoadData loadData = new LoadData(iCardRepository);
        loadData.loadData();
    }

}
