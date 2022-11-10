package com.challenge.musicstorecatalog.controller;

import com.challenge.musicstorecatalog.model.Artist;
import com.challenge.musicstorecatalog.repository.ArtistRepository;
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
@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistRepository artistRepository;

    private Artist artist1;
    private Artist artist2;
    private Artist artist3;
    private List<Artist> allArtists;
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        artist1 = new Artist(1, "Jascha Richter", "@jascha.richter.official", "@JascharMltr");
        artist2 = new Artist(2, "Soren Madsen", "@soren.bodker.madsen", "@SorenMadsenCom" );
        artist3 = new Artist(3, "Kare Wanscher", "@michaellearnstorock", "@");
        allArtists = new ArrayList<>(Arrays.asList(artist1, artist2));
    }

    @Test
    public void shouldReturnTheArtistAndCode200WhenRetrievingAnExistingArtist() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(artist1);
        doReturn(Optional.ofNullable(artist1)).when(artistRepository).findById(1);

        // Act
        mockMvc.perform(
                        get("/artist/1"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return an existing artist by ID: 1
                );
    }

    @Test
    public void shouldReturnCode404WhenRetrievingNonExistingArtist() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(artist1);
        doReturn(Optional.empty()).when(artistRepository).findById(501);

        // Act
        mockMvc.perform(
                        get("/artist/501"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturnAllArtistsAndCode200WhenRetrievingAllArtists() throws Exception {
        // Arrange
        String expectedJson = mapper.writeValueAsString(allArtists);
        doReturn(allArtists).when(artistRepository).findAll();

        // Act
        mockMvc.perform(
                        get("/artist"))
                .andDo(print()) // Assert
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(content().json(expectedJson) // Should return All Artists
                );
    }

    @Test
    public void shouldCreateArtistAndCode201() throws Exception {
        // Arrange
        Artist newArtist = new Artist();
        newArtist.setName("Troeis Skjaerbaek");
        newArtist.setInstagram("@troeis.skjaerbaek");
        newArtist.setTwitter("@TroeisSkajaerbaek");

        Artist artist4 = new Artist(4, "Troeis Skjaerbaek", "@troeis.skjaerbaek", "@TroeisSkajaerbaek");

        String inputJson = mapper.writeValueAsString(newArtist);
        String outputJson = mapper.writeValueAsString(artist4);
        doReturn(artist4).when(artistRepository).save(newArtist);

        // Act
        mockMvc.perform(
                        post("/artist")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isCreated()) // Should return 201 CREATED
                .andExpect(content().json(outputJson) // Should return the artist with ID: 3
                );
    }

    @Test
    public void shouldUpdateExistingArtistAndReturnCode204() throws Exception {
        // Arrange
        Artist updatedArtist2 = new Artist();
        updatedArtist2.setId(2);
        updatedArtist2.setName("Soren Madsen");
        updatedArtist2.setInstagram("@soren.bodker.madsen");
        updatedArtist2.setTwitter("@SorenMadsenMLTR");

        String inputJson = mapper.writeValueAsString(updatedArtist2);
        doReturn(Optional.of(updatedArtist2)).when(artistRepository).findById(2);

        // Act
        mockMvc.perform(
                        put("/artist")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturnCode404WhenUpdatingNonExistentArtist() throws Exception {
        // Arrange
        Artist updatedArtist = new Artist();
        updatedArtist.setId(975);
        updatedArtist.setName("Soren Madsen");
        updatedArtist.setInstagram("@soren.bodker.madsen");
        updatedArtist.setTwitter("@SorenMadsenMLTR");

        String inputJson = mapper.writeValueAsString(updatedArtist);
        doReturn(Optional.empty()).when(artistRepository).findById(975);

        // Act
        mockMvc.perform(
                        put("/artist")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }

    @Test
    public void shouldReturnCode204WhenDeletingExistingArtist() throws Exception {
        // Arrange
        doReturn(Optional.ofNullable(artist2)).when(artistRepository).findById(2);

        // Act
        mockMvc.perform(
                        delete("/artist/2"))
                .andDo(print()) // Assert
                .andExpect(status().isNoContent() // Should return 204 NO_CONTENT
                );
    }

    @Test
    public void shouldReturnCode404WhenDeletingNonExistingArtist() throws Exception {
        // Arrange
        doReturn(Optional.empty()).when(artistRepository).findById(995);

        // Act
        mockMvc.perform(
                        delete("/artist/995"))
                .andDo(print()) // Assert
                .andExpect(status().isNotFound() // Should return 404 NOT_FOUND
                );
    }
}