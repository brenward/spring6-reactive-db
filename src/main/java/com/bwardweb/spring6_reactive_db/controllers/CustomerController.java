package com.bwardweb.spring6_reactive_db.controllers;

import com.bwardweb.spring6_reactive_db.model.CustomerDTO;
import com.bwardweb.spring6_reactive_db.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private static final String CUSTOMER_PATH = "/api/v2/customer";
    private static final String CUSTOMER_PATH_ID = "/api/v2/customer/{customerId}";

    @GetMapping(CUSTOMER_PATH)
    public Flux<CustomerDTO> listCustomers() {
        return customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public Mono<CustomerDTO> getCustomerById(@PathVariable Integer customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping(CUSTOMER_PATH)
    public Mono<ResponseEntity<Void>> saveNewCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomer(customerDTO).map(
                savedDto -> ResponseEntity.created(
                        UriComponentsBuilder.fromHttpUrl("http://localhost:8080/" + CUSTOMER_PATH + "/" + savedDto.getId()).build().toUri())
                        .build()
        );
    }

}
