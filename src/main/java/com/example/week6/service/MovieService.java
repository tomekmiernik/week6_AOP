package com.example.week6.service;

import com.example.week6.aspect.SendEmailAfterAddNewMovie;
import com.example.week6.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MovieService {
    private List<Movie> movieList;

    public MovieService() {
        movieList = new ArrayList<>();
        init();
    }

    private void init() {
        movieList.add(new Movie(1L, "Tytuł_1", "Producent_1", "2012"));
        movieList.add(new Movie(2L, "Tytuł_2", "Producent_2", "2013"));
        movieList.add(new Movie(3L, "Tytuł_3", "Producent_3", "2014"));
    }


    public Collection<Movie> getAllMovies() {
        return movieList;
    }

    @SendEmailAfterAddNewMovie
    public boolean save(Movie movie) {
        if (movie != null) {
            autoIncrementMovieId(movie);
            return movieList.add(movie);
        }
        return false;
    }

    private void autoIncrementMovieId(Movie movie) {
        long lastMovieId = movieList.get(movieList.size() - 1).getId();
        if (lastMovieId >= movieList.size()) {
            movie.setId(lastMovieId + 1);
        }
    }
}


