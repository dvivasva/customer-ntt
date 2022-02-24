package com.dvivasva.customer.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.dvivasva.customer.dto.CustomerDto;
import com.dvivasva.customer.service.CustomerService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class CustomerControllerTest {

    private static WebTestClient webTestClient;

    @Mock
    private static CustomerService customerService;

    @BeforeAll
    public static void setUp() {
        customerService = mock(CustomerService.class);
        webTestClient = WebTestClient.bindToController(new CustomerController(customerService))
                .configureClient()
                .baseUrl("/customer")
                .build();
    }

    @Test
    void readTest() {
        CustomerDto customerDto = new CustomerDto("CUS-003",
                "Gabriela",
                "Galvan",
                12345678,
                "Personal",
                "VID" );
        Flux<CustomerDto> customerDtoMono=Flux.just(customerDto);

        when(customerService.read()).thenReturn(customerDtoMono);

        Flux<CustomerDto> responseBody = webTestClient.get()
                .exchange()
                .expectStatus().isOk()  // Http Status 200
                .returnResult(CustomerDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectNext(customerDto)
                .verifyComplete();
    }
}
