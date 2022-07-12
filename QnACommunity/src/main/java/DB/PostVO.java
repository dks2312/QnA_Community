package DB;

public class PostVO {
	private String num;
	private String cartegory;
	private String title;
	private String content;
	private String writer;
	
	private int visitCount = 0;
	private int likeCount = 0;
	private int commentCount = 0;
	
	// 날짜 데이터
	private String postDate;
	
	public PostVO(String num, String cartegory, String title, String content, String writer) {
		this.num = num;
		this.cartegory = cartegory;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	public PostVO(String num, String cartegory, String title, String content, String writer, 
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
	
	
	public void setNum(String num) 				{ this.num = num; }
	public void setCartegory(String cartegory) 	{ this.cartegory = cartegory; }
	public void setTitle(String title) 			{ this.title = title; }
	public void setContent(String content) 		{ this.content = content; }
	public void setWriter(String writer) 		{ this.writer = writer; }
	public void setVisitCount(int visitCount) 	{ this.visitCount = visitCount; }
	public void setLikeCount(int likeCount) 	{ this.likeCount = likeCount; }
	public void setCommentCount(int commentCount) 	{ this.commentCount = commentCount; }
	public void setPostDate(String postDate) 	{ this.postDate = postDate; }
	
	public String getNum() 			{ return num; }
	public String getCartegory()	{ return cartegory; }
	public String getTitle() 		{ return title; }
	public String getContent() 		{ return content; }
	public String getWriter() 		{ return writer; }
	public int getVisitCount() 		{ return visitCount; }
	public int getLikeCount() 		{ return likeCount; }
	public int getCommentCount() 	{ return commentCount; }
	public String getPostDate() 	{ return postDate; }
}
