package com.challenge.musicstorecatalog.controller;

import com.challenge.musicstorecatalog.model.Label;
import com.challenge.musicstorecatalog.repository.LabelRepository;
import com.sun.xml.txw2.IllegalSignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Path;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;

@RestController
@RequestMapping(value="/label")
public class LabelController {

    @Autowired
    private LabelRepository labelRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Label getLabelById(@PathVariable Integer id) {
        Optional<Label> label = labelRepository.findById(id);
        if (label.isPresent()) {
            return label.get();
        }
        throw new IllegalArgumentException("Label ID '" + id + "' does not exist");
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Label createLabel(@RequestBody @Valid Label label) {
        return labelRepository.save(label);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@RequestBody @Valid Label label){
        if (label.getId() == null || label.getId() < 1) {
            throw new IllegalArgumentException("Label ID is required and must be greater than zero.");
        }

        boolean idExist = labelRepository.findById(label.getId()).isPresent();
        if(!idExist) {
            throw new IllegalArgumentException("Label ID '" + label.getId() + "' does not exist");
        }
        labelRepository.save(label);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Integer id) {
        Optional<Label> label = labelRepository.findById(id);
        if (!label.isPresent()) {
            throw new IllegalArgumentException("Label ID '" + id + "' does not exist");
        }
        labelRepository.deleteById(id);
    }
}
