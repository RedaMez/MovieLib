package com.movielib.backend.model;

import jakarta.persistence.*;

import java.util.Set;

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
    private Set<Actor> actors;
    @ManyToMany
    @JoinTable(
            name = "movies_reviews",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private Set<Review> reviews;

    public Movie(long id, String title, String category, String summary, int releaseDate, double rating, String director) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.director = director;
    }

    public Movie() {
    }

    public Movie(String title, String category, String summary, int releaseDate, double rating, String director) {
        this.title = title;
        this.category = category;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.director = director;
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

    public Set<Actor> getActors() {
        return actors;
    }

    public Set<Review> getReviews() {
        return reviews;
    }
}
