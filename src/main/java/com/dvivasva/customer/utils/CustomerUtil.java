package com.dvivasva.customer.utils;

import com.dvivasva.customer.dto.CustomerDto;
import com.dvivasva.customer.model.Customer;
import org.springframework.beans.BeanUtils;

public final class CustomerUtil {
    private  CustomerUtil() {
    }
    /**
     * @param customer .
     * @return mono dto.
     */
    public static CustomerDto entityToDto(final Customer customer) {
        var customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }
    /**
     * @param customerDto .
     * @return mono entity.
     */
    public static Customer dtoToEntity(final CustomerDto customerDto) {
        var entity = new Customer();
        BeanUtils.copyProperties(customerDto, entity);
        return entity;
    }

}
