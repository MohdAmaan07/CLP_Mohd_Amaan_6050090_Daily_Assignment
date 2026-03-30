package com.example.movieapp.Service;

import com.example.movieapp.DTO.MovieSaveDTO;
import com.example.movieapp.Entities.Movie;
import com.example.movieapp.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepo repo;

    public Movie saveMovie(MovieSaveDTO dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setGenre(dto.getGenre());
        movie.setRating(dto.getRating());

        return repo.save(movie);
    }

    public List<Movie> findMovieByGenre(String genre) {
        return repo.findByGenre(genre);
    }

    public List<Movie> findAllMovies() {
        return repo.findAll();
    }

    public Optional<Movie> findById(Integer id) {
        return repo.findById(id);
    }

    public Movie updateMovie(Integer id, MovieSaveDTO dto) {
        Movie existingMovie = repo.findById(id).orElse(null);
        if (existingMovie != null) {
            existingMovie.setTitle(dto.getTitle());
            existingMovie.setGenre(dto.getGenre());
            existingMovie.setRating(dto.getRating());
            return repo.save(existingMovie);
        }
        return null;
    }

    public void deleteMovie(Integer id) {
        repo.deleteById(id);
    }
}
