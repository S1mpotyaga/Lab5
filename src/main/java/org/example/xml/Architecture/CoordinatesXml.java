package org.example.xml.Architecture;

import org.example.collectionClasses.Coordinates;
import org.example.collectionClasses.interfaces.CoordinatesReadable;
import org.example.xml.interfaces.ParseNodeable;
import org.w3c.dom.*;

import java.util.Scanner;

public class CoordinatesXml implements ParseNodeable<Coordinates> {

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

    private void setTextNode(Node curNode){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Please, enter %s\n", curNode.getNodeName());
        curNode.setTextContent(scanner.next());
    }

    private void parseField(Node curNode, Coordinates result){
        if (curNode.getTextContent().isEmpty()){
            setTextNode(curNode);
        }
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
