package com.joaquin.peliculas.peliculas_api.config;

import com.joaquin.peliculas.peliculas_api.model.Pelicula;
import com.joaquin.peliculas.peliculas_api.repositorio.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (peliculaRepository.count() == 0) {
            Pelicula p1 = new Pelicula(null, "Sueños de libertad", 1994, "Frank Darabont", "Drama", "Dos hombres encarcelados establecen un vínculo a lo largo de varios años.");
            Pelicula p2 = new Pelicula(null, "El padrino", 1972, "Francis Ford Coppola", "Crimen", "El patriarca de una dinastía del crimen organizado transfiere el control de su imperio.");
            Pelicula p3 = new Pelicula(null, "Tiempos violentos", 1994, "Quentin Tarantino", "Crimen", "Las vidas de dos sicarios, un boxeador, la esposa de un gánster y dos bandidos se entrelazan.");
            Pelicula p4 = new Pelicula(null, "Forrest Gump", 1994, "Robert Zemeckis", "Drama", "La historia de Estados Unidos a través de los ojos de un hombre con un CI de 75.");
            Pelicula p5 = new Pelicula(null, "MATRIX", 1999, "Wachowski Brothers", "Ciencia ficción", "Un hacker aprende la verdad sobre su realidad y su papel en la guerra contra sus controladores.");

            peliculaRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
            System.out.println(">>> 5 películas de ejemplo cargadas en la base de datos.");
        }
    }
}