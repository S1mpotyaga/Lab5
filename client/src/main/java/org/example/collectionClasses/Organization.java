package org.example.collectionClasses;

import org.example.collectionClasses.types.OrganizationType;
import lombok.Data;

import java.io.Serializable;


/**
 * A class that describes the Organization of a Product object.
 */
@Data
public class Organization implements Serializable {
    private Integer id;
    private String name;
    private String fullName;
    private OrganizationType organizationType;
    /**
     * Number of Organizations created.
     */
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