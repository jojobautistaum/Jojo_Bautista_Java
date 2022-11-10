package com.challenge.musicstorecatalog.repository;

import com.challenge.musicstorecatalog.model.Album;
import com.challenge.musicstorecatalog.model.Artist;
import com.challenge.musicstorecatalog.model.Label;
import com.challenge.musicstorecatalog.model.Track;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TrackRepositoryTest {
    @Autowired
    private TrackRepository trackRepository;

    @Before
    public void setUp() throws Exception {
        trackRepository.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteTrack() {
        // Arrange
        Track track = new Track();
        track.setAlbumId(1);
        track.setTitle("Angel Eyes");
        track.setRunTime(5);

        // Act
        track = trackRepository.save(track);
        Track whatIGot = trackRepository.findById(track.getId()).get();

        // Assert
        assertEquals(track, whatIGot);

        // Arrange and Act
        trackRepository.deleteById(track.getId());
        Optional<Track> deletedTrack = trackRepository.findById(track.getId());

        // Assert
        assertFalse(deletedTrack.isPresent());
    }

    @Test
    public void shouldFindAllTrack() {
        // Arrange
        Track track1 = new Track();
        track1.setAlbumId(1);
        track1.setTitle("Angel Eyes");
        track1.setRunTime(5);


        Track track2 = new Track();
        track2.setAlbumId(1);
        track2.setTitle("Blue Night");
        track2.setRunTime(4);

        // Act
        track1 = trackRepository.save(track1);
        track2 = trackRepository.save(track2);
        List<Track> allTracks = new ArrayList<>(Arrays.asList(track1, track2));

        List<Track> foundTracks = trackRepository.findAll();

        // Assert
        assertEquals(allTracks.size(), foundTracks.size());
    }
}