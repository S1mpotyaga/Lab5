package org.example;

import lombok.Getter;
import org.example.commands.Command;
import org.example.commands.*;

import java.util.ArrayList;
import java.util.Scanner;

@Getter
public class ManagerCommands {

    private ArrayList<Command> commands = new ArrayList<Command>();

    public ManagerCommands(Application app){
        addCommand(new Help(app));
        addCommand(new Info(app));
        addCommand(new Show(app));
        addCommand(new Insert(app));
        addCommand(new Update(app));
        addCommand(new RemoveKey(app));
        addCommand(new Clear(app));
        addCommand(new Save(app));
        addCommand(new ExecuteScript(app));
        addCommand(new Exit(app));
        addCommand(new ReplaceIfGreater(app));
        addCommand(new ReplaceIfLower(app));
        addCommand(new RemoveGreaterKey(app));
        addCommand(new MinByManufacturer(app));
        addCommand(new MaxByCoordinates(app));
        addCommand(new PrintFieldAscendingPrice(app));
    }

    public void addCommand(Command newCommand){
        this.commands.add(newCommand);
    }

    public void callCommand(Scanner scanner, String[] command){
        boolean isMet = false;
        for (Command current: commands){
            if (current.getName().equals(command[0])){
                current.execute(scanner, command);
                isMet = true;
            }
        }
        if (!isMet){
            System.out.println("Wrong command!");
        }
    }
}