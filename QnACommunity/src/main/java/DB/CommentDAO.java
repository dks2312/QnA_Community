package DB;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.ServletContext;

public class CommentDAO extends myDAO{
	public CommentDAO(){ super(); }
	public CommentDAO(ServletContext application){ super(application); }
	
	public Queue<CommentVO> commentList(long post) {
		Queue<CommentVO> commentQ = new LinkedList<CommentVO>();
		
		String query = "SELECT c.NUM, m.NICK_NAME, c.CONTENT, c.COMMENT_DATE "
					+ "FROM COMMENT_TB c, POST p, MEMBER m "
					+ "WHERE c.POST_NUM = p.NUM AND c.WRITER_ID = m.ID "
					+ "AND POST_NUM = "+ post +" ";
		System.out.println("SQL : " + query);
		
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				CommentVO commentVO = new CommentVO(post, rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
				commentQ.offer(commentVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return commentQ;
	}

	public void insert(long post, String user, String content) {
		String query = "INSERT INTO COMMENT_TB(NUM, POST_NUM, WRITER_ID, CONTENT) VALUES(SEO_COMMENT_NUM.NEXTVAL, ?, ?, ?)";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setLong(1, post);
			psmt.setString(2, user);
			psmt.setString(3, content);
			psmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean addLike(long postNum, String userId) {
		String query = "INSERT INTO LIKE_COMMENT_TB(POST_NUM, WRITER_ID) VALUES(?, ?)";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setLong(1, postNum);
			psmt.setString(2, userId);
			psmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void removeLike(long postNum, String userId) {
		String query = "DELETE FROM LIKE_COMMENT_TB WHERE LIKE_NUM = ? AND LIKE_USER = ?";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setLong(1, postNum);
			psmt.setString(2, userId);
			psmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
		
	}
	
	// 댓글의 좋아요 개수를 구함
	public int getLikeCount(long postNum) {
		return getCount("LIKE_COMMENT_TB", "LIKE_NUM", postNum);
	}
}
