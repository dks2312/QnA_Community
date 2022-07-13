package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public void close() {
		try {
			con.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("DB를 닫는 도중 오류가 발생했습니다\n"+ e.getMessage());
		}
	}
	
	public int selectCount(Map<String, Object> map) {
		String query = "SELECT Count(*) FROM POST ";
		
		if(map.get("searchTitle") != null) {
			query += "WHERE title LIKE '%"+ map.get("searchTitle") +"%' ";
		} 
		
		System.out.println(query);
		
		try {
			rs = stmt.executeQuery(query);
			rs.last();
			
			if(rs.getRow() != 0) {
				System.out.println("\""+ map.get("searchTitle") +"\"검색 결과 개수 : "+ rs.getInt(1));
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("DB에서 전체 개수를 불러오는 과정에서 오류가 발생했습니다\n"+ e.getMessage());
//			e.printStackTrace();
		}
		
		return 0;
	}

	public Queue<PostVO> selectList(Map<String, Object> map) {
		Queue<PostVO> list = new LinkedList<PostVO>();
		
		try {
			String query = "SELECT NUM, CATEGORY, TITLE, CONTENT, WRITER, VISIT_COUNT, LIKE_COUNT, COMMENT_COUNT, POST_DATE FROM POST ";
			boolean flag = false;
			// 검색
			if(map.get("searchTitle") != null) {
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
						rs.getInt(1),
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
		
		String query = "SELECT NUM, CATEGORY, TITLE, CONTENT, WRITER, VISIT_COUNT, LIKE_COUNT, COMMENT_COUNT, POST_DATE FROM ( "
						+"	SELECT Tb.*, ROWNUM rNum FROM ( "
						+"		SELECT * FROM POST ";
		
		boolean flag = false;
		// 검색
		if(map.get("searchTitle") != null) {
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
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				PostVO tmp = new PostVO(
						rs.getInt(1),
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
				PostVO post = new PostVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
				return post;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

//	public void insert(PostVO post) {
//		try {
//			String query = "INSERT INTO POST(NUM, CATEGORY, TITLE, CONTENT, WRITER) "
//					+ "VALUES(SEO_POST_NUM.NEXTVAL, '"+ post.getCartegory() + "', '"+ post.getTitle() + "', '"+ post.getContent() + "', '"+ post.getWriter() + "')";
//			System.out.println("SQL : " + query);
//			rs = stmt.executeQuery(query);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}	

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
	
	private void dummyInsert(String cartegory, String title, String content, String writer, 
			int visitCount, int likeCount, int commentCount, String postDate) {
		try {
			String query = "INSERT INTO POST(NUM, CATEGORY, TITLE, CONTENT, WRITER, VISIT_COUNT, LIKE_COUNT, COMMENT_COUNT, POST_DATE) "
					+ "VALUES(SEO_POST_NUM.NEXTVAL, '"+ cartegory + "', '"+ title + "', '"+ content + "', '"+ writer + "', "
						+ "'"+ visitCount + "', '"+ likeCount + "', '"+ commentCount + "', "
						+ "to_date('"+ postDate +"','YYYY/MM/DD HH24:MI'))";
			System.out.println("SQL : " + query);
				rs = stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		PostDAO dao = new PostDAO();
		
		for(int i = 1; true; i++) {
			
			try {
				int visitCount = (int)(Math.random() * 100);
				int likeCount = (int)(Math.random() * 100);
				int commentCount = (int)(Math.random() * 10);
				
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				
				cal.add(Calendar.DATE, -(int)(Math.random() * 1000));
				
				String date = df.format(cal.getTime());
				
				
				dao.dummyInsert("quest", "더미 게시글 "+i, "이게 질문인가"+ i, "dks2312", visitCount, likeCount, commentCount, date);
			} catch (Exception e) {
				System.out.println("더이상 Insert 할 수 없습니다!!\n");
				e.printStackTrace();
				
				break;
			}
		}
	}
	
}
