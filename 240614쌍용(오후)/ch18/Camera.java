package kr.spring.ch18;

public class Camera {
	//프로퍼티
	private int number;

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Camera [number=" + number + "]";
	}
	
}
