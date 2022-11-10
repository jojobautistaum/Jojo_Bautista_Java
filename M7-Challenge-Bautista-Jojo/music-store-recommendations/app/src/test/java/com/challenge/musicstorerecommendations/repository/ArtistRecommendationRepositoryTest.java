package com.challenge.musicstorerecommendations.repository;

import com.challenge.musicstorerecommendations.model.ArtistRecommendation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArtistRecommendationRepositoryTest {
    @Autowired
    ArtistRecommendationRepository artistRecommendationRepo;

    @Before
    public void setUp() throws Exception {
        artistRecommendationRepo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteArtistRecommendation() {
        // Arrange
        ArtistRecommendation artistRec = new ArtistRecommendation();
        artistRec.setArtistId(1);
        artistRec.setUserId(1);
        artistRec.setLiked(true);

        // Act
        artistRec = artistRecommendationRepo.save(artistRec);
        ArtistRecommendation whatIGot = artistRecommendationRepo.findById(artistRec.getId()).get();

        // Assert
        assertEquals(artistRec, whatIGot);

        // Arrange and Act
        artistRecommendationRepo.deleteById(artistRec.getId());
        Optional<ArtistRecommendation> deletedArtistRecommendation = artistRecommendationRepo.findById(artistRec.getId());

        // Assert
        assertFalse(deletedArtistRecommendation.isPresent());
    }

    @Test
    public void shouldFindAllArtistRecommendation() {
        // Arrange
        ArtistRecommendation artistRec1 = new ArtistRecommendation();
        artistRec1.setArtistId(1);
        artistRec1.setUserId(1);
        artistRec1.setLiked(true);

        ArtistRecommendation artistRec2 = new ArtistRecommendation();
        artistRec2.setArtistId(1);
        artistRec2.setUserId(2);
        artistRec2.setLiked(true);

        // Act
        artistRec1 = artistRecommendationRepo.save(artistRec1);
        artistRec2 = artistRecommendationRepo.save(artistRec2);
        List<ArtistRecommendation> allArtistsRecommendation = new ArrayList<>(Arrays.asList(artistRec1, artistRec2));

        List<ArtistRecommendation> foundArtistsRecommendation = artistRecommendationRepo.findAll();

        // Assert
        assertEquals(allArtistsRecommendation.size(), foundArtistsRecommendation.size());
    }
}