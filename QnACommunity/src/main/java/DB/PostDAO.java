package DB;

import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletContext;


public class PostDAO extends BasicDAO implements Like<PostVO>{
	public PostDAO(){ super(); }
	public PostDAO(ServletContext application){ super(application); }
	
	// 식별번호에 해당하는 게시글에 조회수를 하나 올려줌
	public void updateVisitCount(long num) {
		String query = "UPDATE POST "
					+ "SET VISIT_COUNT = VISIT_COUNT + 1 "
					+ "WHERE num = "+ num;
		System.out.println("SQL : " + query);
		
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// 검색된 게시글의 총 개수를 구함
	public int selectCount(Map<String, Object> map) {
		String query = "SELECT Count(*) FROM POST ";
		
		
		query += "WHERE title LIKE '%"+ map.get("search") +"%' ";
		
		// 카테고리
		if(!map.get("searchCategory").equals("all")) {
			query += "AND CATEGORY = '"+ map.get("searchCategory") +"' ";
		}
		
		// 정렬
		if(map.get("searchSort").equals("NUM")) 
			query += "ORDER BY NUM ASC";
		else 
			query += "ORDER BY "+ map.get("searchSort") +" DESC";
		
		System.out.println(query);
		
		try {
			rs = stmt.executeQuery(query);
			rs.last();
			
			if(rs.getRow() != 0) {
				System.out.println("\""+ map.get("search") +"\"검색 결과 개수 : "+ rs.getInt(1));
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("DB에서 전체 개수를 불러오는 과정에서 오류");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	@Override
	public Queue<PostVO> likeSearch(String userId) {
		Queue<PostVO> list = new LinkedList<PostVO>();
		String query = "SELECT p.NUM, p.CATEGORY, p.TITLE, p.CONTENT, p.WRITER, p.VISIT_COUNT, p.POST_DATE "
						+ "FROM LIKE_POST_TB lp, POST p "
						+ "WHERE lp.LIKE_NUM = p.NUM  "
						+ "AND lp.LIKE_USER = ?";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			
			while(rs.next()) { 
				PostVO tmp = new PostVO(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3),
						rs.getClob(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7));
				list.offer(tmp);
			}
			
			return list;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	// 사용자가 작성한 게시글
	public Queue<PostVO> userSearch(String userId) {
		Queue<PostVO> list = new LinkedList<PostVO>();
		String query = "SELECT NUM, CATEGORY, TITLE, CONTENT, WRITER, VISIT_COUNT, POST_DATE "
						+ "FROM POST WHERE "
						+ "WRITER = ?";
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				PostVO tmp = new PostVO(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3),
						rs.getClob(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7));
				
				list.offer(tmp);
			}
			
			return list;
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}
	
	public Queue<PostVO> selectList(Map<String, Object> map) {
		Queue<PostVO> list = new LinkedList<PostVO>();
		String query ="";
		
		query += "SELECT NUM, CATEGORY, TITLE, CONTENT, WRITER, VISIT_COUNT, POST_DATE FROM POST ";
		
		
		query += "WHERE title LIKE '%"+ map.get("search") +"%' ";
		
		// 사용자
		if(map.get("userId") != null) {
			query += "AND WRITER = '"+ map.get("userId") +"' ";
		}
		
		// 카테고리
		if(!map.get("searchCategory").equals("all")) {
			query += "AND CATEGORY = '"+ map.get("searchCategory") +"' ";
		}
		
		// 정렬
		if(map.get("searchSort").equals("NUM")) 
			query += "ORDER BY NUM ASC";
		else 
			query += "ORDER BY "+ map.get("searchSort") +" DESC";
		
		System.out.println(query);
		
		try {
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				PostVO tmp = new PostVO(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3),
						rs.getClob(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7));
				list.offer(tmp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return list;
	}
		
	
	// 페이징 기능
	public Queue<PostVO> selectListPage(Map<String, Object> map) {
		Queue<PostVO> list = new LinkedList<PostVO>();
		String query;
		
		query = "SELECT NUM, CATEGORY, TITLE, CONTENT, WRITER, VISIT_COUNT, POST_DATE FROM ( "
				+"	SELECT Tb.*, ROWNUM rNum FROM ( "
				+"		SELECT * FROM POST "
				+"		WHERE title LIKE '%"+ map.get("search") +"%' ";
		
		if(!map.get("searchCategory").equals("all")) {
			query +=" 	AND CATEGORY = '"+ map.get("searchCategory") +"' ";
		}
		
		if(map.get("searchSort").equals("NUM")) 
			query += "ORDER BY NUM ASC";
		else 
			query += "ORDER BY "+ map.get("searchSort") +" DESC";
		
		query +="	) Tb"
				+")"
				+"WHERE rNum BETWEEN ? AND ?";
		
		System.out.println("SQL : " + query);
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				PostVO tmp = new PostVO(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3),
						rs.getClob(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7));
				list.offer(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}
	
	// 식별번호로 해당 게시글을 찾음
	public PostVO selectPost(long num) {	
		String query = "SELECT CATEGORY, TITLE, CONTENT, WRITER, VISIT_COUNT, POST_DATE FROM POST WHERE NUM = "+ num;
		System.out.println("SQL : " + query);
		
		try {
			rs = stmt.executeQuery(query);
			rs.last();
			
			if(rs.getRow() == 0) {
				System.out.println("0 row selected...");
			}else {
				PostVO post = new PostVO(
						num,
						rs.getString(1), 
						rs.getString(2), 
						rs.getClob(3), 
						rs.getString(4),
						rs.getInt(5), 
						rs.getString(6));
				
				return post;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	// 검색된 게시글의 총 개수를 구함
	public int getCommentCount(long postNum) {
		return getCount("COMMENT_TB", "POST_NUM", postNum);
	}
	// 검색된 게시글의 총 개수를 구함
	public int getLikeCount(long postNum) {
		return getCount("LIKE_POST_TB", "LIKE_NUM", postNum);
	}

	
	//CallableStatement를 사용하면 좋을 것 같음.
	public void insert(String cartegory, String title, String content, String writer) {
		try {
			String query = "INSERT INTO POST(NUM, CATEGORY, TITLE, CONTENT, WRITER) "
					+ "VALUES(SEO_POST_NUM.NEXTVAL, ?, ?, ?, ?)";
			System.out.println("SQL : " + query);
			psmt = con.prepareStatement(query);	
			
			Clob contentTmp = con.createClob();
			contentTmp.setString(1, content);
			
			psmt.setString(1, cartegory);
			psmt.setString(2, title);
			psmt.setClob(3, contentTmp);
			psmt.setString(4, writer);
			psmt.executeUpdate();
			
			contentTmp.free();
			psmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public String contentStr(Clob content) {
		String str = "";
		try {
	      StringBuffer output = new StringBuffer();
	      Reader input = content.getCharacterStream();
	      char[] buffer = new char[1024];
	      int byteRead;
	      while((byteRead=input.read(buffer,0,1024))!=-1){
	          output.append(buffer,0,byteRead);
	      }
		
	      str = output.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public void likeAction(long postNum, String userId) {
		likeAction(con, psmt, "LIKE_POST_TB", postNum, userId);
	}
	
	// 더미 게시글 생성
	private void dummyInsert(int i) throws SQLException {
		String[] cartegorys = new String[] {"질문", "에러", "자유"};
		String title = "더미 게시글 "+i;
		String content = "이게 질문인가"+ i;
		String writer = "dks2312";
		
		int cartegoryRandom = (int)(Math.random() * cartegorys.length);
		int visitCount = (int)(Math.random() * 10000);
		
		Calendar cal = Calendar.getInstance();
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm");
		cal.add(Calendar.DATE, -(int)(Math.random() * 1000));
		String postDate = df.format(cal.getTime());
		
		
		String query = "INSERT INTO POST(NUM, CATEGORY, TITLE, CONTENT, WRITER, VISIT_COUNT, POST_DATE) "
				+ "VALUES(SEO_POST_NUM.NEXTVAL, '"+ cartegorys[cartegoryRandom] + "', '"+ title + "', '"+ content + "', '"+ writer + "', "
					+ "'"+ visitCount + "', " + "to_date('"+ postDate +"','YYYY/MM/DD HH24:MI'))";
		System.out.println("SQL : " + query);
		
		stmt.executeQuery(query);
	}	

	public static void main(String[] args) {
		PostDAO dao = new PostDAO();
		
		for(int i = 1; i <= 1000; i++) {
			try { dao.dummyInsert(i); }
			catch (Exception e) { break; }
		}
		System.out.println("더이상 Insert 할 수 없습니다!!\n");
	}
}







