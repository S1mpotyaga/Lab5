package org.example.collectionClasses.readers;

import org.example.collectionClasses.types.OrganizationType;

/**
 * Reads the organization type.
 */
public interface OrganizationTypeReadable{

    /**
     * Gets the organization type.
     * @return an object of type OrganizationType.
     */
    public static OrganizationType readOrganizationType(){
        System.out.print("Please, enter only one field from the list: ");
        for (OrganizationType elem : OrganizationType.values()) {
            System.out.println(elem.toString());
        }
        String tmp = Readable.scanner.nextLine();
        return OrganizationType.fromString(tmp);
    }
}
