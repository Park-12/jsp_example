package com.sbs.exam.exam1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shp.mysqlutil.MysqlUtil;
import com.shp.mysqlutil.SecSql;

@WebServlet("/usr/article/doWrite")
public class UsrArticleDoWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		
		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jsp_example");
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		SecSql sql = new SecSql();
		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", body = ?", body);
		int id = MysqlUtil.insert(sql);
		
		response.getWriter().append(id + "번 게시물이 생성되었습니다.");
		
		MysqlUtil.closeConnection();
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}