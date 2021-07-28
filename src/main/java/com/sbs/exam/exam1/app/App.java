package com.sbs.exam.exam1.app;

import com.sbs.exam.exam1.http.container.Container;
import com.sbs.mysqlutil.MysqlUtil;

public class App {
	public static boolean isDevMode() {
		return true;
	}

	public static void init() {
		MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "jsp_example");
		MysqlUtil.setDevMode(isDevMode());
		
		Container.init();
	}

}
