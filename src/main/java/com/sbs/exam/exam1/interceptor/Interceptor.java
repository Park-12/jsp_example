package com.sbs.exam.exam1.interceptor;

import com.sbs.exam.exam1.http.Rq;

// action 실행하기 전 수행되는 관문
public abstract class Interceptor {
	abstract public boolean runBeforeAction(Rq rq);
}
