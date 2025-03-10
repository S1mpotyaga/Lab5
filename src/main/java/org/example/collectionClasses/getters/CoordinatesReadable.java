package org.example.collectionClasses.getters;

import org.example.collectionClasses.Coordinates;

import java.util.Scanner;

public interface CoordinatesReadable {

    static Coordinates readCoordinates(){
        Scanner scanner = new Scanner(System.in);
        Coordinates result = new Coordinates();
        System.out.print("Enter coordinate x: ");
        result.setX(scanner.nextFloat());
        System.out.print("Enter coordinate y: ");
        result.setY(scanner.nextDouble());
        scanner.close();
        return result;
    }
}
