package DB;

import javax.servlet.ServletContext;

public class MemberDAO extends myDAO{
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
}
