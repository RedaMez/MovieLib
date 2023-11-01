package com.movielib.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Getter
@AllArgsConstructor
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
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "category")
    private Category category;
    @Column(name = "summary")
    private String summary;
    @Column(name = "release_date")
    private int releaseDate;
    @Column(name = "rating")
    private double rating;
    @Column(name = "director")
    private String director;
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "movies_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "movies_reviews",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "review_id")
    )
    private List<Review> reviews;

    public Movie(long id, String title, Category category, String summary, int releaseDate, String director, List<Actor> actors) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.rating = .0;
        this.director = director;
        this.actors = actors;
    }

    public Movie() {
    }

    public Movie(String title, Category category, String summary, int releaseDate, String director, List<Actor> actors) {
        this.title = title;
        this.category = category;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.rating = .0;
        this.director = director;
        this.actors = actors;
    }

    /*public double getRating() {
        double avg = 1.;
        if(!reviews.isEmpty()){
            for(Review r: reviews){
                avg *= r.getRating();
            }
        }
        return rating = avg;
    }*/

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public void removeReview(Review review){
        reviews.remove(review);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
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
}
