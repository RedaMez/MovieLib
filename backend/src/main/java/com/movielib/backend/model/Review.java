package com.movielib.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private double rating;
    private String comment;

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Review r = (Review) o;
        return  Objects.equals(this.userEntity, r.userEntity) &&
                Objects.equals(this.movie, r.movie);
    }

    @Override
    public String toString() {
        return  userEntity +
                " " + movie.getId() +
                " " + rating +
                " " + comment;
    }
}
