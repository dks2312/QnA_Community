package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;

//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.sql.DataSource;

public class BasicDAO {
	protected Connection con;
	protected Statement stmt;
	protected PreparedStatement psmt;
	protected ResultSet rs;
	
	// 커넥션 풀을 이용한 DB연결 (성공함)
//	public myDAO() {
//		try {
//			// JNDI 서비스를 이용하기 위한 시작점
//			Context initCtx = new InitialContext();
//			
//			// "java:comp/env" -> 현재 웹 애플리케이션의 루트 디렉터리. 모든 자원은 해당 디렉터리 아래에 위치해 있음
//			// lookup()메서드에 이름을 건네면 원하는 객체를 불러옴
//			Context ctx = (Context)initCtx.lookup("java:comp/env");
//			
//			// "dbcp_myoracle" -> context.xml파일에 <ResouceLink>에 있는 name 속성의 값, 불러온 이유는 server.xml파일에 있는 <Resource>에 있는 DB정보를 가져오기 위함
//			DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");
//			
//			
//			con = source.getConnection();
//			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			
//			System.out.println("DB 커넥션 풀 연결 성공");
//		} catch (Exception e) {
//			System.out.println("DB 커넥션 풀 연결 실패");
//			e.printStackTrace();
//		}
//	}
	
	public BasicDAO() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##green";
			String password = "green1234";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			System.out.println("DB 연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public BasicDAO(ServletContext application) {
		try {
			String driver = application.getInitParameter("OracleDrivaer");
			String url = application.getInitParameter("OracleURL");
			String user = application.getInitParameter("OracleId");
			String password = application.getInitParameter("OraclePwd");
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			System.out.println("DB 연결 성공1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(psmt != null) psmt.close();
			if(con != null) con.close();
			
			System.out.println("DB 연결 해제");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 특정 테이블의 특정 조건의 개수를 구함
	public int getCount(String table, String colunm, long value) {
		String query = "SELECT Count(*) FROM "+ table +" WHERE "+ colunm +" = "+ value;
		
		System.out.println(query);
		
		try {
			rs = stmt.executeQuery(query);
			rs.last();
			
			if(rs.getRow() != 0) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
