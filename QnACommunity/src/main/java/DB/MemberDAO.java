package DB;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

public class MemberDAO extends BasicDAO{
	public MemberDAO(){ super(); }
	public MemberDAO(ServletContext application){ super(application); }
	
	public MemberVO selected(String id, String password) {
		try {
			String query = "SELECT id, password, nick_name FROM member WHERE id='"+ id +"' AND password='"+ password +"'";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());
			
			if (rs.getRow() == 0) 
				System.out.println("0 row selected...");
			else {
				MemberVO userVo = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3));
				return userVo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void insert(MemberVO user) {
		try {
			String query = "INSERT INTO member VALUES('"+ user.getId() +"', '"+ user.getPassword() +"', '"+ user.getNickName() + "')";
			System.out.println("SQL : " + query);
			stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reName(String target_id, String name) {
		try {
			String query = "UPDATE MEMBER SET NICK_NAME = '"+ name +"' WHERE ID = '"+ target_id +"'";
			System.out.println("SQL : " + query);
			stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Integer> tableInfo(){
		return tableInfo("MEMBER");
	}
}
