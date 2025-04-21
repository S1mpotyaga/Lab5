package org.example.commands;

import org.example.Application;
import org.example.collectionClasses.readers.Readable;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ExecuteScript extends Command{

    public ExecuteScript(Application app){
        super(app, "execute_script", "Execute commands in file, which name is file_name. File contains only command name.");
    }

    public void execute(Scanner scanner, String[] command){
        String name = "";
        if (command.length >= 2){
            name = command[1];
        }else{
            System.out.print("Enter path input file: ");
            name = readExecuteFileName(scanner);
        }
        try (Scanner reader = new Scanner(new BufferedInputStream(new FileInputStream(new File(name))))){
            super.getApp().start(reader);
        } catch (IOException e){
            System.out.print("File not found. Do you want to continue execute_command (Yes/No)?: ");
            String tmp = Readable.scanner.nextLine();
            if (tmp.equalsIgnoreCase("yes")) {
                this.execute(scanner, new String[0]);
            }
        } catch (NoSuchElementException e){
            System.out.println("File finished.");
            super.getApp().start(Readable.scanner);
        }
    }

    private String readExecuteFileName(Scanner scanner){
        return scanner.nextLine();
    }
}