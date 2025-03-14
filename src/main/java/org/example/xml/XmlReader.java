package org.example.xml;

import org.example.collectionClasses.Product;
import org.example.xml.Architecture.ProductXml;
import org.example.xml.xmlReaders.XmlReadable;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

/**
 * A class that defines a method for reading a collection.
 */
public class XmlReader implements XmlReadable {

    private String name;

    /**
     * Specifies the name of the input file.
     * @param nameVar variable name
     */
    public XmlReader(String nameVar){
        this.name = System.getenv(nameVar);
    }

    @Override
    public TreeMap<Product, Integer> xmlRead(){
        Document document = createDocument();
        Node root = document.getFirstChild();
        NodeList childList = root.getChildNodes();
        TreeMap<Product, Integer> result = new TreeMap<>(new Sorting());
        for (int i = 0; i < childList.getLength(); ++i){
            Node curNode = childList.item(i);
            if (curNode.getNodeName().equals("Product")) {
                Product tmp = new ProductXml().parseNode(curNode);
                result.put(tmp, tmp.getId());
            }
        }
        return result;
    }

    private Document createDocument(){
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
