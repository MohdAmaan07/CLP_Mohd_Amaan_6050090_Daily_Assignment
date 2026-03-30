package com.example.movieapp.Controller;

import com.example.movieapp.DTO.MovieSaveDTO;
import com.example.movieapp.Service.MovieServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MovieController {

    @Autowired
    private MovieServiceImpl movieService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/movies")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.findAllMovies());
        return "movies";
    }

    @GetMapping("/movies/new")
    public String showMovieForm(Model model) {
        model.addAttribute("movie", new MovieSaveDTO());
        return "movie-form";
    }

    @GetMapping("/movies/{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("movie", movieService.findById(id));
        return "movie-edit";
    }

    @PostMapping("/movies/{id}/update")
    public String updateMovie(@PathVariable Integer id,
                              @Valid @ModelAttribute("movie") MovieSaveDTO dto,
                              BindingResult bindingResult,
                              RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            flash.addFlashAttribute("error", "Please fix the errors and try again.");
            return "movie-edit";
        }
        movieService.updateMovie(id, dto);
        flash.addFlashAttribute("message", "Movie updated successfully!");
        return "redirect:/movies";
    }

    @GetMapping("/movies/{id}/delete")
    public String deleteMovie(@PathVariable Integer id, RedirectAttributes flash) {
        movieService.deleteMovie(id);
        flash.addFlashAttribute("message", "Movie deleted successfully!");
        return "redirect:/movies";
    }

    @PostMapping("/movies/save")
    public String saveMovie(@Valid @ModelAttribute("movie") MovieSaveDTO dto,
                            BindingResult result,
                            RedirectAttributes flash) {
        if (result.hasErrors()) {
            flash.addFlashAttribute("error", "Please fix the errors and try again.");
            return "movie-form";
        }
        movieService.saveMovie(dto);
        flash.addFlashAttribute("message", "Movie saved successfully!");
        return "redirect:/";
    }

    @GetMapping("/movies/search")
    public String searchMovieByGenre(@RequestParam(required = false) String genre,
                                     Model model,
                                     RedirectAttributes flash) {
        if (genre != null && !genre.isEmpty()) {
            model.addAttribute("movies", movieService.findMovieByGenre(genre));
        } else {
            flash.addFlashAttribute("error", "Please select a genre to search.");
        }
        return "search-movies";
    }
}