package com.joaquin.peliculas.peliculas_api.controller;

import com.joaquin.peliculas.peliculas_api.model.Pelicula;
import com.joaquin.peliculas.peliculas_api.repositorio.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @GetMapping
    public CollectionModel<EntityModel<Pelicula>> getAllPeliculas() {
        List<EntityModel<Pelicula>> peliculas = peliculaRepository.findAll().stream()
                .map(pelicula -> EntityModel.of(pelicula,
                        linkTo(methodOn(PeliculaController.class).getPeliculaById(pelicula.getId())).withSelfRel(),
                        linkTo(methodOn(PeliculaController.class).getAllPeliculas()).withRel("peliculas")))
                .collect(Collectors.toList());

        return CollectionModel.of(peliculas, linkTo(methodOn(PeliculaController.class).getAllPeliculas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Pelicula> getPeliculaById(@PathVariable Long id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la película con el id: " + id));

        return EntityModel.of(pelicula,
                linkTo(methodOn(PeliculaController.class).getPeliculaById(id)).withSelfRel(),
                linkTo(methodOn(PeliculaController.class).getAllPeliculas()).withRel("peliculas"));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Pelicula>> createPelicula(@RequestBody Pelicula pelicula) {
        Pelicula nuevaPelicula = peliculaRepository.save(pelicula);
        EntityModel<Pelicula> peliculaModel = EntityModel.of(nuevaPelicula,
                linkTo(methodOn(PeliculaController.class).getPeliculaById(nuevaPelicula.getId())).withSelfRel(),
                linkTo(methodOn(PeliculaController.class).getAllPeliculas()).withRel("peliculas"));

        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Pelicula>> updatePelicula(@PathVariable Long id, @RequestBody Pelicula peliculaDetails) {
        return peliculaRepository.findById(id)
                .map(pelicula -> {
                    pelicula.setTitulo(peliculaDetails.getTitulo());
                    pelicula.setAnio(peliculaDetails.getAnio());
                    pelicula.setDirector(peliculaDetails.getDirector());
                    pelicula.setGenero(peliculaDetails.getGenero());
                    pelicula.setSinopsis(peliculaDetails.getSinopsis());
                    Pelicula updatedPelicula = peliculaRepository.save(pelicula);
                    EntityModel<Pelicula> peliculaModel = EntityModel.of(updatedPelicula,
                            linkTo(methodOn(PeliculaController.class).getPeliculaById(updatedPelicula.getId())).withSelfRel(),
                            linkTo(methodOn(PeliculaController.class).getAllPeliculas()).withRel("peliculas"));
                    return ResponseEntity.ok().body(peliculaModel);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePelicula(@PathVariable Long id) {
        return peliculaRepository.findById(id)
                .map(pelicula -> {
                    peliculaRepository.delete(pelicula);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}