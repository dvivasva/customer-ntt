package com.dvivasva.customer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Customer {
    /**
     * key.
     */
    @Id
    private String id;
    /**
     * name.
     */
    private String name;
    /**
     * lastname.
     */
    private String lastname;
    /**
     * dni.
     */
    private String dni;
    /**
     * type customer personal or enterprise.
     */
    private String typeCustomer;
    /**
     * profile or role VID PYME.
     */
    private String profile;
}