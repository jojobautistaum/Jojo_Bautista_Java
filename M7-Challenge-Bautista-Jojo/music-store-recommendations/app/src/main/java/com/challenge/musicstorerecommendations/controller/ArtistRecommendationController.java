package com.challenge.musicstorerecommendations.controller;

import com.challenge.musicstorerecommendations.model.ArtistRecommendation;
import com.challenge.musicstorerecommendations.repository.ArtistRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/artistRecommendation")
public class ArtistRecommendationController {
    @Autowired
    private ArtistRecommendationRepository artistRecommendationRepo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendation> getAllArtistRecommendations() {
        return artistRecommendationRepo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistRecommendation getArtistRecommendation(@PathVariable Integer id) {
        Optional<ArtistRecommendation> artistRecommendation = artistRecommendationRepo.findById(id);
        if (!artistRecommendation.isPresent()) {
            throw new IllegalArgumentException("Artist Recommendation ID '" + id + "' does not exist.");
        }
        return artistRecommendation.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendation createArtistRecommendation(@RequestBody @Valid ArtistRecommendation artistRecommendation) {
        return artistRecommendationRepo.save(artistRecommendation);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtistRecommendation(@RequestBody @Valid ArtistRecommendation artistRecommendation) {
        if (artistRecommendation.getId() == null || artistRecommendation.getId() < 1) {
            throw new IllegalArgumentException("Artist Recommendation Id is required and must be greater than zero.");
        }

        final boolean idExist = artistRecommendationRepo.findById(artistRecommendation.getId()).isPresent();
        if (!idExist) {
            throw new IllegalArgumentException("Artist Recommendation ID '" + artistRecommendation.getId() + "' does not exist.");
        }
        artistRecommendationRepo.save(artistRecommendation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtistRecommendation(@PathVariable Integer id) {
        Optional<ArtistRecommendation> artistRecommendation = artistRecommendationRepo.findById(id);
        if (!artistRecommendation.isPresent()) {
            throw new IllegalArgumentException("Artist Recommendation ID '" + id + "' does not exist.");
        }
        artistRecommendationRepo.deleteById(id);
    }
}
