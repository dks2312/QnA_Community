package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public interface Like{
	
	public void likeAction(long postNum, String userId);
	
	public default void likeAction(Connection con, PreparedStatement psmt, String table, long postNum, String userId) {
		if(!addLike(con, psmt, table, postNum, userId)) {
			removeLike(con, psmt, table, postNum, userId);
		}
	}
	
	public default boolean addLike(Connection con, PreparedStatement psmt, String table, long postNum, String userId) {
		String query = "INSERT INTO "+ table +"(LIKE_NUM, LIKE_USER) VALUES(?, ?)";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setLong(1, postNum);
			psmt.setString(2, userId);
			psmt.executeUpdate();
			return true;
		} catch(SQLIntegrityConstraintViolationException e) {}	// 좋아요를 누른 전적이 있다면 무시함
		catch (SQLException e) { e.printStackTrace(); }
		
		return false;
	}
	
	public default void removeLike(Connection con, PreparedStatement psmt, String table, long postNum, String userId) {
		String query = "DELETE FROM "+ table +" WHERE LIKE_NUM = ? AND LIKE_USER = ?";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setLong(1, postNum);
			psmt.setString(2, userId);
			psmt.executeUpdate();
		} catch(SQLIntegrityConstraintViolationException e) {}	// 좋아요를 누른 전적이 있다면 무시함
		catch (SQLException e) { e.printStackTrace(); }
	}
}
