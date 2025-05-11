package com.bwardweb.spring6_reactive_db.controllers;

import com.bwardweb.spring6_reactive_db.model.CustomerDTO;
import com.bwardweb.spring6_reactive_db.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

}
