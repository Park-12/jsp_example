package com.sbs.exam.exam1.http.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.exam.exam1.http.Rq;
import com.sbs.exam.exam1.http.container.Container;
import com.sbs.exam.exam1.http.controller.Controller;
import com.sbs.exam.exam1.interceptor.Interceptor;
import com.sbs.mysqlutil.MysqlUtil;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Rq rq = new Rq(req, resp);
		
		if (rq.isInvalid()) {
			rq.print("올바른 요청이 아닙니다.");
			return;
		}
		
		
		if (runInterceptors(rq) == false) {
			return;
		}
		
		Controller controller = null;
		
		switch (rq.getControllerTypeName()) {
		case "usr":
			switch (rq.getControllerName()) {
			case "article":
				controller = Container.usrArticleController;
				break;
			case "member":
				controller = Container.usrMemberController;
				break;
			}
			break;
		}
		
		if (controller != null) {
			controller.performAction(rq);

			MysqlUtil.closeConnection();
		}
		else {
			rq.print("올바른 요청이 아닙니다.");
		}
		
	}

	private boolean runInterceptors(Rq rq) {
		
		if (Container.beforeActionInterceptor.runBeforeAction(rq) == false) {
			return false;
		}
		
		if (Container.needLoginInterceptor.runBeforeAction(rq) == false) {
			return false;
		}
		
		if (Container.needLogoutInterceptor.runBeforeAction(rq) == false) {
			return false;
		}
		
		return true;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}