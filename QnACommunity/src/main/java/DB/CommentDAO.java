package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

public class CommentDAO {	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "c##green";
	private String password = "green1234";

	private Connection con;
	private Statement stmt;
	
	public CommentDAO() {
		connDB();
	}
	
	public void close() {
		try {
			con.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("DB 연결 해제중 에러\n");
			e.printStackTrace();
		}
	}
	
	

	public Queue<CommentVO> commentList(long post) {
		Queue<CommentVO> commentQ = new LinkedList<CommentVO>();
		
		String query = "SELECT m.NICK_NAME, c.CONTENT, c.LIKE_COUNT, c.COMMENT_DATE "
					+ "FROM COMMENTTABLE c, POST p, MEMBER m "
					+ "WHERE c.POST_NUM = p.NUM AND c.WRITER_ID = m.ID "
					+ "AND POST_NUM = "+ post +" ";
		System.out.println("SQL : " + query);
		
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				CommentVO commentVO = new CommentVO(post, rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
				commentQ.offer(commentVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return commentQ;
	}

	public void insert(long post, String user, String content) {
		String query = "INSERT INTO COMMENTTABLE(POST_NUM,	WRITER_ID, CONTENT) VALUES(?, ?, ?)";
		System.out.println("SQL : " + query);
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, post);
			ps.setString(2, user);
			ps.setString(3, content);
			ps.executeQuery();
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
