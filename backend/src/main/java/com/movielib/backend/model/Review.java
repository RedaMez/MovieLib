package com.movielib.backend.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @SequenceGenerator(
            name = "reviews_sequence",
            sequenceName = "reviews_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reviews_sequence"
    )
    @Column(name = "review_id")
    private long id;

    @Column(name = "user_id")
    @JoinTable(
            name = "users",
            joinColumns = @JoinColumn(name = "user_id")
    )
    private long user_id;
    @Column(name = "movie_id")
    @JoinTable(
            name = "movies",
            joinColumns = @JoinColumn(name = "movie_id")
    )
    private long movie_id;
    @Column(name = "rating")
    private double rating;
    @Column(name = "comment")
    private String comment;

    public Review(long id, long user_id, long movieId, double rating, String comment) {
        this.id = id;
        this.user_id = user_id;
        this.movie_id = movieId;
        this.rating = rating;
        this.comment = comment;
    }

    public Review() {
    }

    public Review(long user_id, long movie_id, double rating, String comment) {
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.rating = rating;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public long getUser_id() {
        return user_id;
    }

    public long getMovie_id() {
        return movie_id;
    }

    public double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Review r = (Review) o;
        return  Objects.equals(this.user_id, r.user_id) &&
                Objects.equals(this.movie_id, r.movie_id);
    }
}
