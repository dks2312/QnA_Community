package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDAO {
	private ColumnData[] columnData = new ColumnData[3];
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "c##green";
	private String password = "green1234";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public class ColumnData{
		public String name;
		public int size;
		
		public ColumnData(String name, int size) {
			this.name = name;
			this.size = size;
		}
	}
	
	public MemberDAO() {
		connDB();
		columnSizeInit();
	}

	public boolean selected(MemberVo user) {
		try {
			String query = "SELECT * FROM member WHERE id='"+ user.getId() +"' AND password='"+ user.getPassword() +"'";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());
			
			if (rs.getRow() == 0) 
				System.out.println("0 row selected...");
			else 
				return true;
		} catch (Exception e) {
			System.out.println("Error : "+ e.getMessage());
		}

		return false;
	}

	public void insert(MemberVo user) {
		try {
			String query = "INSERT INTO member VALUES('"+ user.getId() +"', '"+ user.getPassword() +"', '"+ user.getNickName() + "')";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	private void columnSizeInit() {
		try {
			String query = "SELECT * FROM member";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();

			for (int i = 1; i <= columnData.length; i++) {
				columnData[i-1] = new ColumnData(rsmd.getColumnName(i), rsmd.getColumnDisplaySize(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.\n");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success.\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ColumnData columnData(int index) {
		return columnData[index];
	}
}
