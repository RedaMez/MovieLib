package com.movielib.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private long id;
    private String name;
    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies = new ArrayList<>();

    public Actor(String name) {
        this.name = name;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Actor a = (Actor) o;
        return Objects.equals(this.name, a.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
