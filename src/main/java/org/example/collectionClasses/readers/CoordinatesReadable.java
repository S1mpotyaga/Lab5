package org.example.collectionClasses.readers;

import org.example.collectionClasses.Coordinates;

/**
 * Reads the Coordinates object from the console.
 */
public interface CoordinatesReadable{

    /**
     * Method that reads the correct coordinates.
     * @return the Coordinates object.
     */
    static Coordinates readCoordinates(){
        Coordinates result = new Coordinates();
        result.setX(readX());
        result.setY(readY());
        setIncorrectFields(result);
        return result;
    }

    /**
     * Sets the incorrectly entered Coordinates fields.
     * @param coordinates object with incorrect fields.
     */
    public static void setIncorrectFields(Coordinates coordinates){
        if (coordinates.getX() < -152){
            coordinates.setX(readX());
        }
        if (coordinates.getY() == null){
            coordinates.setY(readY());
        }
    }

    /**
     * Reads the value of x.
     * @return the value of x as a Float
     */
    private static Float readX(){
        System.out.print("Enter coordinate x: ");
        Float x = Readable.scanner.nextFloat();
        while (x <= -152){
            System.out.print("Incorrect coordinate x. Enter coordinate again: ");
            x = Readable.scanner.nextFloat();
        }
        Readable.scanner.nextLine(); // считывает оставшийся в буфере перевод строки
        return x;
    }

    /**
     * Reads the value of y.
     * @return a Double value.
     */
    private static Double readY(){
        System.out.print("Enter coordinate y: ");
        Double y = Readable.scanner.nextDouble();
        Readable.scanner.nextLine(); // считывает оставшийся в буфере перевод строки
        return y;
    }
}