package com.care.root.mybatis.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.care.root.board.dto.BoardDTO;
import com.care.root.board.dto.BoardRepDTO;

public interface BoardMapper {
	
	public List<BoardDTO> boardAllList(@Param("s")int start,@Param("e") int end); //2개 이상의 값이 들어가면 @param을 써줘야 한다 
	public int writeSave(BoardDTO dto); // 성공하면 1, 에러나 실패 0, -1
	public BoardDTO contentView(int writeNo);
	public void upHit(int writeNo);
	public int modify(BoardDTO dto);
	public int delete(int writeNo);
	public void addReply(BoardRepDTO dto);
	public List<BoardRepDTO> getRepList(int write_group);
	public int selectBoardCount();
}
