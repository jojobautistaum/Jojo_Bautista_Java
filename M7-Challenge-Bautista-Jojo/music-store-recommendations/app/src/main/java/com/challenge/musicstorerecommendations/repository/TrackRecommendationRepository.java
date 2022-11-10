package com.challenge.musicstorerecommendations.repository;

import com.challenge.musicstorerecommendations.model.TrackRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface TrackRecommendationRepository extends JpaRepository<TrackRecommendation, Integer> {
}
