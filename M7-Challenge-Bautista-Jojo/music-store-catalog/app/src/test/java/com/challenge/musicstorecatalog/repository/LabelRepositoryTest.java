package com.challenge.musicstorecatalog.repository;

import com.challenge.musicstorecatalog.model.Label;
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
public class LabelRepositoryTest {
    @Autowired
    LabelRepository labelRepository;

    @Before
    public void setUp() throws Exception {
        labelRepository.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteLabel() {
        // Arrange
        Label label = new Label();
        label.setName("Universal Records");
        label.setWebsite("www.universalmusic.com");

        // Act
        label = labelRepository.save(label);
        Label whatIGot = labelRepository.findById(label.getId()).get();

        // Assert
        assertEquals(label, whatIGot);

        // Arrange and Act
        labelRepository.deleteById(label.getId());
        Optional<Label> deletedLabel = labelRepository.findById(label.getId());

        // Assert
        assertFalse(deletedLabel.isPresent());
    }

    @Test
    public void shouldFindAllLabel() {
        // Arrange
        Label label1 = new Label();
        label1.setName("EMI Records");
        label1.setWebsite("https://mltr.dk");

        Label label2 = new Label();
        label2.setName("Sony Music");
        label2.setWebsite("https://sony.com/music/mltr");

        // Act
        label1 = labelRepository.save(label1);
        label2 = labelRepository.save(label2);
        List<Label> allLabels = new ArrayList<>(Arrays.asList(label1, label2));

        List<Label> foundLabels = labelRepository.findAll();

        // Assert
        assertEquals(allLabels.size(), foundLabels.size());
    }

}