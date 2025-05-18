package org.example.readers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Provides a common input stream to all reading interfaces.
 */
public interface Readable {

    /**
     * General input stream.
     */
    Scanner scanner = new Scanner(System.in);

    static String readFileName(){
        System.out.print("Enter file name: ");
        return scanner.nextLine();
    }

    static int readId(){
        try {
            System.out.print("Enter id: ");
            String tmp = scanner.nextLine();
            return Integer.parseInt(tmp);
        } catch (NumberFormatException e) {
            System.out.println("Wrong format id.");
            return readId();
        }
    }

    static String readAnswer(){
        return scanner.nextLine();
    }
}
