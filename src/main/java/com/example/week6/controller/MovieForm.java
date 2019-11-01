
package com.example.week6.controller;

import com.example.week6.model.Movie;
import com.example.week6.service.MovieService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieForm extends FormLayout {
    private final String SAVE_MOVIE = "Zapisz";
    private final String CANCEL = "Zamknij";
    private MovieService movieService;
    private MovieGui movieGui;


    private TextField title = new TextField("Tytuł");
    private TextField producer = new TextField("Producent");
    private TextField year = new TextField("Rok");
    private Button save = new Button(SAVE_MOVIE);
    private Button cancel = new Button(CANCEL);

    private Binder<Movie> binder = new Binder<>(Movie.class);

    @Autowired
    public MovieForm(MovieService movieService, MovieGui movieGui) {
        this.movieService = movieService;
        this.movieGui = movieGui;

        HorizontalLayout buttonsGrid = new HorizontalLayout(save, cancel);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
        add(title, producer, year, buttonsGrid);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> saveMovieItem());
        cancel.addClickListener(event -> closedRightForm());
    }


    private void closedRightForm() {
        setVisible(false);
    }


    public void setMovie(Movie movie) {
        binder.setBean(movie);
        if (movie == null || movie.getId() > 0) {
            setVisible(false);
        } else {
            setVisible(true);
            title.focus();
        }
    }


    private void saveMovieItem() {
        Movie movie = binder.getBean();
        if (movie == null) {
            movie = new Movie();
        }
        boolean saveMovie = movieService.save(movie);
        if (saveMovie) {
            Notification.show("Sprawdź skrzynkę pocztową", 5000, Notification.Position.TOP_CENTER);
        }
        movieGui.updateList();
        setMovie(null);
    }
}
