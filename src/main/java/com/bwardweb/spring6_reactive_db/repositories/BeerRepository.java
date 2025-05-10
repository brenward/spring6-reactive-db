package com.bwardweb.spring6_reactive_db.repositories;

import com.bwardweb.spring6_reactive_db.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {

}
