package org.example.collectionClasses.readers;

import java.util.Scanner;

/**
 * Provides a common input stream to all reading interfaces.
 */
public interface Readable {

    /**
     * General input stream.
     */
    static Scanner scanner = new Scanner(System.in);
}
