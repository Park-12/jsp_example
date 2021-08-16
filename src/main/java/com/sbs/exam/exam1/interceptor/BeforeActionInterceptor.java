package com.sbs.exam.exam1.interceptor;

import com.sbs.exam.exam1.dto.Member;
import com.sbs.exam.exam1.http.Rq;
import com.sbs.exam.exam1.util.Ut;

public class BeforeActionInterceptor extends Interceptor {

	@Override
	public boolean runBeforeAction(Rq rq) {
		// 세션에서 정보 꺼내오기
		String loginedMemberJson = rq.getSessionAttr("loginedMemberJson", "");
		
		// 정보가 있으면
		if (loginedMemberJson.length() > 0) {
			rq.setLogined(true);

			// 객체화시켜서 loginedMember 넣기
			rq.setLoginedMember(Ut.toObjFromJson(loginedMemberJson, Member.class));
			
			rq.setLoginedMemberId(rq.getLoginedMember().getId());
		}
		
		rq.setAttr("rq", rq);
		
		return true;
	}
	
}
