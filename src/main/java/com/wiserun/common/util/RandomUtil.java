package com.wiserun.common.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomUtil {
	static String[] randomArray = new String[10];
	
	private static char[] captchars = new char[] { 'a', 'b', 'c', 'd', 'e',
		'2', '3', '4', '5', '6', '7', '8', 'g', 'f', 'y', 'n', 'm', 'n',
		'p', 'w', 'x' };
	
	static Map<String,String> randomMap = new HashMap<String,String>();
	
	public static Map<String, String> getRandomMap() {
		return randomMap;
	}

	public static void setRandomMap(Map<String, String> randomMap) {
		RandomUtil.randomMap = randomMap;
	}

	static {
		randomArray = getIntArray();
	}
	
	public synchronized static String getRandom(int len) {
		Random rd = new Random();
		String result = "";
		for (int i=0; i<len; i++) {
			result = result + randomArray[rd.nextInt(10)];
		}
		if (!randomMap.containsKey(result)) {
			randomMap.put(result, "");
		} else {
			return getRandom(len);
		}
		return result;
	}
	
	public synchronized static String getRandom2(int len) {
		Random rd = new Random();
		String result = "";
		for (int i=0; i<len; i++) {
			result = result + randomArray[rd.nextInt(10)];
		}
		if (!randomMap.containsKey(result)) {
			randomMap.put(result, "");
		} else {
			return getRandom(len);
		}
		return result;
	}
	
	/**
	 * @return
	 */
	public static String[] getIntArray() {
		Random rd = new Random();
		String[] intArray = new String[10];
		List<String> intList = new LinkedList<String>();
		for (int i=0; i<= 9; i++){
			intList.add(String.valueOf(i));
		}
		for(int i=0; i<=9; i++) {
			int tmpInt = rd.nextInt(10);
			int idx = tmpInt%(10-i);
			intArray[i] = intList.get(idx);
			intList.remove(idx);
		}
		return intArray;
	}
	
	public static void printMap() {
		System.out.println("===========start print cpNoMap!!!!!!!");
		for(String cpNo : randomMap.keySet()) {
			System.out.println("cpNo :=" + cpNo);
		}
		System.out.println("===========end print cpNoMap!!!!!!!");
	}
	
	public static void main(String[] args) {
		System.out.println(getRandom(10));
	}
	
	/**
	 * 生成n随机数
	 * 
	 * @param lenth
	 * @return
	 */
	private static Random random=new Random();
	public static String randomNum(int length) {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<length;i++){
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
	
	
	private static Random generator = new Random();
	
	public static String getNonceStr(){
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < 32; i++) {
			sb.append(captchars[generator.nextInt(captchars.length - 1) + 1]);
		}
		return sb.toString();
	}
	
	public static String getCardPwd(){
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append(captchars[generator.nextInt(captchars.length - 1) + 1]);
		}
		return sb.toString();
	}
}
