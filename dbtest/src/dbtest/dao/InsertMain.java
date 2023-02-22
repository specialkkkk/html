package dbtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; //이것도 마찬가지로 관련 메소드가 많기때문에 implements 하면 안된다
import java.sql.SQLException;
import java.util.Scanner;
// implements하면 추상메소드를 override로 일일이 public 어쩌구 구현해줘야한다 (java api 8 사이트에 보면 connection 관련 메소드가 많다)
public class InsertMain  {
	private Connection conn;//implements connection 하면 너무 많이 들어와=> private connection
	private PreparedStatement pstmt;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1522:xe" ;
	private String username = "c##java" ;
	private String password = "1234";
	
	
	
	public InsertMain() { //빈칸에  constructor => 괄호안에 아무것도 없으면 기본생성자
		try {
			Class.forName(driver); //driver loading  (한번만 처리하면 된다) class타입으로 생성 // 맨뒤에 .class 지웠음 ( 알아서 처리)
			System.out.println("driver loading 성공");
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
	
	public void insertArticle() {// try catch 로 예외처리
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 입력 : ");
		String name = scan.next();
		
		System.out.print("나이 입력 : ");
		int age = scan.nextInt();
		
		System.out.print("키 입력 : ");
		double height = scan.nextDouble();
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		
		this.getConnection(); //접속
		
		String sql = "insert into dbtest values (?, ? , ? , sysdate)";
		
		try {
			pstmt = conn.prepareStatement(sql);//생성 // name, age, height 같은 데이터를 ?로 대입( 보안상)
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setDouble(3, height);
			
			
			int su = pstmt.executeUpdate(); //실행 => 개수 리턴
			System.out.println(su + "행 이(가) 삽입되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { //finally = 에러가 있건 없건 실행
			try {
				if(pstmt != null) pstmt.close(); // 너가 null이냐 확인하고 close해라
				if(conn != null) conn.close(); // 너가 null이냐 확인하고 close해라
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		InsertMain im = new InsertMain();
		im.insertArticle();
	}

}
