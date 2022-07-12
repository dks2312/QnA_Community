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

	public Queue<PostVO> selectList(Map<String, Object> map) {
		Queue<PostVO> list = new LinkedList<PostVO>();
		
		try {
			String query = "SELECT NUM, CATEGORY, TITLE, CONTENT, WRITER, VISIT_COUNT, LIKE_COUNT, COMMENT_COUNT, POST_DATE FROM POST ";
			boolean flag = false;
			// 검색
			if(!(map.get("searchTitle") == null || map.get("searchTitle").equals(""))) {
				query += "WHERE title LIKE '%"+ map.get("searchTitle") +"%' ";
				flag = true;
			} 
			
			// 카테고리
			if(!(map.get("searchCategory") == null || map.get("searchCategory").equals("all"))) {
				query += (flag ? "AND" : "WHERE") +" CATEGORY = '"+ map.get("searchCategory") +"' ";
			}
			
			// 정렬
			if(map.get("searchSort") == null) 
				query += "ORDER BY NUM";
			else 
				query += "ORDER BY "+ map.get("searchSort");
			
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				PostVO tmp = new PostVO(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getString(9));
				list.offer(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}
	
	public Queue<PostVO> selectListPage(Map<String, Object> map) {
		Queue<PostVO> list = new LinkedList<PostVO>();
		
		String query = "SELECT NUM, CATEGORY, TITLE, CONTENT, WRITER, VISIT_COUNT, LIKE_COUNT, COMMENT_COUNT, POST_DATE FROM POST( "
						+"	SELECT Tb.*, ROWNUM rNum FROM ( "
						+"		SELECT * FROM POST ";
		
		boolean flag = false;
		// 검색
		if(!(map.get("searchTitle") == null || map.get("searchTitle").equals(""))) {
			query += "WHERE title LIKE '%"+ map.get("searchTitle") +"%' ";
			flag = true;
		} 
		
		// 카테고리
		if(!(map.get("searchCategory") == null || map.get("searchCategory").equals("all"))) {
			query += (flag ? "AND" : "WHERE") +" CATEGORY = '"+ map.get("searchCategory") +"' ";
		}
		
		// 정렬
		if(map.get("searchSort") == null) 
			query += "ORDER BY NUM";
		else 
			query += "ORDER BY "+ map.get("searchSort");
		
		query += "	) Tb"
				+")"
				+"WHERE rNum BETWEEN ? AND ?";
		
		System.out.println("SQL : " + query);
		
		try {
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				PostVO tmp = new PostVO(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getString(9));
				list.offer(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}
	
	public PostVO selectPost(String num) {		
		try {
			String query = "SELECT NUM, CATEGORY, TITLE, CONTENT, WRITER, VISIT_COUNT, LIKE_COUNT, COMMENT_COUNT, POST_DATE FROM POST WHERE num = '"+ num +"'";
			
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected...");
			}else {
				PostVO post = new PostVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
				return post;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
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
