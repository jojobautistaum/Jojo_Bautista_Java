package com.challenge.musicstorerecommendations.repository;

import com.challenge.musicstorerecommendations.model.AlbumRecommendation;
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
public class AlbumRecommendationRepositoryTest {
    @Autowired
    AlbumRecommendationRepository albumRecommendationRepo;

    @Before
    public void setUp() throws Exception {
        albumRecommendationRepo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteAlbumRecommendation() {
        // Arrange
        AlbumRecommendation albumRec = new AlbumRecommendation();
        albumRec.setAlbumId(1);
        albumRec.setUserId(1);
        albumRec.setLiked(true);

        // Act
        albumRec = albumRecommendationRepo.save(albumRec);
        AlbumRecommendation whatIGot = albumRecommendationRepo.findById(albumRec.getId()).get();

        // Assert
        assertEquals(albumRec, whatIGot);

        // Arrange and Act
        albumRecommendationRepo.deleteById(albumRec.getId());
        Optional<AlbumRecommendation> deletedAlbumRecommendation = albumRecommendationRepo.findById(albumRec.getId());

        // Assert
        assertFalse(deletedAlbumRecommendation.isPresent());
    }

    @Test
    public void shouldFindAllAlbumRecommendation() {
        // Arrange
        AlbumRecommendation albumRec1 = new AlbumRecommendation();
        albumRec1.setAlbumId(1);
        albumRec1.setUserId(1);
        albumRec1.setLiked(true);

        AlbumRecommendation albumRec2 = new AlbumRecommendation();
        albumRec2.setAlbumId(1);
        albumRec2.setUserId(2);
        albumRec2.setLiked(true);

        // Act
        albumRec1 = albumRecommendationRepo.save(albumRec1);
        albumRec2 = albumRecommendationRepo.save(albumRec2);
        List<AlbumRecommendation> allAlbumsRecommendation = new ArrayList<>(Arrays.asList(albumRec1, albumRec2));

        List<AlbumRecommendation> foundAlbumsRecommendation = albumRecommendationRepo.findAll();

        // Assert
        assertEquals(allAlbumsRecommendation.size(), foundAlbumsRecommendation.size());
    }
}