package com.challenge.musicstorecatalog.controller;

import com.challenge.musicstorecatalog.model.Label;
import com.challenge.musicstorecatalog.repository.LabelRepository;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LabelController.class)
public class LabelControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LabelRepository labelRepository;

    private Label label1;
    private Label label2;
    private List<Label> allLabels;
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        label1 = new Label(1, "EMI Records", "https://mltr.dk");
        label2 = new Label(2, "Sony Music", "https://sony.com/music/mltr" );
        allLabels = new ArrayList<>(Arrays.asList(label1, label2));
    }

    @Test
    public void shouldReturnTheLabelAndCode200WhenRetrievingAnExistingLabel() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(label1);
        doReturn(Optional.ofNullable(label1)).when(labelRepository).findById(1);

        // Act
        mockMvc.perform(
                get("/label/1"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return an existing label by ID: 1
        );
    }

    @Test
    public void shouldReturnCode404WhenRetrievingNonExistingLabel() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(label1);
        doReturn(Optional.empty()).when(labelRepository).findById(501);

        // Act
        mockMvc.perform(
                        get("/label/501"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturnAllLabelsAndCode200WhenRetrievingAllLabels() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(allLabels);
        doReturn(allLabels).when(labelRepository).findAll();

        // Act
        mockMvc.perform(
                get("/label"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return All Labels
        );
    }

    @Test
    public void shouldCreateLabelAndCode201() throws Exception {
        // Arrange
        Label newLabel = new Label();
        newLabel.setName("Universal Records");
        newLabel.setWebsite("www.universalmusic.com");

        Label label3 = new Label(3, "Universal Records", "www.universalmusic.com");

        String inputJson = mapper.writeValueAsString(newLabel);
        String outputJson = mapper.writeValueAsString(label3);
        doReturn(label3).when(labelRepository).save(newLabel);

        // Act
        mockMvc.perform(
                post("/label")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isCreated()) // Should return 201 CREATED
                .andExpect(content().json(outputJson) // Should return the label with ID: 3
        );
    }

    @Test
    public void shouldUpdateExistingLabelAndReturnCode204() throws Exception {
        // Arrange
        Label updatedLabel2 = new Label();
        updatedLabel2.setId(2);
        updatedLabel2.setName("Warner Music Group");
        updatedLabel2.setWebsite("www.warnermusic.com");

        String inputJson = mapper.writeValueAsString(updatedLabel2);
        doReturn(Optional.of(updatedLabel2)).when(labelRepository).findById(2);

        // Act
        mockMvc.perform(
                        put("/label")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturnCode404WhenUpdatingNonExistentLabel() throws Exception {
        // Arrange
        Label updatedLabel = new Label();
        updatedLabel.setId(935);
        updatedLabel.setName("Warner Music Group");
        updatedLabel.setWebsite("www.warnermusic.com");

        String inputJson = mapper.writeValueAsString(updatedLabel);
        doReturn(Optional.empty()).when(labelRepository).findById(935);

        // Act
        mockMvc.perform(
                        put("/label")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturnCode204WhenDeletingExistingLabel() throws Exception {
        // Arrange
        doReturn(Optional.ofNullable(label2)).when(labelRepository).findById(2);

        // Act
        mockMvc.perform(
                        delete("/label/2"))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
        );
    }

    @Test
    public void shouldReturnCode404WhenDeletingNonExistingLabel() throws Exception {
        // Arrange
        doReturn(Optional.empty()).when(labelRepository).findById(995);

        // Act
        mockMvc.perform(
                        delete("/label/995"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

}