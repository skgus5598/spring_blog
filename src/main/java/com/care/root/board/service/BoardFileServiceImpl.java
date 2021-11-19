package com.care.root.board.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardFileServiceImpl implements BoardFileService {


	@Override
	public String getMessage(HttpServletRequest request, String msg, String url) {

		String message = null;
		String path = request.getContextPath(); // 절대경로
//		message = "<script>alert('새 글이 추가되었습니다.')"; 변수로 써서 메서드를 여러군데 활용할 수 있도록 
		message = "<script>alert(' " + msg + " ');";
		message += "location.href= ' " + path + url + " ' ; </script>"; // ' " + + " '
		return message;
	}

	@Override
	public String saveFile(MultipartFile file) {
		SimpleDateFormat simpl = new SimpleDateFormat("yyyyMMddHHmmss-");
		Calendar calendar = Calendar.getInstance(); // 현재 날짜 얻어오기 위함
		String sysFileName = simpl.format(calendar.getTime()) + file.getOriginalFilename(); // 현재 날짜 가져와서 이 형태로 바꿔줌
		File saveFile = new File(IMAGE_REPO + "/" + sysFileName);
		try {
			file.transferTo(saveFile);// 해당 위치에 파일 저장
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysFileName;
	}

	@Override
	public void deleteImage(String originFileName) {
		File deleteFile = new File(IMAGE_REPO+"/"+originFileName);
		deleteFile.delete();
	}

}






