package com.example.semana8.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Author {

    // campos de la tabla Entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 1")
    private Integer status;

    // campo que es parte de la relacion
    @OneToMany(mappedBy = "author") // mappedBy: realiza el mapeo de la relacion
    @JsonIgnore // para que no se genere un loop infinito
    private List<Book> books = new ArrayList<>();



}
