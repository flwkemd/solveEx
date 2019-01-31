package com.solve.spring.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "placeRank")
public class PlaceRank {

	@Id
	private String searchWord;

	@Column(nullable = false)
	private int cnt;

	public PlaceRank(String searchWord, int cnt) {
		super();
		this.searchWord = searchWord;
		this.cnt = cnt;
	}
}
