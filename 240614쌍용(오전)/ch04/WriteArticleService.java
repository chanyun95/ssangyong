package kr.spring.ch04;

public class WriteArticleService {
	private WriteArticleDAO writeArticleDAO;
	
	//생성자
	public WriteArticleService(WriteArticleDAO writeArticleDAO) {
		this.writeArticleDAO = writeArticleDAO;
	}
	//service 가 dao 에 의존(DI)
	public void write() {
		System.out.println("WriteArticleService의 write()메서드 호출");
		
		writeArticleDAO.insert();
	}
	
	
}
