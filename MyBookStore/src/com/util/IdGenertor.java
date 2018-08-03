package com.util;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class IdGenertor {
	public static String genGUID(){
		return UUID.randomUUID().toString();
	}
	public static String genOrdernum(){
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String s1 = df.format(now);// 20141026+纳秒
		return s1+System.nanoTime();
	}
}
