package com.solve.spring.service;

import java.util.List;

import com.solve.spring.model.SearchRank;

public interface SearchRankService {
	
	List<SearchRank> findAllByOrderCountDesc();
	
	SearchRank findBySearchword(String searchword);

	SearchRank save(SearchRank searchRank);

}
