package com.dvivasva.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {
    /**
     * key id.
     */
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
    private int dni;
    /**
     * type customer personal or enterprise.
     */
    private String typeCustomer;
    /**
     * profile or role VID PYME.
     */
    private String profile;
}
