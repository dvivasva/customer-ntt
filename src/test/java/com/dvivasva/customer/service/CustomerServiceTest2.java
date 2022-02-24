package com.dvivasva.customer.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Provider.Service;

import com.dvivasva.customer.dto.CustomerDto;
import com.dvivasva.customer.model.Customer;
import com.dvivasva.customer.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class CustomerServiceTest2 {

    @Mock
    private static CustomerRepository customerRepository;

    private static CustomerService service;

    @BeforeAll
    public static void setUp() {
        customerRepository = mock(CustomerRepository.class);
        service = new CustomerService(customerRepository);
    }

    @Test
    void readTest() {
        Customer customer = new Customer();
        customer.setDni(0);
        customer.setId("id");
        customer.setLastname("lastname");
        customer.setName("name");
        customer.setProfile("profile");
        customer.setTypeCustomer("typeCustomer");

        when(customerRepository.findAll()).thenReturn(Flux.just(customer,customer));

        Flux<CustomerDto> result = service.read();

        StepVerifier.create( result)
                .expectSubscription()
                .expectNextCount(2)
                .verifyComplete();
    }
    
}
