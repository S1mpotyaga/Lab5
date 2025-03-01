package org.example.xml.createArchitecture;

import org.example.collectionClasses.Coordinates;
import org.example.xml.interfaces.ParseNodeable;
import org.w3c.dom.*;

public class CoordinatesXml implements ParseNodeable<Coordinates> {

    @Override
    public Coordinates parseNode(Node node){
        Coordinates result = new Coordinates();
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i){
            Node curNode = childNodes.item(i);
            parseField(curNode, result);
        }
        return result;
    }

    private void parseField(Node curNode, Coordinates result){
        switch (curNode.getNodeName()){
            case "x" -> parseX(curNode, result);
            case "y" -> parseY(curNode, result);

        }
    }

    private void parseX(Node curNode, Coordinates result){
        try{
            result.setX(Float.parseFloat(curNode.getTextContent()));
        }catch (NumberFormatException e){
            System.out.println("Invalid format field 'x'.");
        }
    }

    private void parseY(Node curNode, Coordinates result){
        try{
            result.setY(Double.parseDouble(curNode.getTextContent()));
        }catch (NumberFormatException e){
            System.out.println("Invalid format field 'y'.");
        }
    }
}
