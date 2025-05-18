package org.example;

import jakarta.xml.bind.JAXBException;
import lombok.Getter;
import org.example.commands.Save;
import org.example.connection.ManagerConnection;
import org.example.xml.XmlReader;

import java.io.*;
import java.time.LocalDate;

import org.slf4j.*;

@Getter
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private final ManagerCommands managerCommands;
    private final ManagerQueries managerQueries;
    private ManagerCollection managerCollection;
    private ManagerConnection managerConnection = null;
    private final LocalDate creationDate;
    private History history = new History();

    public Application() {
        log.info("The server is running.");
        XmlReader xmlReader = new XmlReader("Lab5");
        try {
            this.managerCollection = xmlReader.xmlRead();
            this.managerCollection.setProductNumber();
        } catch (NullPointerException | JAXBException | FileNotFoundException e) {
            log.warn("Error reading file with collection. {}", e.getMessage());
            this.managerCollection = new ManagerCollection();
        }
        log.info("Collection created.");
        this.creationDate = LocalDate.now();
        this.managerCommands = new ManagerCommands(this);
        this.managerQueries = new ManagerQueries();
        this.managerConnection = new ManagerConnection();
        start();
    }

    public void start() {
        new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    if (line.equalsIgnoreCase("exit")) {
                        System.exit(0);
                    } else if (line.equalsIgnoreCase("save")) {
                        new Save(this).execute();
                    }
                }
                System.exit(0);
            } catch (IOException e) {
                log.warn(e.getMessage());
            }
        }).start();
        while (true) {
            ObjectInputStream objStream = this.managerConnection.receiveObjectInputStream();

            if (objStream == null) {
                log.warn("Error message.");
                continue;
            }
            Object[] result = this.managerCommands.callCommand(objStream);
            log.info("Command executed successfully.");
            this.managerConnection.send(result);
        }
    }
}