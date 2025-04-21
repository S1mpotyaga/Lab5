package org.example.commands;

import org.example.Application;
import java.util.Scanner;

public class Exit extends Command{

    public Exit(Application app){
        super(app, "exit", "Close app.");
    }

    public void execute(Scanner scanner, String[] command){
        System.exit(0);
    }
}
