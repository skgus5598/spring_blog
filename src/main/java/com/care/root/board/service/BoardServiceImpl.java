package com.care.root.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;
import com.care.root.mybatis.board.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired BoardMapper mapper;
	@Autowired BoardFileService bfs;
	
	@Override
	public void boardAllList(Model model, int num) {
		int pageLetter = 3; //페이지당 보여질 글 개수 
		int allCount = selectBoardCount(); // 총 게시물 개수 
		int repeat = allCount / pageLetter ; // 반복 횟수 및 총 페이지 수 
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;   // 시작값 
		int start = end + 1 - pageLetter;  // 끝 값 
		model.addAttribute("repeat", repeat);
		model.addAttribute("boardList", mapper.boardAllList(start, end));
	}
	
	public int selectBoardCount() {
		return mapper.selectBoardCount();
	}

	

	public String writeSave(MultipartHttpServletRequest mul, HttpServletRequest request) {
	      BoardDTO dto = new BoardDTO();
	      dto.setTitle( mul.getParameter("title") );
	      dto.setContent( mul.getParameter("content") );
	      dto.setId(mul.getParameter("id"));

	      MultipartFile file = mul.getFile("image_file_name");
	      
	      if(file.getSize() != 0) {
	         //이미지 있을경우 처리
	    	  dto.setImageFileName( bfs.saveFile(file));
	      }else {
	         dto.setImageFileName("nan");
	      }
	      int result = 0;
	      String msg, url;
	      	      
	      try {
	    	  result = mapper.writeSave(dto); //1(성공)또는 에러 
		} catch (Exception e) {    
				e.printStackTrace();
		}	      
	      if(result == 1) {
	         msg="새 글이 추가되었습니다.";
	         url = "/board/boardAllList";
	      }else {
	    	  msg="문제가 발생하였습니다.";
		      url = "/board/writeForm";
	      }
	      return bfs.getMessage(request, msg, url);
	   }

	@Override
	public void contentView(int writeNo, Model model) {		
		model.addAttribute("personalData", mapper.contentView(writeNo));
		upHit(writeNo);
	}
	
	private void upHit(int writeNo) {
		mapper.upHit(writeNo);
	}

	@Override
	public void getData(int writeNo, Model model) {		
		model.addAttribute("personalData", mapper.contentView(writeNo));
	}
	
	public String modify(MultipartHttpServletRequest mul, HttpServletRequest request) {
	      BoardDTO dto = new BoardDTO();
	      dto.setWriteNo( Integer.parseInt(mul.getParameter("writeNo")) );
	      dto.setTitle(mul.getParameter("title"));
	      dto.setContent(mul.getParameter("content"));
	      
	      MultipartFile file = mul.getFile("imageFileName");
	      if(file.getSize() != 0 ) {
	         //이미지 변경시
	    	  dto.setImageFileName( bfs.saveFile(file));
	    	  bfs.deleteImage(mul.getParameter("originFileName"));
	      }else {
	         dto.setImageFileName(mul.getParameter("originFileName"));
	      }
	      int result = mapper.modify(dto);
	      String msg, url;
	      if(result == 1) {
	         msg = "성공적으로 수정되었습니다";
	         url = "/board/boardAllList";
	      }else {
	         msg = "수정 중 문제가 발생하였습니다";
	         url = "/board/modify_form";
	      }
	      String message = bfs.getMessage(request, msg, url);
	      return message;
	   }
	
	public String boardDelete(int writeNo,String imageFileName, 
															HttpServletRequest request) {		
			int result = mapper.delete(writeNo); // 성공 1,   실패 에러 0,-1
			String msg=null, url=null;
			if(result == 1) { 
				bfs.deleteImage(imageFileName); 
				msg = "성공적으로 삭제 되었습니다";
				url = "/board/boardAllList";
			}else {
				msg = "삭제 중 문제가 발생하였습니다";
				url = "/board/contentView";
			}
			String message = bfs.getMessage(request,msg,url);
			return message;
			}

	public void addReply(BoardRepDTO dto) {
		mapper.addReply(dto);
	}
	
	public List<BoardRepDTO> getRepList(int write_group){
		return mapper.getRepList(write_group);
	}

}
