package org.example.readers;

import org.example.collectionClasses.Organization;
import org.example.collectionClasses.types.OrganizationType;
import org.example.queries.QueryType;

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
    static public Organization readOrganization() {
        Organization result = new Organization();
        try {
            System.out.print("Enter name of organization: ");
            result.setName(readName());
        } catch (NoSuchElementException e) {
            System.out.print("Not found name of Organization. Enter name again: ");
            result.setFullName(OrganizationReadable.readName());
        }
        try {
            System.out.print("Enter full name of organization: ");
            result.setFullName(readFullName());
        } catch (NoSuchElementException e) {
            System.out.print("Not found FullName of Organization. Enter FullName of Organization again: ");
            result.setFullName(OrganizationReadable.readFullName());
        }
        try {
            System.out.println("Enter organization type.");
            result.setOrganizationType(OrganizationTypeReadable.readOrganizationType());
        } catch (NoSuchElementException e) {
            System.out.print("Not found OrganizationType. Enter Organization type again: ");
            result.setOrganizationType(OrganizationTypeReadable.readOrganizationType());
        }
        return result;
    }

    /**
     * Reads the FullName field.
     *
     * @return full name
     */
    private static String readFullName() throws NoSuchElementException{
        return Readable.scanner.nextLine();
    }

    /**
     * Reads the short name.
     *
     * @return name
     */
    private static String readName() throws NoSuchElementException{
        return Readable.scanner.nextLine();
    }

    static public Organization readOrganization(Scanner scanner) throws NoSuchElementException{
        Organization result = new Organization();
        result.setName(readName(scanner));
        result.setFullName(readFullName(scanner));
        result.setOrganizationType(OrganizationTypeReadable.readOrganizationType(scanner));
        return result;
    }

    private static String readFullName(Scanner scanner) throws NoSuchElementException{
        return scanner.nextLine();
    }

    private static String readName(Scanner scanner) throws NoSuchElementException{
        return scanner.nextLine();
    }
}