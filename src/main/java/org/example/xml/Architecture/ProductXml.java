package org.example.xml.Architecture;

import org.example.collectionClasses.types.UnitOfMeasure;
import org.example.collectionClasses.readers.ProductReadable;
import org.example.xml.xmlReaders.*;
import org.example.collectionClasses.Product;
import org.w3c.dom.*;

/**
 * A class that converts a Node into a Product object.
 */
public class ProductXml implements NodeParseable<Product> {

    /**
     * The number of Product objects created.
     */
    private static int productNumber = 0;

    /**
     * Defines the parseNode method of the NodeParseable interface
     * @param node Node of the DOM tree.
     * @return the Product object
     */
    @Override
    public Product parseNode(Node node) {
        Product result = new Product();
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            Node curNode = childNodes.item(i);
            callParseField(curNode, result);
        }
        ProductReadable.setIncoorectFields(result);
        return result;
    }

    /**
     * Calls the parser of the current Node field.
     * @param curNode the current Node.
     * @param result the Product object to create.
     */
    private void callParseField(Node curNode, Product result) {
        switch (curNode.getNodeName()) {
            case "name" -> parseName(curNode, result);
            case "price" -> parsePrice(curNode, result);
            case "unitOfMeasure" -> parseUnitOfMeasure(curNode, result);
            case "coordinates" -> parseCoordinates(curNode, result);
            case "organization" -> parseOrganization(curNode, result);
        }
    }

    /**
     * Parses the Name field of the current Node.
     * @param curNode the current Node.
     * @param result the Product object to create.
     */
    private void parseName(Node curNode, Product result) {
        result.setName(curNode.getTextContent());
    }

    /**
     * Parses the Price field of the current Node.
     * @param curNode the current Node.
     * @param result the Product object to create.
     */
    private void parsePrice(Node curNode, Product result) {
        try {
            result.setPrice(Long.parseLong(curNode.getTextContent()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid format field 'price'.");
        }
    }

    /**
     * Parses the UnitOfMeasure field of the current Node.
     * @param curNode the current Node.
     * @param result the Product object to create.
     */
    private void parseUnitOfMeasure(Node curNode, Product result) {
        result.setUnitOfMeasure(UnitOfMeasure.fromString(curNode.getTextContent()));
    }

    /**
     * Parses the Coordinates field of the current Node.
     * @param curNode the current Node.
     * @param result the Product object to create.
     */
    private void parseCoordinates(Node curNode, Product result) {
        result.setCoordinates(new CoordinatesXml().parseNode(curNode));
    }

    /**
     * Parses the Organization field of the current Node.
     * @param curNode the current Node.
     * @param result the Product object to create.
     */
    private void parseOrganization(Node curNode, Product result) {
        result.setOrganization(new OrganizationXml().parseNode(curNode));
    }
}
