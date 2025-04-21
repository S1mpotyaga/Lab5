package org.example.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.example.ManagerCollection;
import org.example.collectionClasses.readers.Readable;
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
    public ManagerCollection xmlRead() {
        try {
            BufferedReader bfReader = new BufferedReader(new FileReader(path));
            String body = bfReader.lines().collect(Collectors.joining());
            StringReader reader = new StringReader(body);
            JAXBContext context = JAXBContext.newInstance(ManagerCollection.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ManagerCollection result = (ManagerCollection) unmarshaller.unmarshal(reader);
            result.copyProducts(result);
            return result;
        } catch (NullPointerException e) {
            System.out.println("Empty file. Do you continue with empty collection (Yes/No)?");
            String name = Readable.scanner.nextLine();
            if (name.equals("No")) {
                System.out.print("Input path to correct not empty file:");
                this.path = Readable.scanner.nextLine();
                return xmlRead();
            }
        } catch (JAXBException e) {
            System.out.print("Wrong input data. Do you continue with empty collection (Yes/No)?");
            String name = Readable.scanner.nextLine();
            if (name.equals("No")) {
                System.out.print("Input path to correct not empty file:");
                this.path = Readable.scanner.nextLine();
                return xmlRead();
            }
            return new ManagerCollection();
        } catch (FileNotFoundException e) {
            System.out.print("File not found. Do you continue with empty collection (Yes/No)?");
            String name = Readable.scanner.nextLine();
            if (name.equals("No")) {
                System.out.print("Input correct path to file:");
                this.path = Readable.scanner.nextLine();
                return xmlRead();
            }
            return new ManagerCollection();
        }
        return new ManagerCollection();
    }
}
