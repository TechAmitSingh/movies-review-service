package com.verizon.in.routes;

import com.verizon.in.domain.Review;
import com.verizon.in.handler.ReviewHandler;
import com.verizon.in.repository.ReviewReactiveRepository;
import com.verizon.in.router.ReviewRouter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.isA;
import static reactor.core.publisher.Mono.when;

@WebFluxTest
@ContextConfiguration(classes = {ReviewRouter.class, ReviewHandler.class})
@AutoConfigureWebTestClient
public class ReviewsUnitTest {

    @MockBean
    private ReviewReactiveRepository reviewReactiveRepository;

    @Autowired
    private WebTestClient webTestClient;

    String REVIEWS_URL="/v1/reviews";

    @Test
    void addReview(){
        var review= new Review(null,1L,"Awesome Movie",9.0);

        Mockito.when(reviewReactiveRepository.save(isA(Review.class)))
                .thenReturn(Mono.just(new Review("abc", 1L, "Awesome Movie", 9.0)));
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

}
