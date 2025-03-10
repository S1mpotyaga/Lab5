package org.example.collectionClasses;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static java.lang.Math.sqrt;

@Data
@XmlRootElement(name="coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    @XmlElement(name="x")
    private Float x;
    @XmlElement(name="y")
    private Double y;

    public Double len(){
        return sqrt(x * x + y * y);
    }

    public boolean isBig(Coordinates b){
        return len() < b.len();
    }
}
