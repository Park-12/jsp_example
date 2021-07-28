package com.sbs.exam.exam1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.exam.exam1.http.Rq;
import com.sbs.exam.exam1.http.controller.Controller;
import com.sbs.exam.exam1.http.controller.UsrArticleController;
import com.sbs.mysqlutil.MysqlUtil;

@WebServlet("/usr/*")
public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Rq rq = new Rq(req, resp);
		
		if (rq.isInvalid()) {
			rq.print("올바른 요청이 아닙니다.");
		}
		
		Controller controller = null;
		
		switch (rq.getControllerTypeName()) {
		case "usr":
			switch (rq.getControllerName()) {
			case "article":
				controller = new UsrArticleController();
				break;
			}
			break;
		}
		
		if (controller != null) {
			MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jsp_example");
			MysqlUtil.setDevMode(true);
			
			controller.performAction(rq);

			MysqlUtil.closeConnection();
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}