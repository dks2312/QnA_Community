package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDAO {	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "c##green";
	private String password = "green1234";

	private Connection con;
	private Statement stmt;
	
	public MemberDAO() {
		connDB();
	}
	
	public void close() {
		try {
			con.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("메모리 해제중 에러\n");
			e.printStackTrace();
		}
	}
	
	

	public MemberVO selected(String id, String password) {
		try {
			String query = "SELECT id, password, nick_name FROM member WHERE id='"+ id +"' AND password='"+ password +"'";
			System.out.println("SQL : " + query);
			ResultSet rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());
			
			if (rs.getRow() == 0) 
				System.out.println("0 row selected...");
			else {
				MemberVO userVo = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3));
				return userVo;
			}
		} catch (Exception e) {
//			System.out.println("Error : "+ e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public void insert(MemberVO user) {
		try {
			String query = "INSERT INTO member VALUES('"+ user.getId() +"', '"+ user.getPassword() +"', '"+ user.getNickName() + "')";
			System.out.println("SQL : " + query);
			stmt.executeQuery(query);
		} catch (Exception e) {
//			System.out.println("Error : "+ e.getMessage());
			e.printStackTrace();
		}
	}	

	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success.");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
