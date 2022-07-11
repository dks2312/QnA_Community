package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class PostDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "c##green";
	private String password = "green1234";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	public PostDAO() {
		connDB();
	}

	public Queue<PostVO> search(Map<String, Object> map) {
		Queue<PostVO> list = new LinkedList<PostVO>();
		
		try {
			String query = "SELECT NUM, CATEGORY, TITLE, CONTENT, WRITER, POST_DATE, VISIT_COUNT, LIKE_COUNT, COMMENT_COUNT FROM POST ";
			
			if(map.get("searchTitle") != null) {
				query += "WHERE title LIKE '%"+ map.get("searchTitle") +"%' ";
			} 
			
			if(map.get("searchCategory") != null) {
				if(map.get("searchTitle") != null) 
					query += "AND CATEGORY = '"+ map.get("searchCategory") +"' ";
				else 
					query += "WHERE title LIKE '%"+ map.get("searchTitle") +"%' ";
			}
			
			if(map.get("searchSort") != null) query += "ORDER BY "+ map.get("searchSort");
			else query += "ORDER BY num";
			
			
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				PostVO tmp = new PostVO(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getInt(9));
				list.offer(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	public void insert(PostVO post) {
		try {
			String query = "INSERT INTO POST(NUM, CATEGORY, TITLE, CONTENT, WRITER) "
					+ "VALUES('"+ post.getNum() +"', '"+ post.getCartegory() + "', '"+ post.getTitle() + "', '"+ post.getContent() + "', '"+ post.getWriter() + "')";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
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
