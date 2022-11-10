package com.challenge.musicstorecatalog.repository;

import com.challenge.musicstorecatalog.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {

}
