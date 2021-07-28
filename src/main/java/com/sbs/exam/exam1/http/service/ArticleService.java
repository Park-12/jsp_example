package com.sbs.exam.exam1.http.service;

import com.sbs.exam.exam1.http.dto.ResultData;
import com.sbs.exam.exam1.http.repository.ArticleRepostiory;
import com.sbs.exam.exam1.util.Util;

public class ArticleService {
	private ArticleRepostiory articleRepostiory;
	
	public ArticleService() {
		articleRepostiory = new ArticleRepostiory();
	}

	public ResultData write(String title, String body) {
		int id = articleRepostiory.write(title, body);
		
		return ResultData.from("S-1", Util.f("%d번 게시물이 생성되었습니다.", id), "id", id);
	}

}
