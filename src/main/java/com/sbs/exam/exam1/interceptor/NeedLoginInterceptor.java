package com.sbs.exam.exam1.interceptor;

import com.sbs.exam.exam1.http.Rq;

public class NeedLoginInterceptor extends Interceptor {

	// 로그인 필요 없는 페이지
	@Override
	public boolean runBeforeAction(Rq rq) {
		switch (rq.getActionPath()) {
		case "/usr/article/list":
		case "/usr/article/detail":
		case "/usr/home/main":
		case "/usr/member/login":
		case "/usr/member/doLogin":
		case "/usr/member/doLogout":
		case "/usr/member/doJoin":
		case "/usr/member/findLoginId":
		case "/usr/member/doFindLoginId":
		case "/usr/member/findLoginPw":
		case "/usr/member/doFindLoginPw":
			return true;
		}
		
		if (rq.isNotLogined()) {
			rq.historyBack("로그인 후 이용해주세요.");
			return false;
		}

		return true;
	}

}
