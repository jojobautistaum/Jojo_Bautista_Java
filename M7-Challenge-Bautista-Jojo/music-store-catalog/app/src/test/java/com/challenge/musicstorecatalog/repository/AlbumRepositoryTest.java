package com.challenge.musicstorecatalog.repository;

import com.challenge.musicstorecatalog.model.Album;
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
public class AlbumRepositoryTest {
    @Autowired
    AlbumRepository albumRepository;

    @Before
    public void setUp() throws Exception {
        albumRepository.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteAlbum() {
        // Arrange
        Album album = new Album();
        album.setTitle("Eternity");
        album.setArtistId(1);
        album.setReleaseDate(LocalDate.of(2008,10,27));
        album.setLabelId(1);
        album.setListPrice(new BigDecimal("39.99"));

        // Act
        album = albumRepository.save(album);
        Album whatIGot = albumRepository.findById(album.getId()).get();

        // Assert
        assertEquals(album, whatIGot);

        // Arrange and Act
        albumRepository.deleteById(album.getId());
        Optional<Album> deletedAlbum = albumRepository.findById(album.getId());

        // Assert
        assertFalse(deletedAlbum.isPresent());
    }

    @Test
    public void shouldFindAllAlbum() {
        // Arrange
        Album album1 = new Album();
        album1.setTitle("Eternity");
        album1.setArtistId(1);
        album1.setReleaseDate(LocalDate.of(2008,10,27));
        album1.setLabelId(1);
        album1.setListPrice(new BigDecimal("39.99"));

        Album album2 = new Album();
        album2.setTitle("Scandinavia");
        album2.setArtistId(1);
        album2.setReleaseDate(LocalDate.of(2012,6,11));
        album2.setLabelId(1);
        album2.setListPrice(new BigDecimal("44.99"));

        // Act
        album1 = albumRepository.save(album1);
        album2 = albumRepository.save(album2);
        List<Album> allAlbums = new ArrayList<>(Arrays.asList(album1, album2));

        List<Album> foundAlbums = albumRepository.findAll();

        // Assert
        assertEquals(allAlbums.size(), foundAlbums.size());
    }
}