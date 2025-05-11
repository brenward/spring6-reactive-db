package com.bwardweb.spring6_reactive_db.mappers;

import com.bwardweb.spring6_reactive_db.domain.Customer;
import com.bwardweb.spring6_reactive_db.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);
}
