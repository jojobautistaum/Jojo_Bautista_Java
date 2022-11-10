package com.challenge.musicstorerecommendations.repository;

import com.challenge.musicstorerecommendations.model.AlbumRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRecommendationRepository extends JpaRepository<AlbumRecommendation, Integer> {
}
