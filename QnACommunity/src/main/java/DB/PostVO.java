package DB;

import java.sql.Clob;

import utils.SimlpDate;

public class PostVO implements SimlpDate{
	private long num;	// 게시글의 일련 번호로 DB에서 관리됨.
	private String cartegory;
	private String title;
	private Clob content;
	private String writer;
	private int visitCount = 0;
	
	private String postDate;	// 날짜 데이터
	
	// 게시글 내용 제외
	public PostVO(long num, String cartegory, String title, String writer, 
			int visitCount, String postDate) {
		this.num = num;
		this.cartegory = cartegory;
		this.title = title;
		this.writer = writer;
		this.postDate = postDate;
		this.visitCount = visitCount;
	}
	public PostVO(long num, String cartegory, String title, Clob content, String writer, 
			int visitCount, String postDate) {
		this.num = num;
		this.cartegory = cartegory;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.postDate = postDate;
		this.visitCount = visitCount;
	}
	
	
	public void setNum(long num) 				{ this.num = num; }	
	public void setCartegory(String cartegory) 	{ this.cartegory = cartegory; }
	public void setTitle(String title) 			{ this.title = title; }
	public void setContent(Clob content) 		{ this.content = content; }
	public void setWriter(String writer) 		{ this.writer = writer; }
	public void setVisitCount(int visitCount) 	{ this.visitCount = visitCount; }
	public void setPostDate(String postDate) 	{ this.postDate = postDate; }
	
	public long getNum() 			{ return num; }
	public String getCartegory()	{ return cartegory; }
	public String getTitle() 		{ return title; }
	public Clob getContent() 		{ return content; }
	public String getWriter() 		{ return writer; }
	public int getVisitCount() 		{ return visitCount; }
	public String getPostDate() 	{ return postDate; }
	
	
	public String getSimlpDate() {
		return getSimlpDate(this.postDate);
	}
}
