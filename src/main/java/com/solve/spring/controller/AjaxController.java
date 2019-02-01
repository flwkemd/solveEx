package com.solve.spring.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solve.spring.enums.EnumCategoryGroup;
import com.solve.spring.model.SearchRank;
import com.solve.spring.service.ApiService;
import com.solve.spring.service.SearchRankService;

@RestController
@RequestMapping("ajax/")
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@Autowired
	ApiService apiService;
	
	@Autowired
	SearchRankService searchRankService;
	
	/**
	 * 장소 검색 restAPI
	 * 
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/placeSearch")
	public Map<String, Object> placeSearch(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("searchWord") String searchword,
			@RequestParam(name = "category", defaultValue = "") String category) {
		
		if(searchRankService.findBySearchword(searchword) == null) {
		SearchRank searchRank = new SearchRank();
		searchRank.setSearchword(searchword);
		searchRank.setCount(1);
		searchRankService.save(searchRank);
		}else {
			searchRankService.findBySearchword(searchword).setCount(searchRankService.findBySearchword(searchword).getCount()+1);
		}		
				
		Map<String, Object> result = apiService.placeSearch(searchword, category);
		
		return result;
	}

	/**
	 * 장소 정보 restAPI
	 * 
	 * @param req
	 * @param res
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getPlace/place_name/{place_name}", method = RequestMethod.GET)
	public Map<String, Object> getPlace(@PathVariable String place_name) {

		Map<String, Object> result = apiService.placeSearch(place_name, EnumCategoryGroup.전체.getCode());

		return result;
	}
}
