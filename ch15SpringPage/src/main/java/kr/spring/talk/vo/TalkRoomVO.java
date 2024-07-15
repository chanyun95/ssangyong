package kr.spring.talk.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TalkRoomVO {
	private long talkroom_num;		//채팅방 번호
	private String basic_name;		//기본 방이름
	private String talkroom_date;	//생성일
	
	private long[] members;			//채팅 멤버
	private long mem_num;			//채팅방 생성자
	private int room_cnt;			//읽지 않은 메시지수
	
	private TalkVO talkVO;
	private TalkMemberVO talkMemberVO;
}
