package org.example.commands;


import org.example.Application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScript {

    public static void execute(Application app, String fileName){
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))){
            app.startFile(scanner);
        } catch (FileNotFoundException e){
            System.err.println("File not found.");
        }
    }
}