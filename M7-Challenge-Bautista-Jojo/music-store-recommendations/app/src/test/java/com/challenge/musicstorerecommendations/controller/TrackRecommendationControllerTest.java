package com.challenge.musicstorerecommendations.controller;

import com.challenge.musicstorerecommendations.model.TrackRecommendation;
import com.challenge.musicstorerecommendations.repository.TrackRecommendationRepository;
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
@WebMvcTest(TrackRecommendationController.class)
public class TrackRecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackRecommendationRepository trackRecommendationRepo;

    private TrackRecommendation trackRec1;
    private TrackRecommendation trackRec2;
    private List<TrackRecommendation> allTrackRec;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        trackRec1 = new TrackRecommendation(1, 1, 1, true);
        trackRec2 = new TrackRecommendation(2, 1, 2, false);
        allTrackRec = new ArrayList<>(Arrays.asList(trackRec1, trackRec2));
    }

    @Test
    public void shouldReturnTrackRecommendationAnd200WhenRetrievingAnExistingTrackRecommendation() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(trackRec1);
        doReturn(Optional.ofNullable(trackRec1)).when(trackRecommendationRepo).findById(1);

        // Act
        mockMvc.perform(
                        get("/trackRecommendation/1"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return an existing track by ID: 1
                );
    }

    @Test
    public void shouldReturn404WhenRetrievingNonExistingTrackRecommendation() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(trackRec1);
        doReturn(Optional.empty()).when(trackRecommendationRepo).findById(501);

        // Act
        mockMvc.perform(
                        get("/trackRecommendation/501"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturnAllTrackRecommendationsAnd200WhenRetrievingAllTrackRecommendation() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(allTrackRec);
        doReturn(allTrackRec).when(trackRecommendationRepo).findAll();

        // Act
        mockMvc.perform(
                        get("/trackRecommendation"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return All Tracks
                );
    }

    @Test
    public void shouldCreateTrackRecommendationAnd201() throws Exception {
        // Arrange
        TrackRecommendation newTrackRec = new TrackRecommendation();
        newTrackRec.setTrackId(2);
        newTrackRec.setUserId(3);
        newTrackRec.setLiked(true);

        TrackRecommendation trackRec3 = new TrackRecommendation(3, 2, 3, true);

        String inputJson = mapper.writeValueAsString(newTrackRec);
        String outputJson = mapper.writeValueAsString(trackRec3);
        doReturn(trackRec3).when(trackRecommendationRepo).save(newTrackRec);

        // Act
        mockMvc.perform(
                        post("/trackRecommendation")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isCreated()) // Should return 201 CREATED
                .andExpect(content().json(outputJson) // Should return the track with ID: 3
                );
    }

    @Test
    public void shouldUpdateExistingTrackRecommendationAndReturn204() throws Exception {
        // Arrange
        TrackRecommendation updatedTrackRec2 = new TrackRecommendation();
        updatedTrackRec2.setId(2);
        updatedTrackRec2.setTrackId(2);
        updatedTrackRec2.setUserId(2);
        updatedTrackRec2.setLiked(true);

        String inputJson = mapper.writeValueAsString(updatedTrackRec2);
        doReturn(Optional.of(updatedTrackRec2)).when(trackRecommendationRepo).findById(2);

        // Act
        mockMvc.perform(
                        put("/trackRecommendation")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturn404WhenUpdatingNonExistentTrackRecommendation() throws Exception {
        // Arrange
        TrackRecommendation updatedTrackRec = new TrackRecommendation();
        updatedTrackRec.setId(935);
        updatedTrackRec.setTrackId(1);
        updatedTrackRec.setUserId(2);
        updatedTrackRec.setLiked(false);

        String inputJson = mapper.writeValueAsString(updatedTrackRec);
        doReturn(Optional.empty()).when(trackRecommendationRepo).findById(935);

        // Act
        mockMvc.perform(
                        put("/trackRecommendation")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturn204WhenDeletingExistingTrackRecommendation() throws Exception {
        // Arrange
        doReturn(Optional.ofNullable(trackRec2)).when(trackRecommendationRepo).findById(2);

        // Act
        mockMvc.perform(
                        delete("/trackRecommendation/2"))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturn404WhenDeletingNonExistingTrackRecommendation() throws Exception {
        // Arrange
        doReturn(Optional.empty()).when(trackRecommendationRepo).findById(995);

        // Act
        mockMvc.perform(
                        delete("/trackRecommendation/995"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }
}