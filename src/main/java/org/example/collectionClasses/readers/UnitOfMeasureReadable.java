package org.example.collectionClasses.readers;

import org.example.collectionClasses.types.UnitOfMeasure;

/**
 * Reads a UnitOfMeasure object from the console.
 */
public interface UnitOfMeasureReadable{

    /**
     * Reads a UnitOfMeasure object from the console.
     * @return an object of type UnitOfMeasure.
     */
    public static UnitOfMeasure readUnitOfMeasure(){
        System.out.println("Please, enter only one field from the list:");
        for (UnitOfMeasure elem : UnitOfMeasure.values()) {
            System.out.println(elem.toString());
        }
        String result = Readable.scanner.nextLine();
        return UnitOfMeasure.fromString(result);
    }
}
