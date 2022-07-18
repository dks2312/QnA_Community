package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LikeDAO{
	private String table;
	private Connection con;
	private PreparedStatement psmt;
	
	public LikeDAO(String table, Connection con, PreparedStatement psmt) {
		this.table = table;
		this.con = con;
		this.psmt = psmt;
	}

	public boolean addLike(long postNum, String userId) {
		String query = "INSERT INTO "+ table +"(LIKE_NUM, LIKE_USER) VALUES(?, ?)";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setLong(1, postNum);
			psmt.setString(2, userId);
			psmt.executeUpdate();
			return true;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return false;
	}
	
	public void removeLike(long postNum, String userId) {
		String query = "DELETE FROM "+ table +" WHERE LIKE_NUM = ? AND LIKE_USER = ?";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setLong(1, postNum);
			psmt.setString(2, userId);
			psmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}
}
