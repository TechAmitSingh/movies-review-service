package com.verizon.in.repository;

import com.verizon.in.domain.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReviewReactiveRepository extends ReactiveMongoRepository<Review,String> {

    Flux<Review> findReviewsByMovieInfoId(Long moviewInfoId);
}
