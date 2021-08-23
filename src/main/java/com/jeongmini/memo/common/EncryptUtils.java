package com.jeongmini.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	//암호화 하는 메소드
	public static String md5(String message) {
		String encData = "";
	
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); //초기화
			byte[] bytes = message.getBytes(); //이진수로 저장하는 배열
			md.update(bytes); //암호화
			byte[] digest = md.digest(); //암호화된 결과 꺼내기
			
			//16진수 -> 문자열

			for(int i = 0; i < digest.length; i++) {
				encData += Integer.toHexString(digest[i]&0xff);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return encData;
	}
}		


