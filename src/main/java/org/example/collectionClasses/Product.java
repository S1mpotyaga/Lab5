package org.example.collectionClasses;

import org.example.collectionClasses.types.UnitOfMeasure;
import lombok.Data;
import org.example.collectionClasses.readers.ProductReadable;

import jakarta.xml.bind.annotation.*;

import java.time.LocalDate;

/**
 * TreeMap collection key.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements ProductReadable {
    @XmlElement(name = "id")
    private int id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "coordinates")
    private Coordinates coordinates;
    @XmlElement(name = "price")
    private Long price;
    @XmlElement(name = "unitOfMeasure")
    private UnitOfMeasure unitOfMeasure;
    @XmlElement(name = "organization")
    private Organization organization;
    @XmlTransient
    private java.time.LocalDate creationDate;
    /**
     * Number of created Products.
     */
    @XmlTransient
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
}