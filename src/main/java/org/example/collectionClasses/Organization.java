package org.example.collectionClasses;

import org.example.collectionClasses.enums.OrganizationType;
import lombok.Data;

@Data
public class Organization {
    private Integer id;
    private String name;
    private String fullName;
    private OrganizationType organizationType;
}