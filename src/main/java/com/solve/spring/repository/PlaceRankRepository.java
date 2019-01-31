package com.solve.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solve.spring.model.PlaceRank;

public interface PlaceRankRepository extends JpaRepository<PlaceRank, String> {

	PlaceRank findOne(String searchWord);
	List<PlaceRank> findAll(String searchWord);
	
}
