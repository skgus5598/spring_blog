package com.care.root.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface BoardFileService {
	public static final String IMAGE_REPO = "/Users/raina/Desktop/java/spring_workspace/image_repo";

	public String getMessage(HttpServletRequest reqeust, String msg, String url);
	
	public String saveFile(MultipartFile file);
	public void deleteImage(String originFileName);

}
