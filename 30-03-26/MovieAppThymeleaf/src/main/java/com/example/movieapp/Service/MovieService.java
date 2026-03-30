package com.example.movieapp.Service;

import com.example.movieapp.DTO.MovieSaveDTO;
import com.example.movieapp.Entities.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Movie saveMovie(MovieSaveDTO movie);
    List<Movie> findMovieByGenre(String genre);
    List<Movie> findAllMovies();
    Optional<Movie> findById(Integer id);
    void deleteMovie(Integer id);
    Movie updateMovie(Integer id, MovieSaveDTO updatedMovie);
}
