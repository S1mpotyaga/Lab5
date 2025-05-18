package org.example.collectionClasses;

import org.example.collectionClasses.types.UnitOfMeasure;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * TreeMap collection key.
 */
@Data
public class Product implements Serializable {
    private int id;
    private String name;
    private Coordinates coordinates;
    private Long price;
    private UnitOfMeasure unitOfMeasure;
    private Organization organization;
    private LocalDate creationDate;
    /**
     * Number of created Products.
     */
    private static int productNumber = 0;

    /**
     * A constructor that initializes the object's base values.
     */
    public Product() {
        this.id = ++productNumber;
        this.creationDate = LocalDate.now();
    }

    /**
     * Constructor that initializes the object's base values and the specified id.
     * * @param id the specified id.
     */
    public Product(int id) {
        this.id = id;
        this.creationDate = LocalDate.now();
    }

    public static void setProductNumber(int x) {
        productNumber = x + 1;
    }
}