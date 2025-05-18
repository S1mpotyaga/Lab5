package org.example.readers;

import org.example.collectionClasses.Coordinates;
import org.example.collectionClasses.Organization;
import org.example.collectionClasses.Product;
import org.example.collectionClasses.types.UnitOfMeasure;
import org.example.queries.QueryType;

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
    static Product readProduct() throws NoSuchElementException{
        Product result = new Product();
        System.out.print("Enter name: ");
        result.setName(readName());
        System.out.print("Enter price: ");
        result.setPrice(readPrice());
        System.out.println("Enter unit of measure.");
        result.setUnitOfMeasure(UnitOfMeasureReadable.readUnitOfMeasure());
        result.setOrganization(OrganizationReadable.readOrganization());
        result.setCoordinates(CoordinatesReadable.readCoordinates());
        return result;
    }

    /**
     * Reads a new Product object with the given id from the console.
     *
     * @param id the given id.
     * @return the Product object.
     */
    static Product readProduct(int id) throws NoSuchElementException{
        Product result = new Product(id);
        System.out.print("Enter name: ");
        result.setName(readName());
        System.out.print("Enter price: ");
        result.setPrice(readPrice());
        System.out.println("Enter unit of measure.");
        UnitOfMeasure.printAll();
        result.setUnitOfMeasure(UnitOfMeasureReadable.readUnitOfMeasure());
        result.setOrganization(OrganizationReadable.readOrganization());
        result.setCoordinates(CoordinatesReadable.readCoordinates());
        return result;
    }

    /**
     * Reads the Price field from the console.
     *
     * @return the Long price
     */
    private static Long readPrice() throws NoSuchElementException{
        String resultTmp = Readable.scanner.nextLine();
        try {
            return Long.parseLong(resultTmp);
        } catch (NumberFormatException e) {
            System.out.print("Wrong format. Enter price again: ");
            return readPrice();
        }
    }

    private static String readName() throws NoSuchElementException {
        String result = Readable.scanner.nextLine();
        if (result.isEmpty()){
            System.out.println("Incorrect name. Enter name again: ");
            return readName();
        }
        return result;
    }

    static Object readProduct(Scanner scanner) throws NoSuchElementException{
        Product result = new Product();
        result.setName(readName(scanner));
        result.setPrice(readPrice(scanner));
        result.setUnitOfMeasure(UnitOfMeasureReadable.readUnitOfMeasure(scanner));
        result.setOrganization(OrganizationReadable.readOrganization(scanner));
        result.setCoordinates(CoordinatesReadable.readCoordinates(scanner));
        return result;
    }

    static Object readProduct(Scanner scanner, int id) throws NoSuchElementException{
        Product result = new Product(id);
        result.setName(readName(scanner));
        result.setPrice(readPrice(scanner));
        result.setUnitOfMeasure(UnitOfMeasureReadable.readUnitOfMeasure(scanner));
        result.setOrganization(OrganizationReadable.readOrganization(scanner));
        result.setCoordinates(CoordinatesReadable.readCoordinates(scanner));
        return result;
    }

    private static Long readPrice(Scanner scanner) throws NoSuchElementException, NumberFormatException {
        try{
            return Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.print("Wrong price.");
            throw new NoSuchElementException();
        }
    }

    private static String readName(Scanner scanner) throws NoSuchElementException {
        return scanner.nextLine();
    }
}