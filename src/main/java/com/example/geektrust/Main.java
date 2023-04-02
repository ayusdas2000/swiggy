package com.example.geektrust;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.example.geektrust.commands.CommandInvoker;
import com.example.geektrust.config.ApplicationConfig;
import com.example.geektrust.exceptions.CommandNotFoundException;

public class Main {
    public static void run(String commandLineArgs) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
        commandInvoker.executeCommand("LOAD_DATA", null);
        String inputFile = commandLineArgs;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                commandInvoker.executeCommand(tokens.get(0), tokens);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException | CommandNotFoundException e) {
            e.printStackTrace();
        }
        commandInvoker.executeCommand("START_GAME", null);
    }

    public static void main(String[] args) {
        run(args[0]);
    }
}
