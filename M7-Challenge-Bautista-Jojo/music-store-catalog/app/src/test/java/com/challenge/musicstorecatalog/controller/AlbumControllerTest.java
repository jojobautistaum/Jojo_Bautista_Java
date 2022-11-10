package com.challenge.musicstorecatalog.controller;

import com.challenge.musicstorecatalog.model.Album;
import com.challenge.musicstorecatalog.repository.AlbumRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.*;
import java.time.temporal.*;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumController.class)
public class AlbumControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumRepository albumRepository;

    private Album album1;
    private Album album2;
    private List<Album> allAlbums;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        // Add dependency in the pom.xml file (jsr310) for this to work
        // Register JavaTimeModule for LocalDate to work with ObjectMapper
        // Reference: https://howtodoinjava.com/jackson/java-8-date-time-type-not-supported-by-default/
        mapper.registerModule(new JavaTimeModule());
        album1 = new Album(1, "Blue Night", 1, LocalDate.of(2020, 11, 1), 1, new BigDecimal("50.00"));
        album2 = new Album(2, "Still", 1, LocalDate.of(2018, 3,21), 1, new BigDecimal("44.99") );
        allAlbums = new ArrayList<>(Arrays.asList(album1, album2));
    }

    @Test
    public void shouldReturnTheAlbumAndCode200WhenRetrievingAnExistingAlbum() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(album1);
        doReturn(Optional.ofNullable(album1)).when(albumRepository).findById(1);

        // Act
        mockMvc.perform(
                        get("/album/1"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return an existing album by ID: 1
                );
    }

    @Test
    public void shouldReturnCode404WhenRetrievingNonExistingAlbum() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(album1);
        doReturn(Optional.empty()).when(albumRepository).findById(501);

        // Act
        mockMvc.perform(
                        get("/album/501"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturnAllAlbumsAndCode200WhenRetrievingAllAlbums() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(allAlbums);
        doReturn(allAlbums).when(albumRepository).findAll();

        // Act
        mockMvc.perform(
                        get("/album"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return All Albums
                );
    }

    @Test
    public void shouldCreateAlbumAndCode201() throws Exception {
        // Arrange
        Album newAlbum = new Album();
        newAlbum.setTitle("Eternity");
        newAlbum.setArtistId(1);
        newAlbum.setReleaseDate(LocalDate.of(2008,10,27));
        newAlbum.setLabelId(1);
        newAlbum.setListPrice(new BigDecimal("39.99"));

        Album album3 = new Album(3, "Eternity", 1, LocalDate.of(2008, 10, 27), 1, new BigDecimal("39.99"));

        String inputJson = mapper.writeValueAsString(newAlbum);
        String outputJson = mapper.writeValueAsString(album3);
        doReturn(album3).when(albumRepository).save(newAlbum);

        // Act
        mockMvc.perform(
                        post("/album")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isCreated()) // Should return 201 CREATED
                .andExpect(content().json(outputJson) // Should return the album with ID: 3
                );
    }

    @Test
    public void shouldUpdateExistingAlbumAndReturnCode204() throws Exception {
        // Arrange
        Album updatedAlbum2 = new Album();
        updatedAlbum2.setId(2);
        updatedAlbum2.setTitle("Scandinavia");
        updatedAlbum2.setArtistId(1);
        updatedAlbum2.setReleaseDate(LocalDate.of(2012,6,11));
        updatedAlbum2.setLabelId(1);
        updatedAlbum2.setListPrice(new BigDecimal("44.99"));

        String inputJson = mapper.writeValueAsString(updatedAlbum2);
        doReturn(Optional.of(updatedAlbum2)).when(albumRepository).findById(2);

        // Act
        mockMvc.perform(
                        put("/album")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturnCode404WhenUpdatingNonExistentAlbum() throws Exception {
        // Arrange
        Album updatedAlbum = new Album();
        updatedAlbum.setId(935);
        updatedAlbum.setTitle("Scandinavia");
        updatedAlbum.setArtistId(1);
        updatedAlbum.setReleaseDate(LocalDate.of(2012,6,11));
        updatedAlbum.setLabelId(1);
        updatedAlbum.setListPrice(new BigDecimal("44.99"));

        String inputJson = mapper.writeValueAsString(updatedAlbum);
        doReturn(Optional.empty()).when(albumRepository).findById(935);

        // Act
        mockMvc.perform(
                        put("/album")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturnCode204WhenDeletingExistingAlbum() throws Exception {
        // Arrange
        doReturn(Optional.ofNullable(album2)).when(albumRepository).findById(2);

        // Act
        mockMvc.perform(
                        delete("/album/2"))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturnCode404WhenDeletingNonExistingAlbum() throws Exception {
        // Arrange
        doReturn(Optional.empty()).when(albumRepository).findById(995);

        // Act
        mockMvc.perform(
                        delete("/album/995"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }
}