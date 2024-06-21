package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.board.vo.BoardVO;

public interface BoardService {
	public void insertBoard(BoardVO board);
	public int selectBoardCount();
	public List<BoardVO> selectBoardList(Map<String, Integer> map);
	public BoardVO selectBoard(int num);
	public void updateBoard(BoardVO vo);
	public void deleteBoard(int num);
	
}
