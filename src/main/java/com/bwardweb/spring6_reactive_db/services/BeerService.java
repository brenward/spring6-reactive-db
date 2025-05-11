package com.bwardweb.spring6_reactive_db.services;

import com.bwardweb.spring6_reactive_db.model.BeerDTO;
import reactor.core.publisher.Flux;

public interface BeerService {
    Flux<BeerDTO> listBeers();
}
