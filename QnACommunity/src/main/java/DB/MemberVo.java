package DB;

public class MemberVo {
	private String id;
	private String password;
	private String nickName;

	public MemberVo() {

	}

	public MemberVo(String id, String password, String nickName) {
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
