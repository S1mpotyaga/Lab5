package org.example.xml.Architecture;

import org.example.collectionClasses.Organization;
import org.example.collectionClasses.types.OrganizationType;
import org.example.collectionClasses.readers.OrganizationReadable;
import org.example.xml.xmlReaders.NodeParseable;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A class that converts a Node into an Organization object.
 */
public class OrganizationXml implements NodeParseable<Organization> {

    /**
     * Defines the parseNode method of the NodeParseable interface.
     * @param node Node of the DOM tree.
     * @return the Organization object
     */
    @Override
    public Organization parseNode(Node node) {
        Organization result = new Organization();
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            Node curNode = childNodes.item(i);
            parseField(curNode, result);
        }
        OrganizationReadable.setIncorrectFields(result);
        return result;
    }

    /**
     * Parses the fields of the current Node.
     * @param curNode the current Node
     * @param result the Organization object to create
     */
    private void parseField(Node curNode, Organization result) {
        switch (curNode.getNodeName()) {
            case "name" -> parseName(curNode, result);
            case "fullName" -> parseFullName(curNode, result);
            case "organizationType" -> parseOrganizationType(curNode, result);
        }
    }

    /**
     * Parses the id field.
     * @param curNode current Node
     * @param result the Organization object to create.
     */
    private void parseId(Node curNode, Organization result) {
        try {
            result.setId(Integer.parseInt(curNode.getTextContent()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid format field 'id'.");
        }
    }

    /**
     * Parses the Name field of the current Node.
     * @param curNode the current Node.
     * @param result the Organization object to create.
     */
    private void parseName(Node curNode, Organization result) {
        result.setName(curNode.getTextContent());
    }

    /**
     * Parses the FullName field of the current Node
     * @param curNode the current Node.
     * @param result the Organization object to create.
     */
    private void parseFullName(Node curNode, Organization result) {
        result.setFullName(curNode.getTextContent());
    }

    /**
     * Parses the OrganizationType field of the current Node.
     * @param curNode the current Node.
     * @param result the Organization object to create.
     */
    private void parseOrganizationType(Node curNode, Organization result) {
        result.setOrganizationType(OrganizationType.fromString(curNode.getTextContent()));
    }
}
