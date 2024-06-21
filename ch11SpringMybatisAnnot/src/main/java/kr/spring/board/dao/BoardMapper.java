package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardVO;

public interface BoardMapper {
	public void insertBoard(BoardVO board);
	//xml 태그를 사용하면 어노테이션 사용X > 충돌
	@Select("SELECT COUNT(*) FROM aboard")
	public int selectBoardCount();
	public List<BoardVO> selectBoardList(Map<String, Integer> map);
	@Select("SELECT * FROM aboard WHERE num=#{num}")
	public BoardVO selectBoard(int num);
	@Update("UPDATE aboard SET writer=#{writer},title=#{title},content=#{content} WHERE num=#{num}")
	public void updateBoard(BoardVO vo);
	@Delete("DELETE FROM aboard WHERE num=#{num}")
	public void deleteBoard(int num);
	
}
