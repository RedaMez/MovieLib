package com.movielib.backend.service;

import com.movielib.backend.repository.MovieRepository;
import com.movielib.backend.services.impl.MovieServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    public void MovieService_ReturnMovie(){
        
    }
}
