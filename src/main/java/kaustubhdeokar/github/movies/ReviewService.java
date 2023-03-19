package kaustubhdeokar.github.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String body, String imdbId) {
        Optional<Movie> movieByImdbId = movieRepository.findMovieByImdbId(imdbId);
        Review review = null;
        if (movieByImdbId.isPresent()) {
            Movie movie = movieByImdbId.get();

            review = reviewRepository.insert(new Review(body));


            mongoTemplate.update(Movie.class)
                    .matching(Criteria.where("imdbId").is(imdbId))
                    .apply(new Update().push("reviewIds").value(review))
                    .first();
        }
        return review;
    }


}
