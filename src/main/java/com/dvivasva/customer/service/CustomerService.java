package com.dvivasva.customer.service;

import com.dvivasva.customer.dto.CustomerDto;
import com.dvivasva.customer.repository.CustomerRepository;
import com.dvivasva.customer.utils.CustomerUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
    /**
     * this is repository.
     */
    private final CustomerRepository customerRepository;

    /**
     * @param customerRepository .
     */
    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * @return Flux<CustomerDto>.
     */
    public Flux<CustomerDto> read() {
        return customerRepository.findAll().map(CustomerUtil::entityToDto);
    }

    /**
     * @param entityToDto .
     * @return Mono<Customer>.
     */
    public Mono<CustomerDto> create(final Mono<CustomerDto> entityToDto) {

        Mono<CustomerDto> result = entityToDto.map(
                p -> {
                    if (p.getTypeCustomer().equals("Empresa")) {
                        p.setProfile("PYME");
                    } else {
                        p.setProfile("VID");
                    }
                    return p;
                });
        return result.map(CustomerUtil::dtoToEntity)
                .flatMap(customerRepository::save)
                .map(CustomerUtil::entityToDto);

    }

    /**
     * @param customerDtoMono .
     * @param id              .
     * @return update data.
     */
    public Mono<CustomerDto> update(
            final Mono<CustomerDto> customerDtoMono, final String id) {
        return customerRepository.findById(id)
                .flatMap(p -> customerDtoMono.map(CustomerUtil::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(customerRepository::save)
                .map(CustomerUtil::entityToDto);
    }

    /**
     * @param id .
     * @return empty.
     */
    public Mono<Void> delete(final String id) {
        return customerRepository.deleteById(id);
    }


    /**
     * @param id param.
     * @return mono Customer.
     */
    public Mono<CustomerDto> findById(final String id) {
        return customerRepository.findById(id).map(CustomerUtil::entityToDto);
    }


}
