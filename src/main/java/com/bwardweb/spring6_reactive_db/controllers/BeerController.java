package com.bwardweb.spring6_reactive_db.controllers;


import com.bwardweb.spring6_reactive_db.model.BeerDTO;
import com.bwardweb.spring6_reactive_db.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BeerController {

    public static final String BEER_PATH = "/api/v2/beer";
    public  static final String BEER_PATH_ID = "/api/v2/beer/{beerId}";

    private final BeerService beerService;

    @GetMapping(BEER_PATH)
    Flux<BeerDTO> listBeers() {
        return beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    Mono<BeerDTO> getBeerById(@PathVariable Integer beerId) {
        return beerService.getBeerById(beerId);
    }

    @PostMapping(BEER_PATH)
    Mono<ResponseEntity<Void>> saveNewBeer(@RequestBody BeerDTO beerDTO) {
        return beerService.saveNewBeer(beerDTO).map(
           savedDto -> ResponseEntity.created(
                   UriComponentsBuilder.fromHttpUrl("http://localhost:8080/" + BEER_PATH + "/" + savedDto.getId()).build().toUri())
                   .build()
        );
    }

    @PutMapping(BEER_PATH_ID)
    ResponseEntity<Void> updateBeer(@PathVariable Integer beerId, @RequestBody BeerDTO beerDTO) {
        beerService.updateBeer(beerId, beerDTO).subscribe();

        return ResponseEntity.ok().build();
    }

    @PatchMapping(BEER_PATH_ID)
    ResponseEntity<Void> patchBeer(@PathVariable Integer beerId, @RequestBody BeerDTO beerDTO) {
        beerService.patchBeer(beerId, beerDTO).subscribe();

        return ResponseEntity.ok().build();
    }
}
