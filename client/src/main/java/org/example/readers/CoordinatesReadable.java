package org.example.readers;

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
    static Coordinates readCoordinates() {
        Coordinates result = new Coordinates();
        System.out.print("Enter coordinate x > -152: ");
        try {
            result.setX(readX());
        } catch (NoSuchElementException e){
            System.out.print("Not found coordinate x. Enter please again: ");
            result.setX(readX());
        }
        System.out.print("Enter coordinate y: ");
        try {
            result.setY(readY());
        } catch (NoSuchElementException e) {
            System.out.print("Not found coordinate y. Enter please again: ");
            result.setY(readY());
        }
        return result;
    }

    /**
     * Reads the value of x.
     *
     * @return the value of x as a Float
     */
    private static Float readX() throws NoSuchElementException{
        String xTmp = Readable.scanner.nextLine();
        try {
            Float x = Float.parseFloat(xTmp);
            if (x <= -152){
                System.out.println("Coordinate x < -152.");
                System.out.print("Enter coordinate x again: ");
                return readX();
            }
            return x;
        } catch (NumberFormatException e) {
            System.out.println("Wrong format.");
            return readX();
        }
    }

    private static Double readY() throws NoSuchElementException{
        String yTmp = Readable.scanner.nextLine();
        try {
            Double y = Double.parseDouble(yTmp);
            if (y <= -152) {
                System.out.print("Incorrect coordinate y. Enter coordinate y again: ");
                return readY();
            }
            return y;
        } catch (NumberFormatException e) {
            System.out.print("It isn't number. Enter coordinate y again: ");
            return readY();
        }
    }

    static Coordinates readCoordinates(Scanner scanner) throws NoSuchElementException{
        Coordinates result = new Coordinates();
        try {
            result.setX(readX(scanner));
        } catch (NumberFormatException e) {
            System.err.print("Wrong coordinate x. Enter coordinate x > -152 again: ");
            result.setX(readX());
        }
        try {
            result.setY(readY(scanner));
        } catch (NumberFormatException e){
            System.err.print("Wrong coordinate y. Enter coordinate y again: ");
            result.setY(readY());
        }
        return result;
    }

    private static Float readX(Scanner scanner) throws NoSuchElementException, NumberFormatException{
        Float x = Float.parseFloat(scanner.nextLine());
        if (x <= -152){
            throw new NumberFormatException();
        }
        return x;
    }

    private static Double readY(Scanner scanner) throws NoSuchElementException, NumberFormatException{
        Double y = Double.parseDouble(scanner.nextLine());
        if (y <= -152) {
            throw new NumberFormatException();
        }
        return y;
    }
}