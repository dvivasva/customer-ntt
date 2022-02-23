package com.dvivasva.customer.service;

import com.dvivasva.customer.controller.CustomerController;
import com.dvivasva.customer.dto.CustomerDto;
import com.dvivasva.customer.util.DataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebFluxTest(CustomerController.class)
class CustomerServiceTest {

    @Autowired
    private  WebTestClient webTestClient;

    @MockBean
    private CustomerService customerService;

    @Test
    void read() {
        when(customerService.read()).thenReturn(DataUtil.customerDtoFlux());
       StepVerifier.create( DataUtil.getResponseBody(webTestClient,""))
                .expectSubscription()
                .expectNextCount(2)
               .verifyComplete();
    }
    @Test
    void create() {
        Mono<CustomerDto> customerDtoMono=Mono.
                just(new CustomerDto("CUS-003",
                        "Gabriela",
                        "Galvan",
                        12345678,
                        "Personal",
                        "VID" ));
        when(customerService.create(customerDtoMono)).thenReturn(customerDtoMono);
        webTestClient.post().uri("/customer")
                .body(Mono.just(customerDtoMono),CustomerDto.class)
                .exchange()
                .expectStatus().isCreated(); // 201
    }

    @Test
    void update() {
        Mono<CustomerDto> customerDtoMono=Mono.
                just(new CustomerDto("CUS-001",
                        "David",
                        "Vivas",
                        12345678,
                        "Personal",
                        "" ));
        when(customerService.update(customerDtoMono,"CUS-001")).thenReturn(customerDtoMono);

        webTestClient.put().uri("/customer/CUS-001")
                .body(Mono.just(customerDtoMono),CustomerDto.class)
                .exchange()
                .expectStatus().isOk();//200
    }

    @Test
    void delete() {
        given(customerService.delete(any())).willReturn(Mono.empty());
        webTestClient.delete().uri("/customer/CUS-001")
                .exchange()
                .expectStatus().isOk();//200
    }

    @Test
    void findById() {

        String id="CUS-001";
        when(customerService.findById(any())).thenReturn(DataUtil.getCustomerDto(id));
        StepVerifier.create(DataUtil.getResponseBody(webTestClient,"/"+id))
                .expectSubscription()
                .expectNextMatches(p->p.getLastname().equals("Vivas"))
                .verifyComplete();
    }


}