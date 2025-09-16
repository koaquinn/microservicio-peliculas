package com.joaquin.peliculas.peliculas_api.repositorio;

import com.joaquin.peliculas.peliculas_api.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    // La magia de Spring Data JPA hace que no necesites escribir nada más aquí.
}