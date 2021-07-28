package com.sbs.exam.exam1.http.controller;

import com.sbs.exam.exam1.http.Rq;

public class UsrArticleController extends Controller {

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
		// TODO Auto-generated method stub
		
	}

	private void actionShowWrite(Rq rq) {
		rq.jsp("usr/article/write");
	}

}
