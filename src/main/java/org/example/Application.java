package org.example;

import lombok.Getter;
import java.time.LocalDate;
import java.util.*;

import org.example.collectionClasses.readers.Readable;
import org.example.xml.XmlReader;


/**
 * A console application class that implements interactive management
 * of a collection of Product objects.
 */

@Getter
public class Application {

    /**
     * Collection of Product objects.
     */
    private final ManagerCollection managerCollection;

    /**
     * Date of collection creation.
     */
    private final java.time.LocalDate creationDate;

    private ManagerCommands managerCommands;

    /**
     * An application constructor that creates collections from input data.
     * Input data is in a xml file.
     * To fill a collection, you need to specify the path to the input file in the 'Lab5' environment variable.
     * After creating a collection, the application automatically starts.
     */
    public Application() {
        XmlReader xmlReader = new XmlReader("Lab5");
        this.managerCollection = xmlReader.xmlRead();
        this.creationDate = LocalDate.now();
        this.managerCommands = new ManagerCommands(this);
        start(Readable.scanner);
    }

    /**
     * Launching the application
     */
    public void start(Scanner scanner) {
        while (true) {
            String[] command = scanner.nextLine().split(" ");
            managerCommands.callCommand(scanner, command);
        }
    }
}