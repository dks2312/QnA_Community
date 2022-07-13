package DB;

public class PostVO {
	private long num;	// 게시글의 일련 번호로 DB에서 관리됨.
	private String cartegory;
	private String title;
	private String content;
	private String writer;
	
	private int visitCount = 0;
	private int likeCount = 0;
	private int commentCount = 0;
	
	private String postDate;	// 날짜 데이터
	
	public PostVO(String cartegory, String title, String content, String writer) {
		this.cartegory = cartegory;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	public PostVO(long num, String cartegory, String title, String content, String writer, 
			int visitCount, int likeCount, int commentCount, String postDate) {
		this.num = num;
		this.cartegory = cartegory;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.postDate = postDate;
		this.visitCount = visitCount;
		this.likeCount = likeCount;
		this.commentCount = commentCount;
	}
	
	
//	public void setNum(long num) 				{ this.num = num; }	일련번호라서 바꾸기 까다로움
	public void setCartegory(String cartegory) 	{ this.cartegory = cartegory; }
	public void setTitle(String title) 			{ this.title = title; }
	public void setContent(String content) 		{ this.content = content; }
	public void setWriter(String writer) 		{ this.writer = writer; }
	public void setVisitCount(int visitCount) 	{ this.visitCount = visitCount; }
	public void setLikeCount(int likeCount) 	{ this.likeCount = likeCount; }
	public void setCommentCount(int commentCount) 	{ this.commentCount = commentCount; }
	public void setPostDate(String postDate) 	{ this.postDate = postDate; }
	
	public long getNum() 			{ return num; }
	public String getCartegory()	{ return cartegory; }
	public String getTitle() 		{ return title; }
	public String getContent() 		{ return content; }
	public String getWriter() 		{ return writer; }
	public int getVisitCount() 		{ return visitCount; }
	public int getLikeCount() 		{ return likeCount; }
	public int getCommentCount() 	{ return commentCount; }
	public String getPostDate() 	{ return postDate; }

	public void print() {
		System.out.println( "PostVO [num=" + num + ", cartegory=" + cartegory + ", title=" + title + ", content=" + content
				+ ", writer=" + writer + ", visitCount=" + visitCount + ", likeCount=" + likeCount + ", commentCount="
				+ commentCount + ", postDate=" + postDate + "]");
	}
}
