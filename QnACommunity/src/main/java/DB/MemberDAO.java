package DB;

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
	
	
	public String validateMamberIPN(MemberVO mamber) {
		return validateMamberIPN(mamber.getId(), mamber.getPassword(), mamber.getNickName());
	}
	public String validateMamberIPN(String id, String pw, String nicName) {
		Map<String, Integer> map = tableInfo();
		
		String msg = "";
		if(id.getBytes().length > map.get("ID")){
			msg += "아이디가 "+ id.getBytes().length +"Byte로 너무 깁니다 (최대:"+ map.get("ID") +")\\n";
		}
		if(pw.getBytes().length > map.get("PASSWORD")){
			msg += "비밀번호가 "+ pw.getBytes().length +"Byte로 너무 깁니다 (최대:"+ map.get("PASSWORD") +")\\n";
		}
		if(nicName.getBytes().length > map.get("NICK_NAME")){
			msg += "닉네임이 "+ nicName.getBytes().length +"Byte로 너무 깁니다 (최대:"+ map.get("NICK_NAME") +")\\n";
		}
		
		if(msg.equals("")) return null;
		else return msg;
	}
}
