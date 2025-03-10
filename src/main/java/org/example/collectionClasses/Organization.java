package org.example.collectionClasses;

import org.example.collectionClasses.enums.OrganizationType;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.Comparator;

@Data
@XmlRootElement(name="organization")
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization {
    @XmlElement(name="id")
    private Integer id;
    @XmlElement(name="name")
    private String name;
    @XmlElement(name="fullName")
    private String fullName;
    @XmlElement(name="organizationType")
    private OrganizationType organizationType;
    @XmlTransient
    private static int organizationNumber = 0;

    public Organization() {
        this.id = ++organizationNumber;
    }

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