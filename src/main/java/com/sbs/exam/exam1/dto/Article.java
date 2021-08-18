package com.sbs.exam.exam1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int boardId;
	private int memberId;
	private String title;
	private String body;
	
	// 추가로 만들어진 거
	private String extra__writerName;
	// 쿼리로 한번에 가져오기 어려움 계산해야함
	private boolean extra__actorCanModify;
	private boolean extra__actorCanDelete;

	public String getTitleForPrint() {
		return title;
	}

	public String getBodySummaryForPrint() {
		return body;
	}
}
