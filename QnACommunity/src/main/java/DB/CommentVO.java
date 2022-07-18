package DB;

public class CommentVO {
	private long post_FK;
	private String writerName;
	private String content;
	private String date;
	
	public CommentVO(long post_FK, String writerName, String content, String date) {
		this.post_FK = post_FK;
		this.writerName = writerName;
		this.content = content;
		this.date = date;
	}
	

	public void setPost_FK(long post_FK) 		{ this.post_FK = post_FK; }
	public void setWriterName(String writerName){ this.writerName = writerName; }
	public void setContent(String content) 		{ this.content = content; }
	
	public long getPost_FK() 		{ return post_FK; }
	public String getWriterName() 	{	return writerName; }
	public String getContent() 		{ return content; }
	public String getDate() 		{ return date; }
}