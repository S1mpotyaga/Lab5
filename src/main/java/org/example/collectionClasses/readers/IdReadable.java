package org.example.collectionClasses.readers;

import java.util.Scanner;


public interface IdReadable {

    public static int idRead(Scanner scanner){
        try {
            String tmp = scanner.nextLine();
            return Integer.parseInt(tmp);
        } catch (NumberFormatException e) {
            System.out.println("Wrong format id.");
            System.out.print("Enter id: ");
            return idRead(Readable.scanner);
        }
    }
}