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
@Table(name = "categories")
public class Category {

    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )
    @Column(name = "category_id")
    private long id;
    @Column(name = "name")
    private String name;

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Category c = (Category) o;
        return Objects.equals(this.name, c.name);
    }
}
