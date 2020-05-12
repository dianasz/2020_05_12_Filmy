package com.example.demo.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MovieController {
    private MovieRepository movieRepository;
    private EntityManager entityManager;

    @PersistenceUnit
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movie", new Movie());
        return "home";
    }

    @PostMapping("/")
    public String saveMovie(@RequestParam String title, @RequestParam CharSequence premiereDate, @RequestParam String description, @RequestParam Category category ) {
        Movie movie;
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(description) || StringUtils.isEmpty((String) premiereDate)|| StringUtils.isEmpty(String.valueOf(category))){
            return "err";
        } else {
            LocalDate localDate = LocalDate.parse(premiereDate);
            movie = new Movie(title, localDate, description, category);
            movieRepository.save(movie);
            return "success";
        }
    }

    @GetMapping("/list")
    public String getList(Model model) {
        List<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "list";
    }

    @GetMapping("/movie")
    public String getMovieDetails(@RequestParam String title, Model model) {
        Movie movie = movieRepository.findByTitle(title);
        model.addAttribute("movie", movie);
        return "movie";
    }

    @GetMapping("/category")
    public String getCategory(@RequestParam("category") Category category, Model model) {
        List<Movie> categoryList = movieRepository.findByCategory(category);
        model.addAttribute("category", categoryList);
        return "category";
    }

}