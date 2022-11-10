package com.challenge.musicstorecatalog.controller;

import com.challenge.musicstorecatalog.model.Track;
import com.challenge.musicstorecatalog.repository.TrackRepository;
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

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrackController.class)
public class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackRepository trackRepository;

    private Track track1;
    private Track track2;
    private List<Track> allTracks;
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        track1 = new Track(1, 1, "You Took My Heart Away", 4);
        track2 = new Track(2, 1, "Blue Night", 4 );
        allTracks = new ArrayList<>(Arrays.asList(track1, track2));
    }

    @Test
    public void shouldReturnTheTrackAndCode200WhenRetrievingAnExistingTrack() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(track1);
        doReturn(Optional.ofNullable(track1)).when(trackRepository).findById(1);

        // Act
        mockMvc.perform(
                        get("/track/1"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return an existing track by ID: 1
                );
    }

    @Test
    public void shouldReturnCode404WhenRetrievingNonExistingTrack() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(track1);
        doReturn(Optional.empty()).when(trackRepository).findById(501);

        // Act
        mockMvc.perform(
                        get("/track/501"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturnAllTracksAndCode200WhenRetrievingAllTracks() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(allTracks);
        doReturn(allTracks).when(trackRepository).findAll();

        // Act
        mockMvc.perform(
                        get("/track"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return All Tracks
                );
    }

    @Test
    public void shouldCreateTrackAndCode201() throws Exception {
        // Arrange
        Track newTrack = new Track();
        newTrack.setAlbumId(1);
        newTrack.setTitle("Angel Eyes");
        newTrack.setRunTime(5);

        Track track3 = new Track(3, 1, "Angel Eyes", 5);

        String inputJson = mapper.writeValueAsString(newTrack);
        String outputJson = mapper.writeValueAsString(track3);
        doReturn(track3).when(trackRepository).save(newTrack);

        // Act
        mockMvc.perform(
                        post("/track")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isCreated()) // Should return 201 CREATED
                .andExpect(content().json(outputJson) // Should return the track with ID: 3
                );
    }

    @Test
    public void shouldUpdateExistingTrackAndReturnCode204() throws Exception {
        // Arrange
        Track updatedTrack2 = new Track();
        updatedTrack2.setId(2);
        updatedTrack2.setAlbumId(1);
        updatedTrack2.setTitle("Angel Eyes");
        updatedTrack2.setRunTime(5);

        String inputJson = mapper.writeValueAsString(updatedTrack2);
        doReturn(Optional.of(updatedTrack2)).when(trackRepository).findById(2);

        // Act
        mockMvc.perform(
                        put("/track")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturnCode404WhenUpdatingNonExistentTrack() throws Exception {
        // Arrange
        Track updatedTrack = new Track();
        updatedTrack.setId(777);
        updatedTrack.setAlbumId(1);
        updatedTrack.setTitle("Angel Eyes");
        updatedTrack.setRunTime(5);

        String inputJson = mapper.writeValueAsString(updatedTrack);
        doReturn(Optional.empty()).when(trackRepository).findById(777);

        // Act
        mockMvc.perform(
                        put("/track")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturnCode204WhenDeletingExistingTrack() throws Exception {
        // Arrange
        doReturn(Optional.ofNullable(track2)).when(trackRepository).findById(2);

        // Act
        mockMvc.perform(
                        delete("/track/2"))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturnCode404WhenDeletingNonExistingTrack() throws Exception {
        // Arrange
        doReturn(Optional.empty()).when(trackRepository).findById(995);

        // Act
        mockMvc.perform(
                        delete("/track/995"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

}