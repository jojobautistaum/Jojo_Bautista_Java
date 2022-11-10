package com.challenge.musicstorerecommendations.repository;

import com.challenge.musicstorerecommendations.model.TrackRecommendation;
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
public class TrackRecommendationRepositoryTest {
    @Autowired
    TrackRecommendationRepository trackRecommendationRepo;

    @Before
    public void setUp() throws Exception {
        trackRecommendationRepo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteTrackRecommendation() {
        // Arrange
        TrackRecommendation trackRec = new TrackRecommendation();
        trackRec.setTrackId(1);
        trackRec.setUserId(1);
        trackRec.setLiked(true);

        // Act
        trackRec = trackRecommendationRepo.save(trackRec);
        TrackRecommendation whatIGot = trackRecommendationRepo.findById(trackRec.getId()).get();

        // Assert
        assertEquals(trackRec, whatIGot);

        // Arrange and Act
        trackRecommendationRepo.deleteById(trackRec.getId());
        Optional<TrackRecommendation> deletedTrackRecommendation = trackRecommendationRepo.findById(trackRec.getId());

        // Assert
        assertFalse(deletedTrackRecommendation.isPresent());
    }

    @Test
    public void shouldFindAllTrack() {
        // Arrange
        TrackRecommendation trackRec1 = new TrackRecommendation();
        trackRec1.setTrackId(1);
        trackRec1.setUserId(1);
        trackRec1.setLiked(true);

        TrackRecommendation trackRec2 = new TrackRecommendation();
        trackRec2.setTrackId(1);
        trackRec2.setUserId(2);
        trackRec2.setLiked(true);

        // Act
        trackRec1 = trackRecommendationRepo.save(trackRec1);
        trackRec2 = trackRecommendationRepo.save(trackRec2);
        List<TrackRecommendation> allTracksRecommendation = new ArrayList<>(Arrays.asList(trackRec1, trackRec2));

        List<TrackRecommendation> foundTracksRecommendation = trackRecommendationRepo.findAll();

        // Assert
        assertEquals(allTracksRecommendation.size(), foundTracksRecommendation.size());
    }
}