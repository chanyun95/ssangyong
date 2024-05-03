--사원관리
create table semployee(
num number primary key,
id varchar2(12) unique not null,
name varchar2(30) not null,
passwd varchar2(12) not null,
salary number(8) not null,
job varchar2(30) not null,
reg_date date default sysdate not null
);
create sequence semployee_seq;
--사원게시판
create table story(
snum number not null, --story의 primary key
title varchar2(150) not null,
content clob not null,
ip varchar2(30) not null,
num number not null, --semployee
reg_date date default sysdate not null,
constraint story_pk primary key (snum),
constraint story_fk foreign key (num) references semployee (num)
);
create sequence story_seq;