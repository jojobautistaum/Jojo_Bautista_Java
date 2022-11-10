package com.challenge.musicstorerecommendations.controller;

import com.challenge.musicstorerecommendations.model.AlbumRecommendation;
import com.challenge.musicstorerecommendations.repository.AlbumRecommendationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumRecommendationController.class)
public class AlbumRecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumRecommendationRepository albumRecommendationRepo;

    private AlbumRecommendation albumRec1;
    private AlbumRecommendation albumRec2;
    private List<AlbumRecommendation> allAlbumRec;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        albumRec1 = new AlbumRecommendation(1, 1, 1, true);
        albumRec2 = new AlbumRecommendation(2, 1, 2, false);
        allAlbumRec = new ArrayList<>(Arrays.asList(albumRec1, albumRec2));
    }

    @Test
    public void shouldReturnAlbumRecommendationAnd200WhenRetrievingAnExistingAlbumRecommendation() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(albumRec1);
        doReturn(Optional.ofNullable(albumRec1)).when(albumRecommendationRepo).findById(1);

        // Act
        mockMvc.perform(
                        get("/albumRecommendation/1"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return an existing album by ID: 1
                );
    }

    @Test
    public void shouldReturn404WhenRetrievingNonExistingAlbumRecommendation() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(albumRec1);
        doReturn(Optional.empty()).when(albumRecommendationRepo).findById(501);

        // Act
        mockMvc.perform(
                        get("/albumRecommendation/501"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturnAllAlbumRecommendationsAnd200WhenRetrievingAllAlbumRecommendation() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(allAlbumRec);
        doReturn(allAlbumRec).when(albumRecommendationRepo).findAll();

        // Act
        mockMvc.perform(
                        get("/albumRecommendation"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return All Albums
                );
    }

    @Test
    public void shouldCreateAlbumRecommendationAnd201() throws Exception {
        // Arrange
        AlbumRecommendation newAlbumRec = new AlbumRecommendation();
        newAlbumRec.setAlbumId(2);
        newAlbumRec.setUserId(3);
        newAlbumRec.setLiked(true);

        AlbumRecommendation albumRec3 = new AlbumRecommendation(3, 2, 3, true);

        String inputJson = mapper.writeValueAsString(newAlbumRec);
        String outputJson = mapper.writeValueAsString(albumRec3);
        doReturn(albumRec3).when(albumRecommendationRepo).save(newAlbumRec);

        // Act
        mockMvc.perform(
                        post("/albumRecommendation")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isCreated()) // Should return 201 CREATED
                .andExpect(content().json(outputJson) // Should return the album with ID: 3
                );
    }

    @Test
    public void shouldUpdateExistingAlbumRecommendationAndReturn204() throws Exception {
        // Arrange
        AlbumRecommendation updatedAlbumRec2 = new AlbumRecommendation();
        updatedAlbumRec2.setId(2);
        updatedAlbumRec2.setAlbumId(2);
        updatedAlbumRec2.setUserId(2);
        updatedAlbumRec2.setLiked(true);

        String inputJson = mapper.writeValueAsString(updatedAlbumRec2);
        doReturn(Optional.of(updatedAlbumRec2)).when(albumRecommendationRepo).findById(2);

        // Act
        mockMvc.perform(
                        put("/albumRecommendation")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturn404WhenUpdatingNonExistentAlbumRecommendation() throws Exception {
        // Arrange
        AlbumRecommendation updatedAlbumRec = new AlbumRecommendation();
        updatedAlbumRec.setId(935);
        updatedAlbumRec.setAlbumId(1);
        updatedAlbumRec.setUserId(2);
        updatedAlbumRec.setLiked(false);

        String inputJson = mapper.writeValueAsString(updatedAlbumRec);
        doReturn(Optional.empty()).when(albumRecommendationRepo).findById(935);

        // Act
        mockMvc.perform(
                        put("/albumRecommendation")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturn204WhenDeletingExistingAlbumRecommendation() throws Exception {
        // Arrange
        doReturn(Optional.ofNullable(albumRec2)).when(albumRecommendationRepo).findById(2);

        // Act
        mockMvc.perform(
                        delete("/albumRecommendation/2"))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturn404WhenDeletingNonExistingAlbumRecommendation() throws Exception {
        // Arrange
        doReturn(Optional.empty()).when(albumRecommendationRepo).findById(995);

        // Act
        mockMvc.perform(
                        delete("/albumRecommendation/995"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

}