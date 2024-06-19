package kr.spring.ch09.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class MemberVO {
	//정규표현식으로 패턴 검사
	@Pattern(regexp="^[0-9a-zA-Z]+$")
	private String id;
	//문자열의 길이 지정
	@Size(min=4,max=10)
	private String password;
	@NotEmpty
	private String name;
	@Email
	@NotEmpty
	private String email;
	//숫자 데이터의 길이를 지정
	@Range(min=1,max=200)
	private Integer age;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", age=" + age
				+ "]";
	}
	
}
