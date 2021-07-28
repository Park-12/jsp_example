package com.sbs.exam.exam1.http.dto;

import java.util.Map;

import com.sbs.exam.exam1.util.Util;

public class ResultData {
	private String msg;
	private String resultCode;
	private Map<String, Object> body ;
	
	private ResultData() {
		
	}

	public String getMsg() {
		return msg;
	}

	public String getResultCode() {
		return resultCode;
	}

	public Map<String, Object> getBody() {
		return body;
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return !isSuccess();
	}

	public static ResultData from(String resultCode, String msg, Object... bodyArgs) {
		ResultData rd = new ResultData();
		
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.body = Util.mapOf(bodyArgs);
		
		return rd;
	}

}
