package com.example.semana8.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer status;

    @ManyToMany(mappedBy = "categories") // mappedBy: realiza el mapeo de la relacion
    @JsonIgnore // para que no se genere un loop infinito
    private Set<Book> books = new HashSet<>();

}
