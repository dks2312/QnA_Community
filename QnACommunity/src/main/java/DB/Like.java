package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Queue;

public interface Like<T>{
	
	// 사용자가 쓴 좋아요한 게시물 가져오기
	public Queue<T> likeSearch(String userId);
	public void likeAction(long postNum, String userId);
	
	public default void likeAction(Connection con, PreparedStatement psmt, String table, long num, String userId) {
		if(!addLike(con, psmt, table, num, userId)) {
			removeLike(con, psmt, table, num, userId);
		}
	}
	
	public default boolean addLike(Connection con, PreparedStatement psmt, String table, long num, String userId) {
		String query = "INSERT INTO "+ table +"(LIKE_NUM, LIKE_USER) VALUES(?, ?)";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setLong(1, num);
			psmt.setString(2, userId);
			psmt.executeUpdate();
			return true;
		} catch(SQLIntegrityConstraintViolationException e) {}	// 좋아요를 누른 전적이 있다면 무시함
		catch (SQLException e) { e.printStackTrace(); }
		
		return false;
	}
	
	public default void removeLike(Connection con, PreparedStatement psmt, String table, long num, String userId) {
		String query = "DELETE FROM "+ table +" WHERE LIKE_NUM = ? AND LIKE_USER = ?";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setLong(1, num);
			psmt.setString(2, userId);
			psmt.executeUpdate();
		} catch(SQLIntegrityConstraintViolationException e) {}	// 좋아요를 누른 전적이 있다면 무시함
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	
}
