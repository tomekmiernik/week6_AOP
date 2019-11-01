
package com.example.week6.controller;

import com.example.week6.model.Movie;
import com.example.week6.service.MovieService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

@Component
@Route("")
public class MovieGui extends VerticalLayout {
    private MovieService movieService;
    private Grid<Movie> grid = new Grid<>(Movie.class);
    private MovieForm form;

    public MovieGui(MovieService movieService) {
        this.movieService = movieService;
        form = new MovieForm(movieService, this);

        grid.setColumns("id", "title", "producer", "year");
        add(grid);
        setSizeFull();

        updateList();

        Button buttonAddNewMovieItem = new Button("Dodaj nowy film");
        buttonAddNewMovieItem.addClickListener(event -> {
            grid.asSingleSelect().clear();
            form.setMovie(new Movie());
        });
        HorizontalLayout buttonToolbar = new HorizontalLayout(buttonAddNewMovieItem);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();
        add(buttonToolbar, mainContent);

        updateList();
        form.setMovie(null);
        grid.asSingleSelect().addValueChangeListener(event ->
                form.setMovie(grid.asSingleSelect().getValue()));
    }


    public void updateList() {
        grid.setItems(movieService.getAllMovies());
    }
}
