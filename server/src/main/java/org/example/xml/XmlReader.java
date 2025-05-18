package org.example.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.example.ManagerCollection;
import org.example.xml.xmlReaders.XmlReadable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.stream.Collectors;

/**
 * A class that defines a method for reading a collection.
 */
public class XmlReader implements XmlReadable {

    private String path;

    /**
     * Specifies the path of the input file.
     *
     * @param pathVar variable path
     */
    public XmlReader(String pathVar) {
        this.path = System.getenv(pathVar);
    }

    @Override
    public ManagerCollection xmlRead() throws FileNotFoundException, JAXBException {
        BufferedReader bfReader = new BufferedReader(new FileReader(path));
        String body = bfReader.lines().collect(Collectors.joining());
        StringReader reader = new StringReader(body);
        JAXBContext context = JAXBContext.newInstance(ManagerCollection.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ManagerCollection res =  (ManagerCollection) unmarshaller.unmarshal(reader);
        res.convertList();
        return res;
    }
}
