package com.challenge.musicstorecatalog.repository;

import com.challenge.musicstorecatalog.model.Artist;
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
public class ArtistRepositoryTest {
    @Autowired
    ArtistRepository artistRepository;

    @Before
    public void setUp() throws Exception {
        artistRepository.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteArtist() {
        // Arrange
        Artist artist = new Artist();
        artist.setName("Troeis Skjaerbaek");
        artist.setInstagram("@troeis.skjaerbaek");
        artist.setTwitter("@TroeisSkajaerbaek");

        // Act
        artist = artistRepository.save(artist);
        Artist whatIGot = artistRepository.findById(artist.getId()).get();

        // Assert
        assertEquals(artist, whatIGot);

        // Arrange and Act
        artistRepository.deleteById(artist.getId());
        Optional<Artist> deletedArtist = artistRepository.findById(artist.getId());

        // Assert
        assertFalse(deletedArtist.isPresent());
    }

    @Test
    public void shouldFindAllArtist() {
        // Arrange
        Artist artist1 = new Artist();
        artist1.setName("Troeis Skjaerbaek");
        artist1.setInstagram("@troeis.skjaerbaek");
        artist1.setTwitter("@TroeisSkajaerbaek");

        Artist artist2 = new Artist();
        artist2.setName("Soren Madsen");
        artist2.setInstagram("@soren.bodker.madsen");
        artist2.setTwitter("@SorenMadsenMLTR");

        // Act
        artist1 = artistRepository.save(artist1);
        artist2 = artistRepository.save(artist2);
        List<Artist> allArtists = new ArrayList<>(Arrays.asList(artist1, artist2));

        List<Artist> foundArtists = artistRepository.findAll();

        // Assert
        assertEquals(allArtists.size(), foundArtists.size());
    }
}