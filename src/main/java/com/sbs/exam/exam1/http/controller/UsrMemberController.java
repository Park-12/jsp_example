package com.sbs.exam.exam1.http.controller;

import com.sbs.exam.exam1.dto.Member;
import com.sbs.exam.exam1.dto.ResultData;
import com.sbs.exam.exam1.http.Rq;
import com.sbs.exam.exam1.http.container.Container;
import com.sbs.exam.exam1.service.MemberService;
import com.sbs.exam.exam1.util.Ut;

public class UsrMemberController extends Controller {

	private MemberService memberService = Container.memberService;

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "login":
			actionShowLogin(rq);
			break;
		case "doLogin":
			actionDoLogin(rq);
			break;
		case "doLogout":
			actionDoLogout(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	private void actionDoLogout(Rq rq) {
		rq.removeSessionAttr("loginedMemberJson");
		rq.replace(null, "../article/list");
	}

	private void actionDoLogin(Rq rq) {
		String loginId = rq.getParam("loginId", "");
		String loginPw = rq.getParam("loginPw", "");
		String redirectUri = rq.getParam("redirectUri", "../article/list");
		
		if (loginId.length() == 0) {
			rq.historyBack("loginId(을)를 입력해주세요.");
			return;
		}

		if (loginPw.length() == 0) {
			rq.historyBack("loginPw(을)를 입력해주세요.");
			return;
		}

		ResultData loginRd = memberService.login(loginId, loginPw);
		
		if (loginRd.isFail()) {
			rq.historyBack(loginRd.getMsg());
		}
		
		Member member = (Member) loginRd.getBody().get("member");
		
		rq.setSessionAttr("loginedMemberJson", Ut.toJson(member, ""));
		rq.replace(loginRd.getMsg(), redirectUri);
	}

	private void actionShowLogin(Rq rq) {
		rq.jsp("usr/member/login");
	}

}
