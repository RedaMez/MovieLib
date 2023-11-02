package com.movielib.backend.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
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
    private long userId;
    @Column(name = "movie_id")
    @JoinTable(
            name = "movies",
            joinColumns = @JoinColumn(name = "movie_id")
    )
    private long movieId;
    @Column(name = "rating")
    private double rating;
    @Column(name = "comment")
    private String comment;

    public Review(long id, long userId, long movieId, double rating, String comment) {
        this.id = id;
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.comment = comment;
    }

    public Review() {
    }

    public Review(long userId, long movieId, double rating, String comment) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Review r = (Review) o;
        return  Objects.equals(this.userId, r.userId) &&
                Objects.equals(this.movieId, r.movieId);
    }
}
