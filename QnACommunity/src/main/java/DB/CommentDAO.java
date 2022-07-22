package DB;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.ServletContext;

public class CommentDAO extends BasicDAO implements Like<CommentVO>{
	public CommentDAO(){ super(); }
	public CommentDAO(ServletContext application){ super(application); }
	
	// 포스트에 연결된 댓글 검사
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
	
	@Override
	public void likeAction(long postNum, String userId) {
		likeAction(con, psmt, "LIKE_COMMENT_TB", postNum, userId);
	}
	
	// likeTB에서 사용자가 누른 댓글
	@Override
	public Queue<CommentVO> likeSearch(String userId) {
		Queue<CommentVO> list = new LinkedList<CommentVO>();
		String query = "SELECT c.NUM, c.POST_NUM, c.WRITER_ID, c.CONTENT, c.COMMENT_DATE "
						+ "FROM LIKE_COMMENT_TB lc, COMMENT_TB c "
						+ "WHERE lc.LIKE_NUM = c.NUM "
						+ "AND lc.LIKE_USER = ?";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CommentVO commentVO = new CommentVO(
						rs.getLong(1), 
						rs.getLong(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5));
				
				list.offer(commentVO);
			}
			
			return list;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	// 사용자가 작성한 댓글
	public Queue<CommentVO> userSearch(String userId) {
		Queue<CommentVO> list = new LinkedList<CommentVO>();
		String query = "SELECT NUM, POST_NUM, WRITER_ID, CONTENT, COMMENT_DATE "
						+ "FROM COMMENT_TB "
						+ "WHERE WRITER_ID = ?";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CommentVO commentVO = new CommentVO(
						rs.getLong(1), 
						rs.getLong(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5));
				
				list.offer(commentVO);
			}
			
			return list;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	// 댓글의 좋아요 개수를 구함
	public int getLikeCount(long postNum) {
		return getCount("LIKE_COMMENT_TB", "LIKE_NUM", postNum);
	}
}
