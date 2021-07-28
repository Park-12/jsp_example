package com.sbs.exam.exam1.http.controller;

import java.util.List;

import com.sbs.exam.exam1.dto.Article;
import com.sbs.exam.exam1.dto.ResultData;
import com.sbs.exam.exam1.http.Rq;
import com.sbs.exam.exam1.http.container.Container;
import com.sbs.exam.exam1.http.service.ArticleService;

public class UsrArticleController extends Controller {
	private ArticleService articleService = Container.articleService;
	
	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "write":
			actionShowWrite(rq);
			break;
		case "doWrite":
			actionDoWrite(rq);
			break;
		case "list":
			actionShowList(rq);
			break;
		}
	}

	private void actionShowList(Rq rq) {
		List<Article> articles = articleService.getForPrintArticles();
		
		rq.setAttr("articles", articles);
		
		rq.jsp("usr/article/list");
	}

	private void actionDoWrite(Rq rq) {
		String title = rq.getParam("title", "");
		String body = rq.getParam("body", "");
		
		if (title.length() == 0) {
			rq.historyBack("title을 입력해주세요.");
			return;
		}
		
		if (body.length() == 0) {
			rq.historyBack("body를 입력해주세요.");
			return;
		}
		
		ResultData writeRd = articleService.write(title, body);
		
		rq.printf(writeRd.getMsg());
	}

	private void actionShowWrite(Rq rq) {
		rq.jsp("usr/article/write");
	}

}
