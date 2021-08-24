package com.sbs.exam.exam1.http.controller;

import com.sbs.exam.exam1.dto.Member;
import com.sbs.exam.exam1.dto.ResultData;
import com.sbs.exam.exam1.http.Rq;
import com.sbs.exam.exam1.http.container.Container;
import com.sbs.exam.exam1.service.MemberService;
import com.sbs.exam.exam1.util.Ut;

public class UsrHomeController extends Controller {

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "main":
			actionShowMain(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	private void actionShowMain(Rq rq) {
		rq.jsp("usr/home/main");
	}

}
