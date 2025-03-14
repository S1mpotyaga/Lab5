package org.example.collectionClasses.readers;

import org.example.collectionClasses.Product;

/**
 * Reads the Product object from the console.
 */
public interface ProductReadable {

    /**
     * Reads a new Product object from the console.
     * @return the Product object.
     */
    static public Product readProduct(){
        Product result = new Product();
        result.setName(readName());
        result.setPrice(readPrice());
        result.setUnitOfMeasure(UnitOfMeasureReadable.readUnitOfMeasure());
        result.setOrganization(OrganizationReadable.readOrganization());
        result.setCoordinates(CoordinatesReadable.readCoordinates());
        setIncoorectFields(result);
        return result;
    }

    /**
     * Reads a new Product object with the given id from the console.
     * @param id the given id.
     * @return the Product object.
     */
    static public Product readProduct(int id){
        Product result = new Product(id);
        result.setName(readName());
        result.setPrice(readPrice());
        result.setUnitOfMeasure(UnitOfMeasureReadable.readUnitOfMeasure());
        result.setOrganization(OrganizationReadable.readOrganization());
        result.setCoordinates(CoordinatesReadable.readCoordinates());
        setIncoorectFields(result);
        return result;
    }

    /**
     * Checks and sets invalid product fields.
     * @param product the Product object to check.
     */
    static public void setIncoorectFields(Product product){
        if (product.getName().isEmpty()){
            product.setName(readName());
        }
        if (product.getCoordinates() == null){
            product.setCoordinates(CoordinatesReadable.readCoordinates());
        }
        if (product.getPrice() == null){
            product.setPrice(readPrice());
        }
        if (product.getUnitOfMeasure() == null){
            product.setUnitOfMeasure(UnitOfMeasureReadable.readUnitOfMeasure());
        }
        if (product.getOrganization() == null){
            product.setOrganization(OrganizationReadable.readOrganization());
        }
    }

    /**
     * Reads the Price field from the console.
     * @return the Long price
     */
    private static Long readPrice(){;
        System.out.print("Enter price: ");
        Long result = Readable.scanner.nextLong();
        Readable.scanner.nextLine(); // считывает оставшийся в буфере перевод строки
        return result;
    }

    /**
     * Reads name from console.
     * @return name
     */
    private static String readName(){
        System.out.print("Enter name new Product: ");
        String result = Readable.scanner.nextLine();
        while (result.isEmpty()){
            System.out.print("Incorrect name. Enter name: ");
            result = Readable.scanner.nextLine();
        }
        return result;
    }
}