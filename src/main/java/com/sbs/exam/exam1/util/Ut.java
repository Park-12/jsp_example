package com.sbs.exam.exam1.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbs.exam.exam1.dto.Member;

public class Ut {

	public static String f(String string, Object... args) {
		return String.format(string, args);
	}

	public static Map<String, Object> mapOf(Object... args) {
		if (args.length % 2 != 0) {
			throw new IllegalArgumentException("인자를 짝수개 입력해주세요.");
		}
		
		int size = args.length / 2;
		
		Map<String, Object> map = new LinkedHashMap<>();
		
		for (int i = 0; i < size; i++) {
			int keyIndex = i * 2;
			int valueIndex = keyIndex + 1;
			
			String key;
			Object value;
			
			try {
				key = (String) args[keyIndex];
			} catch (ClassCastException e) {
				throw new IllegalArgumentException("키는 String으로 입력해야합니다." + e.getMessage());
			}
			
			value = args[valueIndex];
			
			map.put(key, value);
		}
		
		return map;
	}

	public static String toJson(Object obj, String defaultValue) {
		ObjectMapper om = new ObjectMapper();
		
		try {
			return om.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return defaultValue;
		}
	}

}
