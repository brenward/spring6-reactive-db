package com.bwardweb.spring6_reactive_db.repositories;

import com.bwardweb.spring6_reactive_db.config.DatabaseConfig;
import com.bwardweb.spring6_reactive_db.domain.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer(){
        beerRepository.save(getTestBeer()).subscribe(beer -> {
            System.out.println(beer.toString());
        });
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