package com.solve.spring.service;

import java.util.List;

import com.solve.spring.model.PlaceRank;

public interface PlaceRankService {
	 void save(PlaceRank placeRank);
	
	 PlaceRank getPlace(String searchWord);

	 public List<PlaceRank> findAll();
}