package com.challenge.musicstorerecommendations.controller;

import com.challenge.musicstorerecommendations.model.ArtistRecommendation;
import com.challenge.musicstorerecommendations.repository.ArtistRecommendationRepository;
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
@WebMvcTest(ArtistRecommendationController.class)
public class ArtistRecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistRecommendationRepository artistRecommendationRepo;

    private ArtistRecommendation artistRec1;
    private ArtistRecommendation artistRec2;
    private List<ArtistRecommendation> allArtistRec;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        artistRec1 = new ArtistRecommendation(1, 1, 1, true);
        artistRec2 = new ArtistRecommendation(2, 1, 2, false);
        allArtistRec = new ArrayList<>(Arrays.asList(artistRec1, artistRec2));
    }

    @Test
    public void shouldReturnArtistRecommendationAnd200WhenRetrievingAnExistingArtistRecommendation() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(artistRec1);
        doReturn(Optional.ofNullable(artistRec1)).when(artistRecommendationRepo).findById(1);

        // Act
        mockMvc.perform(
                        get("/artistRecommendation/1"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return an existing artist by ID: 1
                );
    }

    @Test
    public void shouldReturn404WhenRetrievingNonExistingArtistRecommendation() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(artistRec1);
        doReturn(Optional.empty()).when(artistRecommendationRepo).findById(501);

        // Act
        mockMvc.perform(
                        get("/artistRecommendation/501"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturnAllArtistRecommendationsAnd200WhenRetrievingAllArtistRecommendation() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(allArtistRec);
        doReturn(allArtistRec).when(artistRecommendationRepo).findAll();

        // Act
        mockMvc.perform(
                        get("/artistRecommendation"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return All Artists
                );
    }

    @Test
    public void shouldCreateArtistRecommendationAnd201() throws Exception {
        // Arrange
        ArtistRecommendation newArtistRec = new ArtistRecommendation();
        newArtistRec.setArtistId(2);
        newArtistRec.setUserId(3);
        newArtistRec.setLiked(true);

        ArtistRecommendation artistRec3 = new ArtistRecommendation(3, 2, 3, true);

        String inputJson = mapper.writeValueAsString(newArtistRec);
        String outputJson = mapper.writeValueAsString(artistRec3);
        doReturn(artistRec3).when(artistRecommendationRepo).save(newArtistRec);

        // Act
        mockMvc.perform(
                        post("/artistRecommendation")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isCreated()) // Should return 201 CREATED
                .andExpect(content().json(outputJson) // Should return the artist with ID: 3
                );
    }

    @Test
    public void shouldUpdateExistingArtistRecommendationAndReturn204() throws Exception {
        // Arrange
        ArtistRecommendation updatedArtistRec2 = new ArtistRecommendation();
        updatedArtistRec2.setId(2);
        updatedArtistRec2.setArtistId(2);
        updatedArtistRec2.setUserId(2);
        updatedArtistRec2.setLiked(true);

        String inputJson = mapper.writeValueAsString(updatedArtistRec2);
        doReturn(Optional.of(updatedArtistRec2)).when(artistRecommendationRepo).findById(2);

        // Act
        mockMvc.perform(
                        put("/artistRecommendation")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturn404WhenUpdatingNonExistentArtistRecommendation() throws Exception {
        // Arrange
        ArtistRecommendation updatedArtistRec = new ArtistRecommendation();
        updatedArtistRec.setId(935);
        updatedArtistRec.setArtistId(1);
        updatedArtistRec.setUserId(2);
        updatedArtistRec.setLiked(false);

        String inputJson = mapper.writeValueAsString(updatedArtistRec);
        doReturn(Optional.empty()).when(artistRecommendationRepo).findById(935);

        // Act
        mockMvc.perform(
                        put("/artistRecommendation")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturn204WhenDeletingExistingArtistRecommendation() throws Exception {
        // Arrange
        doReturn(Optional.ofNullable(artistRec2)).when(artistRecommendationRepo).findById(2);

        // Act
        mockMvc.perform(
                        delete("/artistRecommendation/2"))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturn404WhenDeletingNonExistingArtistRecommendation() throws Exception {
        // Arrange
        doReturn(Optional.empty()).when(artistRecommendationRepo).findById(995);

        // Act
        mockMvc.perform(
                        delete("/artistRecommendation/995"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }
}