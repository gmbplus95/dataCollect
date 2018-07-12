package com.ifisolution.util;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtils {
	private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);
	private static ObjectMapper om;

	public static String trim(String str) {
		if (str != null) {
			str = str.trim();
		}
		return str;
	}
	
	public static String[] trimData(String[] arrStr){
		String[] arrR = new String[arrStr.length];
		for(int i=0; i<arrStr.length; i++){
			arrR[i] = new String(arrStr[i].trim());
		}
		return arrR;
	}

	public static String changeOutput(String name) {
		String[] arr = name.split("_");
		String v = "";
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				v += arr[i];
			} else {
				v += upFirstCase(arr[i]);
			}
		}
		return v;
	}

	public static String upFirstCase(String str) {
		String str1 = str.substring(0, 1);
		String str2 = str.substring(1, str.length());
		String result = str1.toUpperCase() + str2;
		return result;
	}

	public static ObjectMapper getObjectMapper() {
		if (om == null) {
			om = new ObjectMapper();
		}
		return om;
	}

	public static String getJson(Object object) {
		ObjectMapper mapper = getObjectMapper();
		if (object != null) {
			try {
				return mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				logger.error("Get JSON string error: " + e.getMessage());
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static String floatToString(Float f) {
		if (f != null) {
			return new BigDecimal(f).toString();
		}
		return null;
	}
	
	public static String objectToString(Object f) {
		if (f != null) {
			return f+"";
		}
		return null;
	}
	
	public static boolean validString(String temp) {
        if (temp == null || temp.trim().equals("")) {
            return false;
        }
        return true;
    }
}
