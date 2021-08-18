package com.sbs.exam.exam1.http.controller;

import java.util.List;

import com.sbs.exam.exam1.dto.Article;
import com.sbs.exam.exam1.dto.ResultData;
import com.sbs.exam.exam1.http.Rq;
import com.sbs.exam.exam1.http.container.Container;
import com.sbs.exam.exam1.service.ArticleService;
import com.sbs.exam.exam1.util.Ut;

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
		case "detail":
			actionShowDetail(rq);
			break;
		case "doDelete":
			actionDoDelete(rq);
			break;
		case "modify":
			actionShowModify(rq);
			break;
		case "doModify":
			actionDoModify(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	private void actionDoModify(Rq rq) {
		int id = rq.getIntParam("id", 0);
		String title = rq.getParam("title", "");
		String body = rq.getParam("body", "");
		String redirectUri = rq.getParam("redirectUri", "../article/detail");

		if (title.length() == 0) {
			rq.historyBack("title을 입력해주세요.");
			return;
		}

		if (body.length() == 0) {
			rq.historyBack("body를 입력해주세요.");
			return;
		}
		Article article = articleService.getForPrintArticleById(rq.getLoginedMember(), id);

		if (article == null) {
			rq.historyBack("존재하지 않는 게시물 입니다.");
			return;
		}

		// 수정 할 수 있는지 확인 후 actorCanModifyRd에 넣기
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMember(), article);

		// 실패할 경우
		if (actorCanModifyRd.isFail()) {
			rq.historyBack(actorCanModifyRd.getMsg());
			return;
		}

		ResultData modifyRd = articleService.modify(id, title, body);

		rq.replace(modifyRd.getMsg(), redirectUri);
	}

	private void actionShowModify(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			rq.historyBack("id을 입력해주세요.");
			return;
		}

		Article article = articleService.getForPrintArticleById(rq.getLoginedMember(), id);

		// 수정 할 수 있는지 확인 후 actorCanModifyRd에 넣기
		ResultData actorCanModifyRd = articleService.actorCanModify(rq.getLoginedMember(), article);

		// 실패할 경우
		if (actorCanModifyRd.isFail()) {
			rq.historyBack(actorCanModifyRd.getMsg());
			return;
		}

		if (article == null) {
			rq.historyBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
			return;
		}

		rq.setAttr("article", article);

		rq.jsp("usr/article/modify");
	}

	private void actionDoDelete(Rq rq) {
		int id = rq.getIntParam("id", 0);
		String redirectUri = rq.getParam("redirectUri", "../article/list");

		if (id == 0) {
			rq.historyBack("id을 입력해주세요.");
			return;
		}

		Article article = articleService.getForPrintArticleById(rq.getLoginedMember(), id);

		if (article == null) {
			rq.historyBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
			return;
		}

		ResultData actorCanDeleteRd = articleService.actorCanDelete(rq.getLoginedMember(), article);

		if (actorCanDeleteRd.isFail()) {
			rq.historyBack(actorCanDeleteRd.getMsg());
			return;
		}

		articleService.delete(id);

		rq.replace(Ut.f("%d번 게시물을 삭제하였습니다.", id), redirectUri);

	}

	private void actionShowDetail(Rq rq) {
		int id = rq.getIntParam("id", 0);

		if (id == 0) {
			rq.historyBack("id을 입력해주세요.");
			return;
		}

		Article article = articleService.getForPrintArticleById(rq.getLoginedMember(), id);

		if (article == null) {
			rq.historyBack(Ut.f("%d번 게시물이 존재하지 않습니다.", id));
			return;
		}

		rq.setAttr("article", article);

		rq.jsp("usr/article/detail");
	}

	private void actionShowList(Rq rq) {
		// 현재 로그인한 회원을 위해 출력용으로 가져오기
		List<Article> articles = articleService.getForPrintArticles(rq.getLoginedMember());

		rq.setAttr("articles", articles);

		rq.jsp("usr/article/list");
	}

	private void actionDoWrite(Rq rq) {
		int boardId = 1;
		int memberId = rq.getLoginedMemberId();
		String title = rq.getParam("title", "");
		String body = rq.getParam("body", "");
		String redirectUri = rq.getParam("redirectUri", "../article/list");

		if (title.length() == 0) {
			rq.historyBack("title을 입력해주세요.");
			return;
		}

		if (body.length() == 0) {
			rq.historyBack("body를 입력해주세요.");
			return;
		}

		ResultData writeRd = articleService.write(boardId, memberId, title, body);
		int id = (int) writeRd.getBody().get("id");

		redirectUri = redirectUri.replace("[NEW_ID]", id + "");

		rq.replace(writeRd.getMsg(), redirectUri);
	}

	private void actionShowWrite(Rq rq) {
		rq.jsp("usr/article/write");
	}

}
