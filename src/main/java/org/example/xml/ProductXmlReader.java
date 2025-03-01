package org.example.xml;

import org.example.collectionClasses.Product;
import org.example.xml.createArchitecture.ProductXml;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.TreeMap;

import static java.lang.Math.random;

public class ProductXmlReader extends XmlFile<Product>{

    public ProductXmlReader(String nameVar){
        super(System.getenv(nameVar));
    }

    @Override
    public TreeMap<Product, Integer> xmlRead(){
        Document document = createDocument();
        Node root = document.getFirstChild();
        NodeList childList = root.getChildNodes();
        TreeMap<Product,Integer> result = new TreeMap<>(new Sorting());
        for (int i = 0; i < childList.getLength(); ++i){
            Node curNode = childList.item(i);
            if (curNode.getNodeName().equals("Product")) {
                result.put(new ProductXml().parseNode(curNode), (int) (random() * 1000));
            }
        }
        return result;
    }
}
