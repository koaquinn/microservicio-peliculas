package com.joaquin.peliculas.peliculas_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.hateoas.RepresentationModel;


@Entity
@Table(name = "peliculas")
public class Pelicula extends RepresentationModel<Pelicula> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private int anio;
    private String director;
    private String genero;
    private String sinopsis;

    public Pelicula() {
    }
    public Pelicula(Long id, String titulo, int anio, String director, String genero, String sinopsis) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.director = director;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}