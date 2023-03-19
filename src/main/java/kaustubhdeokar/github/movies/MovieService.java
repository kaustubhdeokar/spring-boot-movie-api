package kaustubhdeokar.github.movies;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    @Nullable
//    public Movie getMovieById(ObjectId id) {
//        Optional<Movie> byId = movieRepository.findById(id);
//        return byId.orElse(null);
//    }

    public Movie getMovieByImdbId(String id) {
        Optional<Movie> byId = movieRepository.findMovieByImdbId(id);
        return byId.orElse(null);
    }

//    public Movie getMovieByImdbId(String id) {
//        Optional<Movie> movieByImdbId = movieRepository.findMovieByImdbId(id);
//        return movieByImdbId.orElse(null);
//    }

}
