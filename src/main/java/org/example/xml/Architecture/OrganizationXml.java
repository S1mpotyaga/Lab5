package org.example.xml.Architecture;

import org.example.collectionClasses.Organization;
import org.example.collectionClasses.enums.OrganizationType;
import org.example.collectionClasses.interfaces.OrganizationReadable;
import org.example.xml.interfaces.ParseNodeable;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Scanner;

public class OrganizationXml implements ParseNodeable<Organization> {


    private void setTextNode(Node curNode){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Please, enter %s", curNode.getNodeName());
        curNode.setTextContent(scanner.next());
    }

    @Override
    public Organization parseNode(Node node) {
        if (node.getTextContent().isEmpty()){
            setTextNode(node);
        }
        Organization result = new Organization();
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            Node curNode = childNodes.item(i);
            parseField(curNode, result);
        }
        OrganizationReadable.setIncorrectFields(result);
        return result;
    }

    private void parseField(Node curNode, Organization result) {
        switch (curNode.getNodeName()) {
            case "name" -> parseName(curNode, result);
            case "fullName" -> parseFullName(curNode, result);
            case "organizationType" -> parseOrganizationType(curNode, result);
        }
    }

    private void parseId(Node curNode, Organization result) {
        try {
            result.setId(Integer.parseInt(curNode.getTextContent()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid format field 'id'.");
        }
    }

    private void parseName(Node curNode, Organization result) {
        result.setName(curNode.getTextContent());
    }

    private void parseFullName(Node curNode, Organization result) {
        result.setFullName(curNode.getTextContent());
    }

    private void parseOrganizationType(Node curNode, Organization result) {
        result.setOrganizationType(OrganizationType.fromString(curNode.getTextContent()));
    }
}
