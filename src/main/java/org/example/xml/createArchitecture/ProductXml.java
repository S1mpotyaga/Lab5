package org.example.xml.createArchitecture;

import org.example.collectionClasses.enums.UnitOfMeasure;
import org.example.xml.interfaces.ParseNodeable;
import org.example.collectionClasses.Product;
import org.w3c.dom.*;

public class ProductXml implements ParseNodeable<Product>{

    @Override
    public Product parseNode(Node node){
        Product result = new Product();
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i){
            Node curNode = childNodes.item(i);
            parseField(curNode, result);
        }
        return result;
    }

    private void parseField(Node curNode, Product result){
        switch (curNode.getNodeName()){
            case "id" -> parseId(curNode, result);
            case "name" -> parseName(curNode, result);
            case "price" -> parsePrice(curNode, result);
            case "unitOfMeasure" -> parseUnitOfMeasure(curNode, result);
            case "coordinates" -> parseCoordinates(curNode, result);
            case "organization" -> parseOrganization(curNode, result);
        }
    }

    private void parseId(Node curNode, Product result){
        try{
            result.setId(Integer.parseInt(curNode.getTextContent()));
        }catch(NumberFormatException e){
            System.out.println("Invalid format field 'id'.");
        }
    }

    private void parseName(Node curNode, Product result){
        result.setName(curNode.getTextContent());
    }

    private void parsePrice(Node curNode, Product result){
        try{
            result.setPrice(Long.parseLong(curNode.getTextContent()));
        } catch(NumberFormatException e){
            System.out.println("Invalid format field 'price'.");
        }
    }

    private void parseUnitOfMeasure(Node curNode, Product result){
        result.setUnitOfMeasure(UnitOfMeasure.fromString(curNode.getTextContent()));
    }

    private void parseCoordinates(Node curNode, Product result){
        result.setCoordinates(new CoordinatesXml().parseNode(curNode));
    }

    private void parseOrganization(Node curNode, Product result){
        result.setOrganization(new OrganizationXml().parseNode(curNode));
    }
}
