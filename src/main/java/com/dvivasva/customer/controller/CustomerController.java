package com.dvivasva.customer.controller;
import com.dvivasva.customer.dto.CustomerDto;
import com.dvivasva.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/sms")
    public String getMessage(){
        String hello="Hello this is spring with azure :)";
        return hello;
    }


    @GetMapping
    public Flux<CustomerDto> read() {
        return customerService.read();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CustomerDto> create(@RequestBody Mono<CustomerDto> customerDtoMono){
        return customerService.create(customerDtoMono);
    }

    @PutMapping("/{id}")
    public Mono<CustomerDto> update( @RequestBody Mono<CustomerDto> customerDtoMono,@PathVariable String id){
        return customerService.update(customerDtoMono,id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return customerService.delete(id);
    }

    @GetMapping("/{id}")
    public Mono<CustomerDto> findById(@PathVariable String id){
        return customerService.findById(id);
    }
}
