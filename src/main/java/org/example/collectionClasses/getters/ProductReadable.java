package org.example.collectionClasses.getters;

import org.example.collectionClasses.Organization;
import org.example.collectionClasses.Product;
import org.example.collectionClasses.enums.UnitOfMeasure;
import org.example.collectionClasses.getters.*;

import java.util.Scanner;

public interface ProductReadable{

    static public Product readProduct(){
        Scanner scanner = new Scanner(System.in);
        Product result = new Product();
        System.out.print("Enter name Product: ");
        result.setName(scanner.nextLine());
        System.out.print("Enter price Product: ");
        result.setPrice(scanner.nextLong());
        result.setUnitOfMeasure(UnitOfMeasure.readUnitOfMeasure(scanner));
        scanner.close();
        result.setOrganization(OrganizationReadable.readOrganization());
        result.setCoordinates(CoordinatesReadable.readCoordinates());
        return result;
    }
}