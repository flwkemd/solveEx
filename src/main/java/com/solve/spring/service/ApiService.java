package com.solve.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.solve.spring.enums.EnumCategoryGroup;
import com.solve.spring.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiService {
	private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

	private static final String API_REST_API_KEY = "9ec3ac48e69bf6523be0fab261bcf934";
	private static final String API_PLACE_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

	public Map<String, Object> placeSearch(String searchWord, String category) {
		
		//
		final String URL = API_PLACE_URL + "?query="+searchWord+ "&category_group_code=" + category;
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "KakaoAK " + API_REST_API_KEY);
		Map<String, String> params = new HashMap<>();
		params.put("query", searchWord);
		String jsonString = null;
		Map<String, Object> resultData = null;
		try {
			jsonString = Utils.getHttpPOST2String(URL, headers, params, false);
			ObjectMapper mapper = new ObjectMapper();
			resultData = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
			});

			logger.debug(URL + " - get API Info : " + jsonString);
			// resultData = JsonUtils.readJsonToStringObjectUnparse(jsonString);

		} catch (Exception e) {
			logger.info(URL + " - get API Exception : " + jsonString);
			e.printStackTrace();
		}
		return resultData;
	}

	/**
	* 책 정보 가져오기
	* 
	* 데이터 참조 :
	* https://developers.kakao.com/docs/restapi/search#%EC%B1%85-%EA%B2%80%EC%83%89
	* 
	* @param place_name
	* @return JSON -> Map<String,Object>
	*/
	public Map<String, Object> getPlace(String place_name) {
		Map<String, Object> place = null;
		Map<String, Object> json = this.placeSearch(place_name, EnumCategoryGroup.전체.getCode());
		int cnt = (Integer) ((Map) json.get("meta")).get("total_count");
		if (cnt > 0) {
			place = (Map) ((List) json.get("documents")).get(0);
		}
		return place;
	}

}