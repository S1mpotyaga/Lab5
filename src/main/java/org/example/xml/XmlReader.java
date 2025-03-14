package org.example.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.example.ProductCollection;
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
     * @param pathVar variable path
     */
    public XmlReader(String pathVar){
        this.path = System.getenv(pathVar);
    }

    @Override
    public ProductCollection xmlRead(){
        try {
            BufferedReader bfReader = new BufferedReader(new FileReader(path));
            String body = bfReader.lines().collect(Collectors.joining());
            StringReader reader = new StringReader(body);
            JAXBContext context = JAXBContext.newInstance(ProductCollection.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ProductCollection result = (ProductCollection) unmarshaller.unmarshal(reader);
            result.copyProducts(result);
            return result;
        } catch (FileNotFoundException | JAXBException e){
            System.out.print("Wrong path to input xml file or wrong input data. Check input file and enter path to file:");
            this.path = Readable.scanner.nextLine();
            return xmlRead();
        }
    }
}
