package com.bwardweb.spring6_reactive_db.services;

import com.bwardweb.spring6_reactive_db.mappers.BeerMapper;
import com.bwardweb.spring6_reactive_db.model.BeerDTO;
import com.bwardweb.spring6_reactive_db.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .map(beerMapper::beerToBeerDto);
    }
}
