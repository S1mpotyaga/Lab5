package org.example.xml;

import lombok.Data;
import org.example.xml.interfaces.DocumentCreateable;
import org.example.xml.interfaces.XmlReadable;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Data
public abstract class XmlFile<T> implements DocumentCreateable, XmlReadable<T> {

    private String name;

    public XmlFile(String name){
        this.name = name;
    }

    @Override
    public Document createDocument(){
        Document document = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(new File(this.name));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }
        return document;
    }
}