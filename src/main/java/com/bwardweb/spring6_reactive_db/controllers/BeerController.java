package com.bwardweb.spring6_reactive_db.controllers;


import com.bwardweb.spring6_reactive_db.model.BeerDTO;
import com.bwardweb.spring6_reactive_db.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class BeerController {

    public static final String BEER_PATH = "/api/v2/beer";

    private final BeerService beerService;

    @GetMapping(BEER_PATH)
    Flux<BeerDTO> listBeers() {
        return beerService.listBeers();
    }
}
