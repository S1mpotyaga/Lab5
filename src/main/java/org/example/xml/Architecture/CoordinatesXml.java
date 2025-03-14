package org.example.xml.Architecture;

import org.example.collectionClasses.Coordinates;
import org.example.collectionClasses.readers.CoordinatesReadable;
import org.example.xml.xmlReaders.NodeParseable;
import org.w3c.dom.*;

/**
 * Class that converts a Node into a Coordinates object
 */
public class CoordinatesXml implements NodeParseable<Coordinates> {

    /**
     * Method defining the body of the parseNode method of the ParseNodeable interface.
     * @param node Node of the DOM tree.
     * @return the Coordinates object
     */
    @Override
    public Coordinates parseNode(Node node){
        Coordinates result = new Coordinates();
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i){
            Node curNode = childNodes.item(i);
            parseField(curNode, result);
        }
        CoordinatesReadable.setIncorrectFields(result);
        return result;
    }

    /**
     * Parses Node fields
     * @param curNode current Node
     * @param result created Coordinates object
     */
    private void parseField(Node curNode, Coordinates result){
        switch (curNode.getNodeName()){
            case "x" -> parseX(curNode, result);
            case "y" -> parseY(curNode, result);

        }
    }

    /**
     * Parses the x field of the current curNode
     * @param curNode the current vertex
     * @param result the Coordinates object to create
     */
    private void parseX(Node curNode, Coordinates result){
        try{
            result.setX(Float.parseFloat(curNode.getTextContent()));
        }catch (NumberFormatException e){
            System.out.println("Invalid format field 'x'.");
        }
    }

    /**
     * Parses the y field of the current curNode
     * @param curNode the current vertex
     * @param result the Coordinates object to create
     */
    private void parseY(Node curNode, Coordinates result){
        try{
            result.setY(Double.parseDouble(curNode.getTextContent()));
        }catch (NumberFormatException e){
            System.out.println("Invalid format field 'y'.");
        }
    }
}
