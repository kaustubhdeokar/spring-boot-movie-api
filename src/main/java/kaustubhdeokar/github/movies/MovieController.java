package kaustubhdeokar.github.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable ObjectId id) {
        Movie movieById = movieService.getMovieById(id);
        return createResponseForMovie(movieById);
    }

    @GetMapping("/imdb/{id}")
    public ResponseEntity<Movie> getMovieByImdbId(@PathVariable String id){
        Movie movieByImdbId = movieService.getMovieByImdbId(id);
        return createResponseForMovie(movieByImdbId);
    }

    private static ResponseEntity<Movie> createResponseForMovie(Movie movieById) {
        if (movieById == null) {
            return new ResponseEntity<>(movieById, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(movieById, HttpStatus.OK);
        }
    }

}
