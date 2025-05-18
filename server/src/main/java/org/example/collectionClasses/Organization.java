package org.example.collectionClasses;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import org.example.collectionClasses.types.OrganizationType;

import java.io.Serializable;


/**
 * A class that describes the Organization of a Product object.
 */
@Data
@XmlRootElement(name="organization")
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    @XmlElement(name="id")
    private Integer id;
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="fullName")
    private String fullName;
    @XmlElement(name="organizationType")
    private OrganizationType organizationType;

    /**
     * Number of Organizations created.
     */
    @XmlTransient
    private static int organizationNumber = 0;

    /**
     * Constructor that initializes the Organization base values.
     */
    public Organization() {
        this.id = ++organizationNumber;
    }

    /**
     * Method that compares the current Organization object with the b object.
     * @param b is the second operand of the comparison.
     * @return the result of the comparison. -1 if the current object is less than b, 0 if they are equal, and 1 if b is greater.
     */
    public boolean isLow(Organization b) {
        int check1 = 0;
        if (this.id < b.getId()) {
            check1 = -1;
        } else if (this.id > b.getId()) {
            check1 = 1;
        }
        int check2 = this.fullName.compareToIgnoreCase(b.getFullName());
        if (check1 != 0) {
            return (check1 == -1);
        }
        return (check2 == -1);
    }
}