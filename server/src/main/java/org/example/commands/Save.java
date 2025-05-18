package org.example.commands;

import jakarta.xml.bind.JAXB;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.example.Application;
import org.example.ManagerCollection;
import org.example.queries.QueryType;
import org.example.queries.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Save{

    private static final Logger log = LoggerFactory.getLogger(Save.class);
    private Application app;
    private static Integer FileNumber = 0;
    private final Commands command = Commands.SAVE;
    private final String description = "save collection to xml file.";

    public Save(Application app){
        this.app = app;
    }

    public Object[] execute() {
        String fileName = "out" + (++FileNumber).toString() + ".xml";
        try {
            JAXBContext context = JAXBContext.newInstance(ManagerCollection.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(this.app.getManagerCollection(), new File(fileName));
            log.info("File saved successfully.");
            return new Object[]{new QueryWrapper(QueryType.ALL_DONE)};
        } catch (JAXBException e) {
            log.warn("Error saving file. {}", e.getMessage());
            return new Object[]{new QueryWrapper(QueryType.ERROR)};
        }
    }
}