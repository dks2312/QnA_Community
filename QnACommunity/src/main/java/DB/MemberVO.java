package DB;

public class MemberVO {
	private String id;
	private String password;
	private String nickName = "익명의 사용자";
	
	public MemberVO(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public MemberVO(String id, String password, String nickName) {
		this.id = id;
		this.password = password;
		this.nickName = nickName;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	
	public String getNickName() {
		return nickName;
	}
}
