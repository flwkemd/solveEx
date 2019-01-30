package com.solve.spring.enums;
 
public enum EnumCategoryGroup {
	전체("전체", ""), 대형마트("대형마트", "MT1"), 편의점("편의점", "CS2"), 어린이집("어린이집", "PS3"), 학교("학교", "AC5"), 주차장("주차장", "PK6"),
	주유소("주유소", "OL7"), 지하철역("지하철역", "SW8"), 은행("은행", "BK9"), 문화시설("문화시설", "PS3"), 중개업소("중개업소", "AG2"), 공공기관("공곤기관", "PO3"),
	관광명소("관광명소", "AT4"), 숙박("숙박", "AD5"), 음식점("음식점", "FD6"), 카페("카페", "CE7"), 병원("병원", "HP8"), 약국("약국", "PM9");
	
	private String desc;
	private String code;
 
	private EnumCategoryGroup(String desc, String code) {
		this.desc = desc;
		this.code = code;
	}
 
	public String getDesc() {
		return desc;
	}
 
	public String getCode() {
		return code;
	}
 
	public static EnumCategoryGroup getByCode(String code) {
		EnumCategoryGroup returnValue = null;
 
		for (EnumCategoryGroup temp : EnumCategoryGroup.values()) {
			if (temp.getCode().equals(code)) {
				returnValue = temp;
				break;
			}
		}
 
		return returnValue;
	}
}
