package com.dvivasva.customer.controller;

import com.dvivasva.customer.dto.CustomerDto;
import com.dvivasva.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    /**
     * customerService.
     */
    private final CustomerService customerService;

    /**
     * @return message.
     */
    @GetMapping("/sms")
    public String getMessage() {
        return "Hello this is spring with azure :)";
    }

    /**
     * @return flux
     */
    @GetMapping
    public Flux<CustomerDto> read() {
        return customerService.read();
    }

    /**
     * @param customerDtoMono .
     * @return 201.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CustomerDto> create(
            @RequestBody final Mono<CustomerDto> customerDtoMono) {
        return customerService.create(customerDtoMono);
    }

    /**
     * @param customerDtoMono .
     * @param id              .
     * @return mono.
     */
    @PutMapping("/{id}")
    public Mono<CustomerDto> update(
            @RequestBody final Mono<CustomerDto> customerDtoMono,
            @PathVariable final String id) {
        return customerService.update(customerDtoMono, id);
    }

    /**
     * @param id .
     * @return void delete.
     */
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable final String id) {
        return customerService.delete(id);
    }

    /**
     * @param id .
     * @return mono.
     */
    @GetMapping("/{id}")
    public Mono<CustomerDto> findById(@PathVariable final String id) {
        return customerService.findById(id);
    }
}
