package com.challenge.musicstorecatalog.controller;

import com.challenge.musicstorecatalog.model.Artist;
import com.challenge.musicstorecatalog.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/artist")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> getAllArtist() {
        return artistRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist getArtistById(@PathVariable Integer id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()) {
            return artist.get();
        }
        throw new IllegalArgumentException("Artist ID `" + id + "' does not exist.");
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody @Valid Artist artist) {
        return artistRepository.save(artist);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtist(@RequestBody @Valid Artist artist) {
        if (artist.getId() == null || artist.getId() < 1) {
            throw new IllegalArgumentException("Arist ID is required and must be greater than zero.");
        }

        final boolean idExist = artistRepository.findById(artist.getId()).isPresent();
        if (!idExist) {
            throw new IllegalArgumentException("Artist ID '" + artist.getId() + "' does not exist.");
        }
        artistRepository.save(artist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Integer id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (!artist.isPresent()) {
            throw new IllegalArgumentException("Artist ID '" + id + "' does not exist.");
        }
        artistRepository.deleteById(id);
    }
}
