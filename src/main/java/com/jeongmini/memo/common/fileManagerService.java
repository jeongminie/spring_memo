package com.jeongmini.memo.common;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class fileManagerService {
	
	      //절대 안바뀜    변수 이름 대문자로 쓰는게 일반적
	private final String FILE_UPLOAD_PATH = "C:\\Users\\opooi\\OneDrive\\바탕 화면\\workspace\\Spring\\upload\\images/";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//파일 업로드           사용자별로 아이디 관리해서 파일이름 겹치는것 방지
	public String saveFile(int userId, MultipartFile file) {
		//올린 시간별로 구분해서 저장
		//hagulu_35245632/test.png
		//1970년 1월 1일 이후 지금 몇초가 지났는지
		
		String directoryName = userId + "_" + System.currentTimeMillis() + "/"; 
		//완전한 파일 경로
		
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		//파일 저장할 디렉토리 생성
		File directory = new File(filePath);
		
		if(directory.mkdir() == false ) {
			logger.error("[fileManagerService saveFile] 디렉토리 생성 실패");
			return null;
		} 
		
		//파일저장
		// byte 바이트는 가장 날것
		byte[] bytes;
		try {
			bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			//그 패스에 해당하는 바이트를 저장
			Files.write(path, bytes);			
		} catch (IOException e) {
			logger.error("[fileManagerService saveFile] 디렉토리 생성 실패");
			e.printStackTrace();
			return null;
		}
		//파일 접근 가능한 path 리턴
		//src="/image/hagulu_12123242/test.png
		return "/images/" + directoryName + file.getOriginalFilename();
	}
}
