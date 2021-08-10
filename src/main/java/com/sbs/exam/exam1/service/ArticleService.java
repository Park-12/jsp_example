package com.sbs.exam.exam1.service;

import java.util.List;

import com.sbs.exam.exam1.dto.Article;
import com.sbs.exam.exam1.dto.ResultData;
import com.sbs.exam.exam1.http.container.Container;
import com.sbs.exam.exam1.repository.ArticleRepository;
import com.sbs.exam.exam1.util.Util;

public class ArticleService {
	private ArticleRepository articleRepository = Container.articleRepository;
	
	public ResultData write(String title, String body) {
		int id = articleRepository.write(title, body);
		
		return ResultData.from("S-1", Util.f("%d번 게시물이 생성되었습니다.", id), "id", id);
	}

	public List<Article> getForPrintArticles() {
		return articleRepository.getForPrintArticles();
	}

	public Article getForPrintArticleById(int id) {
		return articleRepository.getForPrintArticleById(id);
	}

}
