package org.example.xml.xmlReaders;

import org.example.ManagerCollection;

/**
 * Gets a collection from a xml file.
 */
public interface XmlReadable {

    /**
     * Gets a collection of objects.
     * @return a TreeMap of Product objects.
     */
    public ManagerCollection xmlRead();
}
