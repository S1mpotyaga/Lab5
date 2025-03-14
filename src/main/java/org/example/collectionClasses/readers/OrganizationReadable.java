package org.example.collectionClasses.readers;

import org.example.collectionClasses.Organization;

/**
 * Reads the Organization object from the console.
 */
public interface OrganizationReadable extends Readable {

    /**
     * reads the Organization object from the console.
     * @return the Organization object.
     */
    static public Organization readOrganization(){
        Organization result = new Organization();
        result.setName(readName());
        result.setFullName(readFullName());
        result.setOrganizationType(OrganizationTypeReadable.readOrganizationType());
        setIncorrectFields(result);
        return result;
    }

    /**
     * sets invalid org fields.
     * @param org Organization object to check.
     */
    static public void setIncorrectFields(Organization org){
        if (org.getName().isEmpty()){
            org.setName(readName());
        }
        if (org.getFullName().isEmpty() || org.getFullName().length() > 554){
            org.setFullName(readFullName());
        }
        if (org.getOrganizationType() == null){
            org.setOrganizationType(OrganizationTypeReadable.readOrganizationType());
        }
    }

    /**
     * Reads the FullName field.
     * @return full name
     */
    private static String readFullName(){
        System.out.print("Enter full name organization: ");
        return Readable.scanner.nextLine();
    }

    /**
     * Reads the short name.
     * @return name
     */
    private static String readName(){
        System.out.print("Enter name organization: ");
        return Readable.scanner.nextLine();
    }
}