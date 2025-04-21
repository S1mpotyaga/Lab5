package org.example.collectionClasses.readers;

import org.example.collectionClasses.Organization;
import org.example.collectionClasses.types.OrganizationType;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads the Organization object from the console.
 */
public interface OrganizationReadable{

    /**
     * reads the Organization object from the console.
     *
     * @return the Organization object.
     */
    static public Organization readOrganization(Scanner scanner) {
        Organization result = new Organization();
        try {
            System.out.print("Enter name of organization: ");
            result.setName(readName(scanner));
        } catch (NoSuchElementException e) {
            System.out.print("Not found name of Organization. Enter name again: ");
            result.setFullName(OrganizationReadable.readName(Readable.scanner));
        }
        try {
            System.out.print("Enter full name of organization: ");
            result.setFullName(readFullName(scanner));
        } catch (NoSuchElementException e) {
            System.out.print("Not found FullName of Organization. Enter FullName of Organization again: ");
            result.setFullName(OrganizationReadable.readFullName(Readable.scanner));
        }
        try {
            System.out.println("Enter organization type.");
            OrganizationType.printAll();
            result.setOrganizationType(OrganizationTypeReadable.readOrganizationType(scanner));
        } catch (NoSuchElementException e) {
            System.out.print("Not found OrganizationType. Enter Organization type again: ");
            result.setOrganizationType(OrganizationTypeReadable.readOrganizationType(Readable.scanner));
        }
        return result;
    }

    /**
     * Reads the FullName field.
     *
     * @return full name
     */
    private static String readFullName(Scanner scanner) throws NoSuchElementException{
        return scanner.nextLine();
    }

    /**
     * Reads the short name.
     *
     * @return name
     */
    private static String readName(Scanner scanner) throws NoSuchElementException{
        return scanner.nextLine();
    }
}