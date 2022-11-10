package com.challenge.musicstorerecommendations.repository;

import com.challenge.musicstorerecommendations.model.ArtistRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRecommendationRepository extends JpaRepository<ArtistRecommendation, Integer> {

}
