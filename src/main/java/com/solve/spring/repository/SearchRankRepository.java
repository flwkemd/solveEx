package com.solve.spring.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solve.spring.model.SearchRank;

@Repository
public interface SearchRankRepository extends CrudRepository<SearchRank, Long> {

	SearchRank findBySearchword(String searchword);

	List<SearchRank> findAll(Sort orderBycntDesc);
	
}
