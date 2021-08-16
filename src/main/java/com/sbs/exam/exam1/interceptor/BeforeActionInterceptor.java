package com.sbs.exam.exam1.interceptor;

import com.sbs.exam.exam1.dto.Member;
import com.sbs.exam.exam1.http.Rq;
import com.sbs.exam.exam1.util.Ut;

public class BeforeActionInterceptor extends Interceptor {

	@Override
	public boolean runBeforeAction(Rq rq) {
		// 로그인 = X
		boolean isLogined = false;
		// 로그인된 아이디 = 0
		int loginedMemberId = 0;
		// 로그인된 회원 X
		Member loginedMember = null;
		
		// 세션에서 정보 꺼내오기
		String loginedMemberJson = rq.getSessionAttr("loginedMemberJson", "");
		
		// 정보가 있으면
		if (loginedMemberJson.length() > 0) {
			isLogined = true;
			
			// 객체화시켜서 loginedMember 넣기
			loginedMember = Ut.toObjFromJson(loginedMemberJson, Member.class);
			
			loginedMemberId = loginedMember.getId();
		}
		
		// 위의 정보들을 rq에 넣기
		rq.setAttr("isLogined", isLogined);
		rq.setAttr("loginedMember", loginedMember);
		rq.setAttr("loginedMemberId", loginedMemberId);
		
		return true;
	}
	
}
