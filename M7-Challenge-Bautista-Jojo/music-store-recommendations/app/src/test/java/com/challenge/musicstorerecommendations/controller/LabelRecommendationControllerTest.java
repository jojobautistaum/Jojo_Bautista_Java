package com.challenge.musicstorerecommendations.controller;

import com.challenge.musicstorerecommendations.model.LabelRecommendation;
import com.challenge.musicstorerecommendations.repository.LabelRecommendationRepository;
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
@WebMvcTest(LabelRecommendationController.class)
public class LabelRecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LabelRecommendationRepository labelRecommendationRepo;

    private LabelRecommendation labelRec1;
    private LabelRecommendation labelRec2;
    private List<LabelRecommendation> allLabelRec;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        labelRec1 = new LabelRecommendation(1, 1, 1, true);
        labelRec2 = new LabelRecommendation(2, 1, 2, false);
        allLabelRec = new ArrayList<>(Arrays.asList(labelRec1, labelRec2));
    }

    @Test
    public void shouldReturnLabelRecommendationAnd200WhenRetrievingAnExistingLabelRecommendation() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(labelRec1);
        doReturn(Optional.ofNullable(labelRec1)).when(labelRecommendationRepo).findById(1);

        // Act
        mockMvc.perform(
                        get("/labelRecommendation/1"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return an existing label by ID: 1
                );
    }

    @Test
    public void shouldReturn404WhenRetrievingNonExistingLabelRecommendation() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(labelRec1);
        doReturn(Optional.empty()).when(labelRecommendationRepo).findById(501);

        // Act
        mockMvc.perform(
                        get("/labelRecommendation/501"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturnAllLabelRecommendationsAnd200WhenRetrievingAllLabelRecommendation() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(allLabelRec);
        doReturn(allLabelRec).when(labelRecommendationRepo).findAll();

        // Act
        mockMvc.perform(
                        get("/labelRecommendation"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return All Labels
                );
    }

    @Test
    public void shouldCreateLabelRecommendationAnd201() throws Exception {
        // Arrange
        LabelRecommendation newLabelRec = new LabelRecommendation();
        newLabelRec.setLabelId(2);
        newLabelRec.setUserId(3);
        newLabelRec.setLiked(true);

        LabelRecommendation labelRec3 = new LabelRecommendation(3, 2, 3, true);

        String inputJson = mapper.writeValueAsString(newLabelRec);
        String outputJson = mapper.writeValueAsString(labelRec3);
        doReturn(labelRec3).when(labelRecommendationRepo).save(newLabelRec);

        // Act
        mockMvc.perform(
                        post("/labelRecommendation")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isCreated()) // Should return 201 CREATED
                .andExpect(content().json(outputJson) // Should return the label with ID: 3
                );
    }

    @Test
    public void shouldUpdateExistingLabelRecommendationAndReturn204() throws Exception {
        // Arrange
        LabelRecommendation updatedLabelRec2 = new LabelRecommendation();
        updatedLabelRec2.setId(2);
        updatedLabelRec2.setLabelId(2);
        updatedLabelRec2.setUserId(2);
        updatedLabelRec2.setLiked(true);

        String inputJson = mapper.writeValueAsString(updatedLabelRec2);
        doReturn(Optional.of(updatedLabelRec2)).when(labelRecommendationRepo).findById(2);

        // Act
        mockMvc.perform(
                        put("/labelRecommendation")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturn404WhenUpdatingNonExistentLabelRecommendation() throws Exception {
        // Arrange
        LabelRecommendation updatedLabelRec = new LabelRecommendation();
        updatedLabelRec.setId(935);
        updatedLabelRec.setLabelId(1);
        updatedLabelRec.setUserId(2);
        updatedLabelRec.setLiked(false);

        String inputJson = mapper.writeValueAsString(updatedLabelRec);
        doReturn(Optional.empty()).when(labelRecommendationRepo).findById(935);

        // Act
        mockMvc.perform(
                        put("/labelRecommendation")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturn204WhenDeletingExistingLabelRecommendation() throws Exception {
        // Arrange
        doReturn(Optional.ofNullable(labelRec2)).when(labelRecommendationRepo).findById(2);

        // Act
        mockMvc.perform(
                        delete("/labelRecommendation/2"))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturn404WhenDeletingNonExistingLabelRecommendation() throws Exception {
        // Arrange
        doReturn(Optional.empty()).when(labelRecommendationRepo).findById(995);

        // Act
        mockMvc.perform(
                        delete("/labelRecommendation/995"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }
}