package kr.spring.board.vo;

import kr.spring.util.DurationFromNow;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardReplyVO {
	private long re_num;		//댓글 번호
	private String re_content;	//내용
	private String re_date;		//등록일
	private String re_mdate;	//수정일
	private String re_ip;		//아이피 주소
	private long board_num;		//부모글 번호
	private long mem_num;		//작성자 회원번호
	
	private String id;			//아이디
	private String nick_name;	//별명
	
	//로그인한 상태에서 클릭한 사람의 정보 읽기, 로그인하지 않으면 0 전달
	private int click_num;
	private int refav_cnt;		//댓글 좋아요 개수
	
	private int resp_cnt;		//답글 개수
	
	public void setRe_date(String re_date) {
		this.re_date = DurationFromNow.getTimeDiffLabel(re_date);
	}
	public void setRe_mdate(String re_mdate) {
		this.re_mdate = DurationFromNow.getTimeDiffLabel(re_mdate);
	}
}