--회원 테이블
create table member(
	mem_num number not null,
	mem_id varchar2(12) not null,
	mem_auth number(1) default 2 not null,
	constraint member_pk primary key (mem_num)
);
--회원 상세 테이블
create table member_detail(
	mem_num number not null,
	mem_passwd varchar2(20) not null,
	mem_name varchar2(30) not null,
	mem_birth date not null,
	mem_email varchar2(50) not null,
	mem_phone varchar2(11) not null,
	mem_zipcode varchar2(5) not null,
	mem_address1 varchar2(90) not null,
	mem_address2 varchar2(90) not null,
	mem_reg_date date default sysdate not null,
	mem_modify_date date,
	constraint member_detail_pk primary key (mem_num),
	constraint member_detail_fk foreign key (mem_num) references member (mem_num)
);
create sequence member_seq;

--의사회원 테이블
create table dmember(
	dmem_num number not null,
	dmem_id varchar2(12) not null,
	dmem_passwd varchar2(20) not null,
	dmem_name varchar2(30) not null,
	dmem_email varchar2(50) not null,
	dmem_reg_date date default sysdate not null,
	dmem_depart varchar2(15) not null, --진료과
	dmem_hname varchar2(30) not null, --병원 이름
	dmem_phone varchar2(11) not null, -- 병원 전화번호
	dmem_photo varchar2(400) not null, --의사 프로필 사진
	dmem_zipcode varchar2(5) not null, --병원 주소 우편번호
	dmem_address1 varchar2(90) not null, --병원 주소
	dmem_address2 varchar2(90) not null, --병원 상세주소
	dmem_license varchar2(400) not null, --의사 면허증
	constraint dmember_pk primary key (dmem_num)
); 
create sequence dmember_seq;
