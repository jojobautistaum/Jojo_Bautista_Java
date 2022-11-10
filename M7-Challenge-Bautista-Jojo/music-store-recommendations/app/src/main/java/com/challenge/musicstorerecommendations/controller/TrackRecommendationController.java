package com.challenge.musicstorerecommendations.controller;

import com.challenge.musicstorerecommendations.model.TrackRecommendation;
import com.challenge.musicstorerecommendations.repository.TrackRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/trackRecommendation")
public class TrackRecommendationController {
    @Autowired
    private TrackRecommendationRepository trackRecommendationRepo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendation> getAllTrackRecommendations() {
        return trackRecommendationRepo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackRecommendation getTrackRecommendation(@PathVariable Integer id) {
        Optional<TrackRecommendation> trackRecommendation = trackRecommendationRepo.findById(id);
        if (!trackRecommendation.isPresent()) {
            throw new IllegalArgumentException("Track Recommendation ID '" + id + "' does not exist.");
        }
        return trackRecommendation.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation createTrackRecommendation(@RequestBody @Valid TrackRecommendation trackRecommendation) {
        return trackRecommendationRepo.save(trackRecommendation);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrackRecommendation(@RequestBody @Valid TrackRecommendation trackRecommendation) {
        if (trackRecommendation.getId() == null || trackRecommendation.getId() < 1) {
            throw new IllegalArgumentException("Track Recommendation ID is required and must be greater than zero.");
        }

        final boolean idExist = trackRecommendationRepo.findById(trackRecommendation.getId()).isPresent();
        if (!idExist) {
            throw new IllegalArgumentException("Track Recommendation ID '" + trackRecommendation.getId() + "' does not exist.");
        }
        trackRecommendationRepo.save(trackRecommendation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackRecommendation(@PathVariable Integer id) {
        Optional<TrackRecommendation> trackRecommendation = trackRecommendationRepo.findById(id);
        if (!trackRecommendation.isPresent()) {
            throw new IllegalArgumentException("Track Recommendation ID '" + id + "' does not exist.");
        }
        trackRecommendationRepo.deleteById(id);
    }
}
