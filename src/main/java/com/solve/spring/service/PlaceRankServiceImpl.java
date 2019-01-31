package com.solve.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.solve.spring.model.PlaceRank;
import com.solve.spring.repository.PlaceRankRepository;

@Service
public class PlaceRankServiceImpl implements PlaceRankService {
	
	    @Autowired
	    private PlaceRankRepository placeRankRepository;

	    @Override
	    public void save(PlaceRank placeRank) {
	    	placeRankRepository.save(placeRank);
	    }

	    @Override
		public PlaceRank getPlace(String searchWord) {
			return placeRankRepository.findOne(searchWord);
		}

		@Override
		public List<PlaceRank> findAllByOrdercntDesc() {
			return placeRankRepository.findAll(orderBycntDesc());
		}
		
		private Sort orderBycntDesc() {
			return new Sort(Sort.Direction.DESC, "cnt");
		}

	    
	    
	    


}
