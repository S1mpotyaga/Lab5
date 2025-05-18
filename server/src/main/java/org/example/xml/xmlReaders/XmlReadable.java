package org.example.xml.xmlReaders;

import jakarta.xml.bind.JAXBException;
import org.example.ManagerCollection;

import java.io.FileNotFoundException;

/**
 * Gets a collection from a xml file.
 */
public interface XmlReadable {

    /**
     * Gets a collection of objects.
     * @return a TreeMap of Product objects.
     */
    public ManagerCollection xmlRead() throws FileNotFoundException, JAXBException;
}
