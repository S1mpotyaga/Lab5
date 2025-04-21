package org.example.collectionClasses.readers;

import org.example.collectionClasses.Coordinates;
import org.example.collectionClasses.Product;
import org.example.collectionClasses.types.UnitOfMeasure;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads the Product object from the console.
 */
public interface ProductReadable {

    /**
     * Reads a new Product object from the console.
     *
     * @return the Product object.
     */
    static public Product readProduct(Scanner scanner) {
        Product result = new Product();
        try {
            System.out.print("Enter name: ");
            result.setName(readName(scanner));
        } catch (NoSuchElementException e) {
            System.out.print("Not found name. Enter name please again: ");
            result.setName(readName(Readable.scanner));
        }
        try {
            System.out.print("Enter price: ");
            result.setPrice(readPrice(scanner));
        } catch (NoSuchElementException e) {
            System.out.print("Not found price. Enter price please again: ");
            result.setPrice(readPrice(Readable.scanner));
        }
        try {
            System.out.println("Enter unit of measure.");
            UnitOfMeasure.printAll();
            result.setUnitOfMeasure(UnitOfMeasureReadable.readUnitOfMeasure(scanner));
        } catch (NoSuchElementException e) {
            System.out.println("Not found field UnitOfMeasure. Enter please again.");
            result.setUnitOfMeasure(UnitOfMeasureReadable.readUnitOfMeasure(Readable.scanner));
        }
        result.setOrganization(OrganizationReadable.readOrganization(scanner));
        result.setCoordinates(CoordinatesReadable.readCoordinates(scanner));
        return result;
    }

    /**
     * Reads a new Product object with the given id from the console.
     *
     * @param id the given id.
     * @return the Product object.
     */
    static public Product readProduct(Scanner scanner, int id) {
        Product result = new Product(id);
        try {
            System.out.print("Enter name: ");
            result.setName(readName(scanner));
        } catch (NoSuchElementException e) {
            System.out.print("Not found name. Enter name please again: ");
            result.setName(readName(Readable.scanner));
        }
        try {
            System.out.print("Enter price: ");
            result.setPrice(readPrice(scanner));
        } catch (NoSuchElementException e) {
            System.out.print("Not found price. Enter price please again: ");
            result.setPrice(readPrice(Readable.scanner));
        }
        try {
            System.out.println("Enter unit of measure.");
            UnitOfMeasure.printAll();
            result.setUnitOfMeasure(UnitOfMeasureReadable.readUnitOfMeasure(scanner));
        } catch (NoSuchElementException e) {
            System.out.print("Not found field UnitOfMeasure. Enter please again:");
            result.setUnitOfMeasure(UnitOfMeasureReadable.readUnitOfMeasure(scanner));
        }
        result.setOrganization(OrganizationReadable.readOrganization(Readable.scanner));
        result.setCoordinates(CoordinatesReadable.readCoordinates(Readable.scanner));
        return result;
    }

    /**
     * Reads the Price field from the console.
     *
     * @return the Long price
     */
    private static Long readPrice(Scanner scanner) throws NoSuchElementException{
        String resultTmp = scanner.nextLine();
        try {
            return Long.parseLong(resultTmp);
        } catch (NumberFormatException e) {
            System.out.print("Wrong format. Enter price again: ");
            return readPrice(Readable.scanner);
        }
    }

    private static String readName(Scanner scanner) throws NoSuchElementException {
        String result = scanner.nextLine();
        if (result.isEmpty()){
            System.out.println("Incorrect name. Enter name again: ");
            return readName(scanner);
        }
        return result;
    }
}