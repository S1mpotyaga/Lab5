package org.example.collectionClasses;

import org.example.collectionClasses.enums.UnitOfMeasure;
import lombok.Data;
import org.example.collectionClasses.interfaces.ProductReadable;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements ProductReadable{
    @XmlElement(name="id")
    private int id;
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="coordinates")
    private Coordinates coordinates;
    @XmlElement(name="price")
    private Long price;
    @XmlElement(name="unitOfMeasure")
    private UnitOfMeasure unitOfMeasure;
    @XmlElement(name="organization")
    private Organization organization;
    @XmlElement(name="creationDate")
    private java.time.LocalDate creationDate;
    @XmlTransient
    private static int productNumber = 0;

    public Product(){
        this.id = ++productNumber;
        this.creationDate = LocalDate.now();
    }

    public Product(int id){
        this.id = id;
        this.creationDate = LocalDate.now();
    }
}