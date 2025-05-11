package com.bwardweb.spring6_reactive_db.mappers;

import com.bwardweb.spring6_reactive_db.domain.Beer;
import com.bwardweb.spring6_reactive_db.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
