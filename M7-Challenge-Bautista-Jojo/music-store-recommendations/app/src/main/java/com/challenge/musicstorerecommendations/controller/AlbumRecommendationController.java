package com.challenge.musicstorerecommendations.controller;

import com.challenge.musicstorerecommendations.model.AlbumRecommendation;
import com.challenge.musicstorerecommendations.repository.AlbumRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/albumRecommendation")
public class AlbumRecommendationController {
    @Autowired
    private AlbumRecommendationRepository albumRecommendationRepo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendation> getAllAlbumRecommendations() {
        return albumRecommendationRepo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecommendation getAlbumRecommendation(@PathVariable Integer id) {
        Optional<AlbumRecommendation> albumRecommendation = albumRecommendationRepo.findById(id);
        if (!albumRecommendation.isPresent()) {
            throw new IllegalArgumentException("Album Recommendation ID '" + id + "' does not exist.");
        }
        return albumRecommendation.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation createAlbumRecommendation(@RequestBody @Valid AlbumRecommendation albumRecommendation) {
        return albumRecommendationRepo.save(albumRecommendation);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbumRecommendation(@RequestBody @Valid AlbumRecommendation albumRecommendation) {
        if (albumRecommendation.getId() == null || albumRecommendation.getId() < 1) {
            throw new IllegalArgumentException("Album Recommendation Id is required and must be greater than zero.");
        }

        final boolean idExist = albumRecommendationRepo.findById(albumRecommendation.getId()).isPresent();
        if (!idExist) {
            throw new IllegalArgumentException("Album Recommendation ID '" + albumRecommendation.getId() + "' does not exist.");
        }
        albumRecommendationRepo.save(albumRecommendation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumRecommendation(@PathVariable Integer id) {
        Optional<AlbumRecommendation> albumRecommendation = albumRecommendationRepo.findById(id);
        if (!albumRecommendation.isPresent()) {
            throw new IllegalArgumentException("Album Recommendation ID '" + id + "' does not exist.");
        }
        albumRecommendationRepo.deleteById(id);
    }
}
