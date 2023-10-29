package com.movielib.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    @Column(name = "movie_id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "category")
    @JoinTable(
            name = "categories",
            joinColumns = @JoinColumn(name = "name")
    )
    private String category;
    @Column(name = "summary")
    private String summary;
    @Column(name = "release_date")
    private int releaseDate;
    @Column(name = "rating")
    private double rating;
    @Column(name = "director")
    private String director;
    @ManyToMany
    @JoinTable(
            name = "movies_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;
    @ManyToMany
    @JoinTable(
            name = "movies_reviews",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private List<Review> reviews;

    public Movie(long id, String title, String category, String summary, int releaseDate, String director, List<Actor> actors) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.rating = 0.0;
        this.director = director;
        this.actors = actors;
    }

    public Movie() {
    }

    public Movie(String title, String category, String summary, int releaseDate, String director, List<Actor> actors) {
        this.title = title;
        this.category = category;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.rating = 0.0;
        this.director = director;
        this.actors = actors;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void addActor(Actor actor){
        actors.add(actor);
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
