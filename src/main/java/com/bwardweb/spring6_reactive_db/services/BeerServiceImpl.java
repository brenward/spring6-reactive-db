package com.bwardweb.spring6_reactive_db.services;

import com.bwardweb.spring6_reactive_db.mappers.BeerMapper;
import com.bwardweb.spring6_reactive_db.model.BeerDTO;
import com.bwardweb.spring6_reactive_db.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Override
    public Mono<BeerDTO> getBeerById(Integer beerId) {
        return beerRepository.findById(beerId).map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> saveNewBeer(BeerDTO beerDTO) {
        return beerRepository.save(beerMapper.beerDtoToBeer(beerDTO)).map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> updateBeer(Integer beerId, BeerDTO beerDTO) {
        return beerRepository.findById(beerId)
                .map(foundBeer -> {
                    foundBeer.setBeerName(beerDTO.getBeerName());
                    foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    foundBeer.setUpc(beerDTO.getUpc());
                    foundBeer.setPrice(beerDTO.getPrice());
                    foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());
                    return foundBeer;
                }).flatMap(beerRepository::save).map(beerMapper::beerToBeerDto);
    }

    @Override
    public Mono<BeerDTO> patchBeer(Integer beerId, BeerDTO beerDTO) {
        return beerRepository.findById(beerId)
                .map(foundBeer -> {
                    if(StringUtils.isNotBlank(beerDTO.getBeerName())){
                        foundBeer.setBeerName(beerDTO.getBeerName());
                    }
                    if(StringUtils.isNotBlank(beerDTO.getBeerStyle())){
                        foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    }
                    if(StringUtils.isNotBlank(beerDTO.getUpc())){
                        foundBeer.setUpc(beerDTO.getUpc());
                    }
                    if(beerDTO.getPrice() != null){
                        foundBeer.setPrice(beerDTO.getPrice());
                    }
                    if(beerDTO.getQuantityOnHand() != null){
                        foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());
                    }

                    return foundBeer;
                }).flatMap(beerRepository::save).map(beerMapper::beerToBeerDto);
    }
}
