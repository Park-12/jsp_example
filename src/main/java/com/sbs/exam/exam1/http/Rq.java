package com.sbs.exam.exam1.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.exam.exam1.dto.Member;
import com.sbs.exam.exam1.util.Ut;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Rq {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	@Getter
	private boolean isInvalid = false;
	@Getter
	private String actionMethodName;
	@Getter
	private String controllerName;
	@Getter
	private String controllerTypeName;
	
	@Getter
	@Setter
	private boolean isLogined = false;
	
	@Getter
	@Setter
	private int loginedMemberId = 0;
	
	@Getter
	@Setter
	private Member loginedMember = null;
	
	public boolean isNotLogined() {
		return isLogined == false;
	}

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
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
			isInvalid = true;
			return;
		}

		int controllerTypeNameIndex = 2;
		int controllerNameIndex = 3;
		int actionMethodNameIndex = 4;

		this.controllerTypeName = requestUriBits[controllerTypeNameIndex];
		this.controllerName = requestUriBits[controllerNameIndex];
		this.actionMethodName = requestUriBits[actionMethodNameIndex];

	}

	public void print(String str) {
		try {
			resp.getWriter().append(str);
		} catch (IOException e) {
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

	public String getParam(String paramName, String defaultValue) {
		String paramValue = req.getParameter(paramName);

		if (paramValue == null) {
			return defaultValue;
		}

		return paramValue;
	}

	public int getIntParam(String paramName, int defaultValue) {
		String paramValue = req.getParameter(paramName);

		if (paramValue == null) {
			return defaultValue;
		}

		try {
			return Integer.parseInt(paramValue);
		} catch (NumberFormatException e) {
			return defaultValue;
		}

	}

	public void printf(String format, Object... args) {
		print(Ut.f(format, args));

	}

	public void historyBack(String msg) {
		println("<script>");
		if (msg != null && msg.trim().length() > 0) {
			printf("alert('%s');\n", msg.trim());
		}
		println("history.back();");
		println("</script>");
	}

	public void println(Object obj) {
		println(obj.toString());
	}

	public void setAttr(String attrName, Object attrValue) {
		req.setAttribute(attrName, attrValue);
	}

	public void replace(String msg, String redirectUri) {
		println("<script>");
		if (msg != null && msg.trim().length() > 0) {
			printf("alert('%s');\n", msg.trim());
		}
		printf("location.replace('%s');\n", redirectUri);
		println("</script>");
	}

	public void setSessionAttr(String attrName, String attrValue) {
		req.getSession().setAttribute(attrName, attrValue);
	}
	
	public void removeSessionAttr(String attrName) {
		req.getSession().removeAttribute(attrName);
	}
	
	public <T> T getSessionAttr(String attrName, T defaultValue) {
		if (req.getSession().getAttribute(attrName) == null) {
			return defaultValue;
		}
		
		return (T) req.getSession().getAttribute(attrName);
	}

	public String getActionPath() {
		return "/" + controllerTypeName + "/" + controllerName + "/" + actionMethodName;
	}
	
	public String getAttr(String attrName, String defaultValue) {
		String attrValue = (String)req.getAttribute(attrName);

		if (attrValue == null) {
			return defaultValue;
		}

		return attrValue;
	}
	
	public int getIntAttr(String attrName, int defaultValue) {
		// Integer -> null 허용
		Integer attrValue = (Integer)req.getAttribute(attrName);

		if (attrValue == null) {
			return defaultValue;
		}

		return attrValue;

	}
	
	// Map형태로 정리
	private Map<String, Object> getParamMap() {
		// Map 생성
		Map<String, Object> params = new HashMap<>();

		// req에서 이름만 뽑아오기
		Enumeration<String> parameterNames = req.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			Object paramValue = req.getParameter(paramName);

			params.put(paramName, paramValue);
		}

		return params;
	}
	
	public String getParamMapJsonStr() {
		return Ut.toJson(getParamMap(), "");
	}
	
	private Map<String, Object> rqBaseTypeAttrMapStr() {
		Map<String, Object> attrs = new HashMap<>();

		Enumeration<String> attrNames = req.getAttributeNames();

		while (attrNames.hasMoreElements()) {
			String attrName = attrNames.nextElement();
			Object attrValue = req.getAttribute(attrName);
			
			if (attrName.equals("rq")) {
				continue;
			}
			
			// articles 빼고
			if (Ut.isBaseType(attrValue) == false) {
				continue;
			}

			attrs.put(attrName, attrValue);
		}

		return attrs;
	}
	
	public String getBaseTypeAttrMapJsonStr() {
		return Ut.toJson(rqBaseTypeAttrMapStr(), "");
	}

}
