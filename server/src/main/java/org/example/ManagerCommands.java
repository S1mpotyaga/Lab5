package org.example;

import lombok.Getter;
import org.example.commands.*;
import org.example.queries.QueryType;
import org.example.queries.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

@Getter
public class ManagerCommands {

    private static final Logger log = LoggerFactory.getLogger(ManagerCommands.class);
    private ArrayList<Command> commands = new ArrayList<>();

    public ManagerCommands(Application app) {
        commands.add(new Help(app));
        commands.add(new Info(app));
        commands.add(new Show(app));
        commands.add(new Insert(app));
        commands.add(new Update(app));
        commands.add(new RemoveKey(app));
        commands.add(new Clear(app));
        commands.add(new Exit(app));
        commands.add(new ReplaceIfGreater(app));
        commands.add(new ReplaceIfLower(app));
        commands.add(new RemoveGreaterKey(app));
        commands.add(new MinByManufacturer(app));
        commands.add(new MaxByCoordinates(app));
        commands.add(new PrintFieldAscendingPrice(app));
        commands.add(new RepeatLastCommand(app));
        commands.add(new ExecuteScript(app));
    }

    public Object[] callCommand(ObjectInputStream objStream) {
        try {
            CommandWrapper commandWrapper = (CommandWrapper) objStream.readObject();
            log.info("Execute '{}' command", commandWrapper.toString());
            Object[] result = new Object[0];
            for (Command tmp : this.commands) {
                if (tmp.getCommand().equals(commandWrapper.getCommand())) {
                    tmp.getApp().getHistory().addCommand(tmp);
                    result = tmp.execute(objStream);
                    break;
                }
            }
            return result;
        } catch (ClassNotFoundException | IOException e) {
            log.warn("Error reading command. {}", e.getMessage());
            return new Object[]{new QueryWrapper(QueryType.ERROR)};
        }
    }

}