package com.sbs.exam.exam1.http.controller;

import com.sbs.exam.exam1.http.Rq;
import com.sbs.exam.exam1.http.dto.ResultData;
import com.sbs.exam.exam1.http.service.ArticleService;

public class UsrArticleController extends Controller {
	private ArticleService articleService;
	
	public UsrArticleController() {
		articleService = new ArticleService();
	}

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "write":
			actionShowWrite(rq);
		case "doWrite":
			actionDoWrite(rq);
		case "list":
			actionShowList(rq);
		}
	}

	private void actionShowList(Rq rq) {
		// TODO Auto-generated method stub
		
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
