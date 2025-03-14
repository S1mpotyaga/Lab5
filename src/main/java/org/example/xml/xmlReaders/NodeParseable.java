package org.example.xml.xmlReaders;

import org.w3c.dom.Node;

/**
 * Parses Node
 * @param <T> the type of object the parser should return
 */
public interface NodeParseable<T> {

    /**
     * Method that parses node
     * @param node Node of the DOM tree.
     * @return object T that represents node
     */
    public T parseNode(Node node);
}