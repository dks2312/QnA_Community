package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.sql.DataSource;

public class BasicDAO {
	protected Connection con;
	protected Statement stmt;
	protected PreparedStatement psmt;
	protected ResultSet rs;
	
    public BasicDAO() {
        try {
            // 커넥션 풀(DataSource) 얻기
            Context initCtx = new InitialContext();
            Context ctx = (Context)initCtx.lookup("java:comp/env");
            DataSource source = (DataSource)ctx.lookup("dbcp_myoracle");

            // 커넥션 풀을 통해 연결 얻기
            con = source.getConnection();

            System.out.println("DB 커넥션 풀 연결 성공");
        }
        catch (Exception e) {
            System.out.println("DB 커넥션 풀 연결 실패");
            e.printStackTrace();
        }
    }
	
//	public BasicDAO() {
//		try {
//			String driver = "oracle.jdbc.driver.OracleDriver";
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "c##green";
//			String password = "green1234";
//			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, user, password);
//			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//			
//			System.out.println("DB 연결 성공");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
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
			if(rs != null)	rs.close();
			if(stmt != null)stmt.close();
			if(psmt != null)psmt.close();
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
	
	public Map<String, Integer> tableInfo(String table){
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		String query = "SELECT COLUMN_NAME, DATA_LENGTH "
					+ "FROM user_tab_columns "
					+ "WHERE TABLE_NAME = '"+ table +"'";
		
		System.out.println(query);
		
		try {
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return map;
	}
}