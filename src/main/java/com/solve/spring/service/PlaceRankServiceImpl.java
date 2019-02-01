package com.solve.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.solve.spring.model.PlaceRank;
import com.solve.spring.repository.PlaceRankRepository;

@Service
@ComponentScan("com.solve.spring.repository")
public class PlaceRankServiceImpl implements PlaceRankService {
	
	    PlaceRankRepository placeRankRepository = new PlaceRankRepository() {
			
			@Override
			public <S extends PlaceRank> Optional<S> findOne(Example<S> example) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <S extends PlaceRank> Page<S> findAll(Example<S> example, Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <S extends PlaceRank> boolean exists(Example<S> example) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public <S extends PlaceRank> long count(Example<S> example) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public <S extends PlaceRank> S save(S entity) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Optional<PlaceRank> findById(String id) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean existsById(String id) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void deleteById(String id) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void deleteAll(Iterable<? extends PlaceRank> entities) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void deleteAll() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void delete(PlaceRank entity) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public long count() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Page<PlaceRank> findAll(Pageable pageable) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <S extends PlaceRank> S saveAndFlush(S entity) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <S extends PlaceRank> List<S> saveAll(Iterable<S> entities) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public PlaceRank getOne(String id) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void flush() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public List<PlaceRank> findAllById(Iterable<String> ids) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <S extends PlaceRank> List<S> findAll(Example<S> example, Sort sort) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public <S extends PlaceRank> List<S> findAll(Example<S> example) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<PlaceRank> findAll(Sort sort) {
				return null;
			}
			
			@Override
			public List<PlaceRank> findAll() {
				List<PlaceRank> placeList = placeRankRepository.findAll();
				return placeList;
			}
			
			@Override
			public void deleteInBatch(Iterable<PlaceRank> entities) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void deleteAllInBatch() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public PlaceRank findOne(String searchWord) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<PlaceRank> findAll(String searchWord) {
				// TODO Auto-generated method stub
				return null;
			}
		};

	    @Override
	    public void save(PlaceRank placeRank) {
	    	placeRankRepository.save(placeRank);
	    }

	    @Override
		public PlaceRank getPlace(String searchWord) {
			return placeRankRepository.findOne(searchWord);
		}

		@Override
		public List<PlaceRank> findAll() {
			List<PlaceRank> placeList = placeRankRepository.findAll();
			return placeList;
		}

//		@Override
//		public List<PlaceRank> findAllByOrdercntDesc() {
//			return placeRankRepository.findAll(orderBycntDesc());
//		}
		
//		private Sort orderBycntDesc() {
//			return new Sort(Sort.Direction.DESC, "cnt");
//		}

	    


}
