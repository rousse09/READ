package com.read.biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo; // Código único del libro

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private int numPaginas;

    @Column(nullable = false)
    private int anioPublicacion;

    private String descripcion;

    @Column(nullable = false)
    private String estado; // Ejemplo: 'disponible', 'reservado', 'entregado'

    private String imagen; // URL o ruta de la imagen del libro

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String idioma;

    @Column(nullable = false)
    private String editorial;

    @Column(nullable = false)
    private int stock;
}
