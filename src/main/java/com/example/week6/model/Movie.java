package com.example.week6.model;

public class Movie {
    private long id;
    private String title;
    private String producer;
    private String year;

    public Movie() {
    }

    public Movie(long id, String title, String producer, String year) {
        this.id = id;
        this.title = title;
        this.producer = producer;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
