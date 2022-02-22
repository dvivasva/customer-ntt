package com.dvivasva.customer.util;

import com.dvivasva.customer.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DataUtil {

    public static Logger logger = LoggerFactory.getLogger(DataUtil.class);

    public static Flux<CustomerDto> customerDtoFlux(){
        return Flux.just(
                new CustomerDto("CUS-001",
                        "David",
                        "Vivas",
                        12345678,
                        "Personal",
                        "VID" ),
                new CustomerDto("CUS-002",
                        "Danae",
                        "Linares",
                        12345678,
                        "Empresa",
                        "PYME" )
        );
    }
    public static Flux<CustomerDto> getResponseBody(WebTestClient webTestClient, String URI){
        return webTestClient.get().uri("/customer"+URI)
                .exchange()
                .expectStatus().isOk()
                .returnResult(CustomerDto.class)
                .getResponseBody();
    }
    public static Mono<CustomerDto> getCustomerDto(String id){
        logger.info("print id "+ id);
        return customerDtoFlux().filter(e->e.getId().equals(id))
                .map(p-> p).next();
    }



}
