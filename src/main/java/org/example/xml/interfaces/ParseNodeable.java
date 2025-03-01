package org.example.xml.interfaces;

import org.w3c.dom.Node;

public interface ParseNodeable<T> {

    public T parseNode(Node node);
}