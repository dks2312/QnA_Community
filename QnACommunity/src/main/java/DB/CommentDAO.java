package DB;

import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.ServletContext;

public class CommentDAO extends myDAO{
	public CommentDAO(){ super(); }
	public CommentDAO(ServletContext application){ super(application); }
	
	public Queue<CommentVO> commentList(long post) {
		Queue<CommentVO> commentQ = new LinkedList<CommentVO>();
		
		String query = "SELECT m.NICK_NAME, c.CONTENT, c.LIKE_COUNT, c.COMMENT_DATE "
					+ "FROM COMMENTTABLE c, POST p, MEMBER m "
					+ "WHERE c.POST_NUM = p.NUM AND c.WRITER_ID = m.ID "
					+ "AND POST_NUM = "+ post +" ";
		System.out.println("SQL : " + query);
		
		try {
			rs = stmt.executeQuery(query);
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
			psmt = con.prepareStatement(query);
			psmt.setLong(1, post);
			psmt.setString(2, user);
			psmt.setString(3, content);
			psmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}