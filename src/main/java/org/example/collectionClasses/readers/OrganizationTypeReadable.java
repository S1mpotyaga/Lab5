package org.example.collectionClasses.readers;

import org.example.collectionClasses.types.OrganizationType;
import org.example.collectionClasses.types.UnitOfMeasure;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads the organization type.
 */
public interface OrganizationTypeReadable{

    /**
     * Gets the organization type.
     * @return an object of type OrganizationType.
     */
    public static OrganizationType readOrganizationType(Scanner scanner) throws NoSuchElementException{
        String tmp = scanner.nextLine();
        boolean flag = false;
        for (OrganizationType elem: OrganizationType.values())
            if (tmp.equals(elem.toString())){
                flag = true;
            }
        if (!flag){
            System.out.println("Wrong input OrganizationType. Enter please again one field from the list:");
            OrganizationType.printAll();
            return readOrganizationType(Readable.scanner);
        }
        return OrganizationType.fromString(tmp);
    }
}
