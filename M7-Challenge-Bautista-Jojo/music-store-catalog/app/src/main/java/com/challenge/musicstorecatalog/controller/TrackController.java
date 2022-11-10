package com.challenge.musicstorecatalog.controller;

import com.challenge.musicstorecatalog.model.Track;
import com.challenge.musicstorecatalog.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/track")
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Track getTrackById(@PathVariable Integer id) {
        Optional<Track> track = trackRepository.findById(id);
        if (track.isPresent()) {
            return track.get();
        }
        throw new IllegalArgumentException("Track ID '" + id + "' does not exist.");
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Track createTract(@RequestBody @Valid Track track){
        return trackRepository.save(track);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrack(@RequestBody @Valid Track track) {
        if (track.getId() == null || track.getId() < 1) {
            throw new IllegalArgumentException("Track ID is required and must be greater than zero.");
        }

        final boolean idExist = trackRepository.findById(track.getId()).isPresent();
        if (!idExist) {
            throw new IllegalArgumentException("Track ID '" + track.getId() + "' does not exist.");
        }
        trackRepository.save(track);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrack(@PathVariable Integer id) {
        Optional<Track> track = trackRepository.findById(id);
        if (!track.isPresent()) {
            throw new IllegalArgumentException("Track ID '" + id + "' does not exist.");
        }
        trackRepository.deleteById(id);
    }
}
