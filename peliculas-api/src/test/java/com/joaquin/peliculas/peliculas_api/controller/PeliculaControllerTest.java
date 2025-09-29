package com.joaquin.peliculas.peliculas_api.controller;

import com.joaquin.peliculas.peliculas_api.model.Pelicula;
import com.joaquin.peliculas.peliculas_api.repositorio.PeliculaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaRepository peliculaRepository;

    @Test
    public void obtenerAllPeliculas_retornaListaDePeliculas() throws Exception {
        Pelicula p2 = new Pelicula(2L, "El padrino", 1972, "Francis Ford Coppola", "Crimen", "El patriarca de una dinastía del crimen organizado transfiere el control de su imperio.");
        Pelicula p3 = new Pelicula(3L, "Tiempos violentos", 1994, "Quentin Tarantino", "Crimen", "Las vidas de dos sicarios, un boxeador, la esposa de un gánster y dos bandidos se entrelazan.");
        when(peliculaRepository.findAll()).thenReturn(Arrays.asList(p2, p3));

        mockMvc.perform(get("/api/peliculas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.peliculaList", hasSize(2)))
                .andExpect(jsonPath("$._embedded.peliculaList[0].titulo", is("El padrino")))
                .andExpect(jsonPath("$._embedded.peliculaList[1].titulo", is("Tiempos violentos")));
    }

    @Test
    public void obtenerPeliculaById_retornaPelicula() throws Exception {
        Pelicula pelicula = new Pelicula(2L, "El padrino", 1972, "Francis Ford Coppola", "Crimen", "El patriarca de una dinastía del crimen organizado transfiere el control de su imperio.");
        when(peliculaRepository.findById(2L)).thenReturn(Optional.of(pelicula));

        mockMvc.perform(get("/api/peliculas/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo", is("El padrino")))
                .andExpect(jsonPath("$.director", is("Francis Ford Coppola")));
    }
}