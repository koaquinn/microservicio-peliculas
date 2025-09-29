package com.joaquin.peliculas.peliculas_api.repositorio;

import com.joaquin.peliculas.peliculas_api.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
}