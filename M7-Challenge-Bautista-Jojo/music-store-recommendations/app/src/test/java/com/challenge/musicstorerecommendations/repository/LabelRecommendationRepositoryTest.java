package com.challenge.musicstorerecommendations.repository;

import com.challenge.musicstorerecommendations.model.LabelRecommendation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LabelRecommendationRepositoryTest {
    @Autowired
    LabelRecommendationRepository labelRecommendationRepo;

    @Before
    public void setUp() throws Exception {
        labelRecommendationRepo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteLabelRecommendation() {
        // Arrange
        LabelRecommendation labelRec = new LabelRecommendation();
        labelRec.setLabelId(1);
        labelRec.setUserId(1);
        labelRec.setLiked(true);

        // Act
        labelRec = labelRecommendationRepo.save(labelRec);
        LabelRecommendation whatIGot = labelRecommendationRepo.findById(labelRec.getId()).get();

        // Assert
        assertEquals(labelRec, whatIGot);

        // Arrange and Act
        labelRecommendationRepo.deleteById(labelRec.getId());
        Optional<LabelRecommendation> deletedLabelRecommendation = labelRecommendationRepo.findById(labelRec.getId());

        // Assert
        assertFalse(deletedLabelRecommendation.isPresent());
    }

    @Test
    public void shouldFindAllLabelRecommendation() {
        // Arrange
        LabelRecommendation labelRec1 = new LabelRecommendation();
        labelRec1.setLabelId(1);
        labelRec1.setUserId(1);
        labelRec1.setLiked(true);

        LabelRecommendation labelRec2 = new LabelRecommendation();
        labelRec2.setLabelId(1);
        labelRec2.setUserId(2);
        labelRec2.setLiked(true);

        // Act
        labelRec1 = labelRecommendationRepo.save(labelRec1);
        labelRec2 = labelRecommendationRepo.save(labelRec2);
        List<LabelRecommendation> allLabelsRecommendation = new ArrayList<>(Arrays.asList(labelRec1, labelRec2));

        List<LabelRecommendation> foundLabelsRecommendation = labelRecommendationRepo.findAll();

        // Assert
        assertEquals(allLabelsRecommendation.size(), foundLabelsRecommendation.size());
    }
}