package com.challenge.musicstorecatalog.controller;

import com.challenge.musicstorecatalog.model.Album;
import com.challenge.musicstorecatalog.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/album")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAllAlbum() {
        return albumRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Album getAlbumById(@PathVariable Integer id){
        Optional<Album> album = albumRepository.findById(id);
        if (album.isPresent()) {
            return album.get();
        }
        throw new IllegalArgumentException ("Album ID '" + id + "' does not exist.");
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody @Valid Album album) {
        return albumRepository.save(album);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbum(@RequestBody @Valid Album album){
        if(album.getId() == null || album.getId() < 1) {
            throw new IllegalArgumentException("Album ID is required and must be greater than zero.");
        }

        final boolean idExist = albumRepository.findById(album.getId()).isPresent();
        if(!idExist) {
            throw new IllegalArgumentException("Album ID '" + album.getId() + "' does not exist");
        }
        albumRepository.save(album);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Integer id) {
        Optional<Album> album = albumRepository.findById(id);
        if (!album.isPresent()) {
            throw new IllegalArgumentException("Album ID '" + id + "' does not exist.");
        }
        albumRepository.deleteById(id);
    }
}
