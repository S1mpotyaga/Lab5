package org.example.collectionClasses;

import lombok.Data;

import jakarta.xml.bind.annotation.*;

import static java.lang.Math.sqrt;

/**
 * Class describing Coordinates of the Product object.
 */
@Data
@XmlRootElement(name="coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    @XmlElement(name="x")
    private Float x;
    @XmlElement(name="y")
    private Double y;

    /**
     * Distance from the origin to the current object.
     * @return Double distance.
     */
    public Double len(){
        return sqrt(x * x + y * y);
    }

    /**
     * Method for comparing the current Coordinates object with the b object.
     * @param b is the second operand of the comparison.
     * @return a boolean object. true if the current object is less than b, otherwise false.
     */
    public boolean isBig(Coordinates b){
        return len() < b.len();
    }
}
