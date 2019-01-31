package com.solve.spring.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solve.spring.enums.EnumCategoryGroup;
import com.solve.spring.model.PlaceRank;
import com.solve.spring.service.ApiService;
import com.solve.spring.service.PlaceRankService;

@RestController
@RequestMapping("ajax/")
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@Autowired
	ApiService apiService;
	
	@Autowired
	PlaceRankService placeRankService;
	
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
			@RequestParam("searchWord") String searchWord,
			@RequestParam(name = "category", defaultValue = "") String category) {
		
		if(placeRankService.getPlace(searchWord) != null) {
			placeRankService.save(new PlaceRank(searchWord, placeRankService.getPlace(searchWord).getCnt()+1));
			
		}else {
			placeRankService.save(new PlaceRank(searchWord, 1));
		}
		
		Map<String, Object> result = apiService.placeSearch(searchWord, category);
		
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
