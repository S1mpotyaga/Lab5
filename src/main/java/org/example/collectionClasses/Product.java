package org.example.collectionClasses;

import org.example.collectionClasses.enums.UnitOfMeasure;
import lombok.Data;

import java.util.Comparator;

@Data
public class Product{
    private int id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Long price;
    private UnitOfMeasure unitOfMeasure;
    private Organization organization;
}
