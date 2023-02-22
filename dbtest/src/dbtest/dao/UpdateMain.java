package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateMain {
	private Connection conn;//implements connection 하면 너무 많이 들어와=> private connection
	private PreparedStatement pstmt;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1522:xe" ;
	private String username = "c##java" ;
	private String password = "1234";
	
	public UpdateMain() { //생성자
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void getConnection() { //접속하기
		try {
			conn = DriverManager.getConnection(url,username,password); //URL , 계정명, 비번
		                  	//(지포스처럼)오라클 드라이버 이름 : thin  //@아이피      * localhost = 나 자신    1522=포트번호  xe=DB
			System.out.println("connection 성공");
		} catch (SQLException e) {

			e.printStackTrace();
		}  
	}	
	
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	// 여기까지 insertMain과 똑같음
	
	
	
	
	
	public void updateArticle() {
		Scanner scan = new Scanner(System.in);
		System.out.print("검색 할 이름 입력 : ");
		String name = scan.next();
		
		getConnection(); //접속 //this 안써도그만
		
		String sql = "update dbtest set age=age+1, height=height+1 where name like ?"; //?은 홍 이지만 보안상 물음표로 대체
		
				
				
		try {
			pstmt = conn.prepareStatement(sql); //생성
			
			pstmt.setString(1,  "%"+name+"%");
			
			int su = pstmt.executeUpdate(); //실행 => 개수 리턴
			System.out.println(su + "행 이(가) 업데이트 되었습니다");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally { //finally = 에러가 있건 없건 실행
			try {
				if(pstmt != null) pstmt.close(); // 너가 null이냐 확인하고 close해라
				if(conn != null) conn.close(); // 너가 null이냐 확인하고 close해라
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		
		
	}
	
	
	
	public static void main(String[] args) {
		UpdateMain um = new UpdateMain();
		um.updateArticle();

		
	}

}

/*
검색 할 이름을 입력 : 홍

 홍이 들어간 레코드의 나이를 1증가 , 키도 1증가 하시오
 */
