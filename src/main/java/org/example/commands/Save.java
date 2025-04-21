package org.example.commands;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.example.Application;
import org.example.ManagerCollection;
import org.example.collectionClasses.readers.Readable;

import java.io.File;
import java.util.Scanner;

public class Save extends Command{

    public Save(Application app){
        super(app, "save", "Save collection to xml file.");
    }

    public void execute(Scanner scanner, String[] command){
        try {
            JAXBContext context = JAXBContext.newInstance(ManagerCollection.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            System.out.print("Enter name file output: ");
            String nameFileOutput = getFileOutputName(scanner);
            File fileOutput = new File(nameFileOutput);
            super.getApp().getManagerCollection().createOut(ManagerCollection.getProducts().keySet());
            marshaller.marshal(super.getApp().getManagerCollection(), fileOutput);
        } catch (JAXBException e) {
            System.out.println("Failed to write to xml file. Try again.");
            this.execute(Readable.scanner, command);
        }
    }

    private String getFileOutputName(Scanner scanner){
        String name = scanner.nextLine();
        if (name.length() < 4) {
            name += ".xml";
        }
        if (!name.endsWith(".xml")) {
            name += ".xml";
        }
        return name;
    }
}
