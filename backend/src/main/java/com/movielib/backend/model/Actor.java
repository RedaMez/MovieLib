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
@Table(name = "actors")
public class Actor {

    @Id
    @SequenceGenerator(
            name = "actor_sequence",
            sequenceName = "actor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "actor_sequence"
    )
    @Column(name = "actor_id")
    private long id;
    @Column(name = "name")
    private String name;

    public Actor(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
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
}
