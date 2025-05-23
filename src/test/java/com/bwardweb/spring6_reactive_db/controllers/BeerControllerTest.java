package com.bwardweb.spring6_reactive_db.controllers;

import com.bwardweb.spring6_reactive_db.domain.Beer;
import com.bwardweb.spring6_reactive_db.model.BeerDTO;
import com.bwardweb.spring6_reactive_db.repositories.BeerRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOAuth2Login;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
class BeerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(2)
    void testListBeers(){
        webTestClient
                .mutateWith(mockOAuth2Login())
                .get()
                .uri(BeerController.BEER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    @Order(1)
    void testGetBeerById(){
        webTestClient
                .mutateWith(mockOAuth2Login())
                .get().uri(BeerController.BEER_PATH_ID, 1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody(BeerDTO.class);
    }

    @Test
    void testGetBeerByIdNotFound(){
        webTestClient
                .mutateWith(mockOAuth2Login())
                .get().uri(BeerController.BEER_PATH_ID, 999)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateBeer(){
        webTestClient
                .mutateWith(mockOAuth2Login())
                .post().uri(BeerController.BEER_PATH)
                .body(Mono.just(getTestBeer()), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080/api/v2/beer/4");
    }

    @Test
    void testCreateBeerBadData(){
        Beer beer = getTestBeer();
        beer.setBeerName("");

        webTestClient
                .mutateWith(mockOAuth2Login())
                .post().uri(BeerController.BEER_PATH)
                .body(Mono.just(beer), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Order(3)
    void testUpdateBeer(){
        webTestClient
                .mutateWith(mockOAuth2Login())
                .put().uri(BeerController.BEER_PATH_ID, 1)
                .body(Mono.just(getTestBeer()), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @Order(4)
    void testUpdateBeerBadRequest(){
        Beer beer = getTestBeer();
        beer.setBeerStyle("");

        webTestClient.mutateWith(mockOAuth2Login())
                .put()
                .uri(BeerController.BEER_PATH_ID, 1)
                .body(Mono.just(beer), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdateBeerNotFound(){
        webTestClient
                .mutateWith(mockOAuth2Login())
                .put()
                .uri(BeerController.BEER_PATH_ID, 999)
                .body(Mono.just(getTestBeer()), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Order(5)
    void testPatchBeer(){
        webTestClient
                .mutateWith(mockOAuth2Login())
                .patch()
                .uri(BeerController.BEER_PATH_ID, 1)
                .body(Mono.just(getTestBeer()), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testPatchBeerNotFound(){
        webTestClient
                .mutateWith(mockOAuth2Login())
                .patch().uri(BeerController.BEER_PATH_ID, 999)
                .body(Mono.just(getTestBeer()), BeerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Order(999)
    void testDeleteBeer(){
        webTestClient
                .mutateWith(mockOAuth2Login())
                .delete().uri(BeerController.BEER_PATH_ID, 1)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDeleteBeerNotFound(){
        webTestClient
                .mutateWith(mockOAuth2Login())
                .delete().uri(BeerController.BEER_PATH_ID, 999)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isNotFound();
    }

    Beer getTestBeer(){
        return Beer.builder()
                .beerName("Test Beer")
                .beerStyle("Ake")
                .quantityOnHand(10)
                .upc("123456789012")
                .price(new BigDecimal("12.99"))
                .build();
    }

}