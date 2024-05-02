create table mboard(
	num number not null,
	title varchar2(150) not null,
	name varchar2(30) not null,
	passwd varchar2(12) not null,
	content clob not null,
	ip varchar2(30) not null,
	reg_date date default sysdate not null,
	constraint mboard_pk primary key (num)
);
create sequence mboard_seq;