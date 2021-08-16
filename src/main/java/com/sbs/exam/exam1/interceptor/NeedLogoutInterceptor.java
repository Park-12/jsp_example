package com.sbs.exam.exam1.interceptor;

import com.sbs.exam.exam1.http.Rq;

public class NeedLogoutInterceptor extends Interceptor {

	@Override
	public boolean runBeforeAction(Rq rq) {
		return true;
	}

}