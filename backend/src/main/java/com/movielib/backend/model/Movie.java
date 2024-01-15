package com.movielib.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    private String summary;
    private int releaseDate;
    @Builder.Default
    private double rating = 1.;
    private String director;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors = new ArrayList<>();
    @OneToMany(
            mappedBy = "movie",
            cascade = CascadeType.REMOVE
    )
    private List<Review> reviews = new ArrayList<>();

    public void addReview(Review review){
        reviews.add(review);
        updateRating();
    }

    private void updateRating() {
        double avg_rating = 1.;
        for(Review r:reviews){
            avg_rating *= r.getRating();
        }
        double average = avg_rating / reviews.size();
        BigDecimal roundedAverage = new BigDecimal(average).setScale(1, RoundingMode.HALF_UP);
        this.rating = roundedAverage.doubleValue();
    }

    public void removeReview(Review review){
        reviews.remove(review);
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Movie m = (Movie) o;
        return Objects.equals(this.title, m.title) && Objects.equals(this.director, m.director);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", category=" + category +
                ", summary='" + summary + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                ", director='" + director + '\'' +
                ", actors=" + actors +
                ", reviews=" + reviews +
                '}';
    }
}
