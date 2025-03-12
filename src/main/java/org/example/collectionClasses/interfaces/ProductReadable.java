package org.example.collectionClasses.interfaces;

import org.example.collectionClasses.Product;
import org.example.collectionClasses.enums.UnitOfMeasure;

import java.util.Scanner;

public interface ProductReadable{

    static public Product readProduct(){
        Product result = new Product();
        result.setName(readName());
        result.setPrice(readPrice());
        result.setUnitOfMeasure(UnitOfMeasure.readUnitOfMeasure());
        result.setOrganization(OrganizationReadable.readOrganization());
        result.setCoordinates(CoordinatesReadable.readCoordinates());
        setIncoorectFields(result);
        return result;
    }

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
            product.setUnitOfMeasure(UnitOfMeasure.readUnitOfMeasure());
        }
        if (product.getOrganization() == null){
            product.setOrganization(OrganizationReadable.readOrganization());
        }
    }

    private static Long readPrice(){
        Scanner in = new Scanner(System.in);
        System.out.println("Null price. Enter price: ");
        return in.nextLong();
    }

    private static String readName(){
        Scanner in = new Scanner(System.in);
        String result = in.next();
        while (result.isEmpty()){
            System.out.println("Incorrect name. Enter name: ");
            result = in.next();
        }
        in.close();
        return result;
    }
}