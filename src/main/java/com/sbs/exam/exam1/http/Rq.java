package com.sbs.exam.exam1.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Rq {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private boolean isInvalid = false;
	private String actionMethodName;
	private String controllerName;
	private String controllerTypeName;
	

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.setCharacterEncoding("UTF-8");

		resp.setContentType("text/html; charset=UTF-8");
		
		this.req = req;
		this.resp = resp;

		// /jsp_example/usr/article/ddd
		String requestUri = req.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		int minBitsCount = 5;

		if (requestUriBits.length < minBitsCount) {
			isInvalid  = true;
			return;
		}
		
		int controllerTypeNameIndex = 2;
		int controllerNameIndex = 3;
		int actionMethodNameIndex = 4;
		
		this.controllerTypeName = requestUriBits[controllerTypeNameIndex];
		this.controllerName = requestUriBits[controllerNameIndex];
		this.actionMethodName = requestUriBits[actionMethodNameIndex];

	}

	public HttpServletRequest getReq() {
		return req;
	}

	public HttpServletResponse getResp() {
		return resp;
	}

	public boolean isInvalid() {
		return isInvalid;
	}

	public String getActionMethodName() {
		return actionMethodName;
	}

	public String getControllerName() {
		return controllerName;
	}

	public String getControllerTypeName() {
		return controllerTypeName;
	}

	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void println(String str) {
		print(str + "\n");
	}

	public void jsp(String jspPath) {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/" + jspPath + ".jsp");
		try {
			requestDispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
