package com.lauracercas.moviecards.unittest.service;

import com.lauracercas.moviecards.model.Movie;
import com.lauracercas.moviecards.repositories.MovieJPA;
import com.lauracercas.moviecards.service.movie.MovieService;
import com.lauracercas.moviecards.service.movie.MovieServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Autor: Laura Cercas Ramos
 * Proyecto: TFM Integración Continua con GitHub Actions
 * Fecha: 04/06/2024
 */
class MovieServiceImplTest {
//    @Mock
//    private MovieJPA movieJPA;
    @Mock
    private RestTemplate template;
    //private MovieServiceImpl sut;
    @InjectMocks
    private MovieService sut = new MovieServiceImpl();
    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = openMocks(this);
//        sut = new MovieServiceImpl(movieJPA);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldGetAllMovies() {
//        List<Movie> movies = new ArrayList<>();
//        movies.add(new Movie());
//        movies.add(new Movie());
        Movie movies[]=new Movie[2];
        movies[0]=new Movie();
        movies[1]=new Movie();

        //when(movieJPA.findAll()).thenReturn(movies);
        when(template.getForObject(anyString(),any())).thenReturn(movies);

        List<Movie> result = sut.getAllMovies();

        assertEquals(2, result.size());
    }

    @Test
    public void shouldGetMovieById() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setTitle("Sample Movie");

        //when(movieJPA.getById(anyInt())).thenReturn(movie);
        when(template.getForObject(anyString(),any())).thenReturn(movie);

        Movie result = sut.getMovieById(1);

        assertEquals(1, result.getId());
        assertEquals("Sample Movie", result.getTitle());
    }

//    @Test
//    public void shouldSaveMovie() {
//        Movie movie = new Movie();
//        movie.setTitle("New Movie");
//
//        when(movieJPA.save(movie)).thenReturn(movie);
//
//        Movie result = sut.save(movie);
//
//        assertEquals("New Movie", result.getTitle());
//    }


}