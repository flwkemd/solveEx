package com.solve.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.solve.spring.model.SearchRank;
import com.solve.spring.repository.SearchRankRepository;

@Service
public class SearchRankServiceImpl implements SearchRankService {

	@Autowired
	private SearchRankRepository searchRankRepository;
 
	@Override
	public SearchRank findBySearchword(String searchword) {
		SearchRank searchOne = searchRankRepository.findBySearchword(searchword);
		return searchOne;
	}

	@Override
	public SearchRank save(SearchRank searchRank) {
		return searchRankRepository.save(searchRank);
	}
	
	@Override
	public List<SearchRank> findAllByOrderCountDesc() {
		return searchRankRepository.findAll(orderBycntDesc());
	}

	private Sort orderBycntDesc() {
		return new Sort(Sort.Direction.DESC, "count");
	}

	@Override
	public SearchRank updateCount(SearchRank searchRank) {
		
		searchRank.setCount(searchRank.getCount()+1);
		
		return searchRankRepository.save(searchRank);
	}

}

