package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Launch the application.
 */
class Main{

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args){
        Application application = new Application();
    }
}