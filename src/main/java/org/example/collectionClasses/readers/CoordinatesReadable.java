package org.example.collectionClasses.readers;

import org.example.collectionClasses.Coordinates;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads the Coordinates object from the console.
 */
public interface CoordinatesReadable{

    /**
     * Method that reads the correct coordinates.
     *
     * @return the Coordinates object.
     */
    static Coordinates readCoordinates(Scanner scanner) {
        Coordinates result = new Coordinates();
        System.out.print("Enter coordinate x > -152: ");
        try {
            result.setX(readX(scanner));
        } catch (NoSuchElementException e){
            System.out.print("Not found coordinate x. Enter please again: ");
            result.setX(readX(Readable.scanner));
        }
        System.out.print("Enter coordinate y: ");
        try {
            result.setY(readY(scanner));
        } catch (NoSuchElementException e) {
            System.out.print("Not found coordinate y. Enter please again: ");
            result.setY(readY(Readable.scanner));
        }
        return result;
    }

    /**
     * Reads the value of x.
     *
     * @return the value of x as a Float
     */
    private static Float readX(Scanner scanner) throws NoSuchElementException{
        String xTmp = scanner.nextLine();
        try {
            Float x = Float.parseFloat(xTmp);
            if (x <= -152){
                System.out.println("Coordinate x < -152.");
                System.out.print("Enter coordinate x again: ");
                return readX(Readable.scanner);
            }
            return x;
        } catch (NumberFormatException e) {
            System.out.println("Wrong format.");
            return readX(Readable.scanner);
        }
    }

    private static Double readY(Scanner scanner) throws NoSuchElementException{
        String yTmp = scanner.nextLine();
        try {
            Double y = Double.parseDouble(yTmp);
            if (y <= -152) {
                System.out.print("Incorrect coordinate y. Enter coordinate y again: ");
                return readY(Readable.scanner);
            }
            return y;
        } catch (NumberFormatException e) {
            System.out.print("It isn't number. Enter coordinate y again: ");
            return readY(Readable.scanner);
        }
    }
}