package org.example.xml.createArchitecture;

import org.example.collectionClasses.enums.UnitOfMeasure;
import org.example.xml.interfaces.*;
import org.example.collectionClasses.Product;
import org.w3c.dom.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Scanner;

public class ProductXml implements ParseNodeable<Product>{

    private static int productNumber = 0;

    @Override
    public Product parseNode(Node node) {
        Product result = new Product();
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            Node curNode = childNodes.item(i);
            parseField(curNode, result);
        }
        return result;
    }

    private void setTextNode(Node curNode){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Please, enter %s for Product %d\n", curNode.getNodeName(), productNumber);
        curNode.setTextContent(scanner.next());
    }

    private void parseField(Node curNode, Product result) {
        if (curNode.getTextContent().isEmpty()){
            setTextNode(curNode);
        }
        switch (curNode.getNodeName()) {
            case "name" -> parseName(curNode, result);
            case "price" -> parsePrice(curNode, result);
            case "unitOfMeasure" -> parseUnitOfMeasure(curNode, result);
            case "coordinates" -> parseCoordinates(curNode, result);
            case "organization" -> parseOrganization(curNode, result);
        }
    }

    private void parseName(Node curNode, Product result) {
        result.setName(curNode.getTextContent());
    }

    private void parsePrice(Node curNode, Product result) {
        try {
            result.setPrice(Long.parseLong(curNode.getTextContent()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid format field 'price'.");
        }
    }

    private void parseUnitOfMeasure(Node curNode, Product result) {
        result.setUnitOfMeasure(UnitOfMeasure.fromString(curNode.getTextContent()));
    }

    private void parseCoordinates(Node curNode, Product result) {
        result.setCoordinates(new CoordinatesXml().parseNode(curNode));
    }

    private void parseOrganization(Node curNode, Product result) {
        result.setOrganization(new OrganizationXml().parseNode(curNode));
    }
}
