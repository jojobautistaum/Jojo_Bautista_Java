package com.challenge.musicstorerecommendations.controller;

import com.challenge.musicstorerecommendations.model.LabelRecommendation;
import com.challenge.musicstorerecommendations.repository.LabelRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/labelRecommendation")
public class LabelRecommendationController {

    @Autowired
    private LabelRecommendationRepository labelRecommendationRepo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendation> getAllLabelRecommendations() {
        return labelRecommendationRepo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelRecommendation getLabelRecommendation(@PathVariable Integer id) {
        Optional<LabelRecommendation> labelRecommendation = labelRecommendationRepo.findById(id);
        if (!labelRecommendation.isPresent()) {
            throw new IllegalArgumentException("Label Recommendation ID '" + id + "' does not exist.");
        }
        return labelRecommendation.get();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation createLabelRecommendation(@RequestBody @Valid LabelRecommendation labelRecommendation) {
        return labelRecommendationRepo.save(labelRecommendation);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabelRecommendation(@RequestBody @Valid LabelRecommendation labelRecommendation) {
        if (labelRecommendation.getId() == null || labelRecommendation.getId() < 1) {
            throw new IllegalArgumentException("Album Recommendation Id is required and must be greater than zero.");
        }

        final boolean idExist = labelRecommendationRepo.findById(labelRecommendation.getId()).isPresent();
        if (!idExist) {
            throw new IllegalArgumentException("Label Recommendation ID '" + labelRecommendation.getId() + "' does not exist.");
        }
        labelRecommendationRepo.save(labelRecommendation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabelRecommendation(@PathVariable Integer id) {
        Optional<LabelRecommendation> labelRecommendation = labelRecommendationRepo.findById(id);
        if (!labelRecommendation.isPresent()) {
            throw new IllegalArgumentException("Label Recommendation ID '" + id + "' does not exist.");
        }
        labelRecommendationRepo.deleteById(id);
    }

}
