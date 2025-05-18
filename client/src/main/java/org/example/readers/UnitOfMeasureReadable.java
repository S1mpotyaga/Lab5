package org.example.readers;

import org.example.collectionClasses.types.UnitOfMeasure;
import org.example.queries.QueryType;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads a UnitOfMeasure object from the console.
 */
public interface UnitOfMeasureReadable {

    /**
     * Reads a UnitOfMeasure object from the console.
     *
     * @return an object of type UnitOfMeasure.
     */
    public static UnitOfMeasure readUnitOfMeasure() throws NoSuchElementException{
        UnitOfMeasure.printAll();
        String result = Readable.scanner.nextLine();
        boolean flag = false;
        for (UnitOfMeasure elem : UnitOfMeasure.values()) {
            if (result.equals(elem.toString())) {
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Wrong input UnitOfMeasure. Enter please again one field from the list:");
            UnitOfMeasure.printAll();
            return readUnitOfMeasure();
        }
        return UnitOfMeasure.fromString(result);
    }

    public static UnitOfMeasure readUnitOfMeasure(Scanner scanner) throws NoSuchElementException {
        String result = scanner.nextLine();
        boolean flag = false;
        for (UnitOfMeasure elem : UnitOfMeasure.values()) {
            if (result.equals(elem.toString())) {
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Wrong input UnitOfMeasure. Enter please again one field from the list:");
            UnitOfMeasure.printAll();
            return readUnitOfMeasure();
        }
        return UnitOfMeasure.fromString(result);
    }
}