package com.verizon.in.routes;

import com.verizon.in.domain.Review;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
public class ReviewsIntgTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ReactiveMongoRepository reactiveMongoRepository;

    String REVIEWS_URL="/v1/reviews";

    @BeforeEach
    void setUp() {

        var reviewList= List.of(
                new Review(null,1L,"Awesome Movie1",8.0),
                new Review(null,1L,"Awesome Movie1",8.0),
                new Review(null,1L,"Excellent Movie",9.0),
                new Review(null,1L,"Good",7.0));
        reactiveMongoRepository.saveAll(reviewList)
                .blockLast();
    }

    @AfterEach
    void tearDown() {
        reactiveMongoRepository.deleteAll().block();
    }

    @Test
    void addReview(){

        var review= new Review(null,1L,"Good",7.0);

        webTestClient
                .post()
                .uri(REVIEWS_URL)
                .bodyValue(review)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Review.class)
                .consumeWith(reviewEntityExchangeResult ->{
                 var savedReview= reviewEntityExchangeResult.getResponseBody();
                 assert savedReview !=null;
                 assert savedReview.getReviewId() !=null;
                });
    }

    @Test
    void getAllReview(){
        webTestClient
                .get()
                .uri(REVIEWS_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Review.class)
                .hasSize(4);
    }
}
