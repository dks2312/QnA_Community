package utils;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
		String pagingStr = "";
		
		int totalPages = (int)(Math.ceil((double) totalCount / pageSize));
		
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		
		pagingStr += "<a href='"+ reqUrl +"?pageNum="+ 1 +"' class=\"page_number_btn "+ ((pageTemp != 1) ? "":"off") +"\">&lt;&lt;&lt;</a>";
		pagingStr += "<a href='"+ reqUrl +"?pageNum="+ (pageTemp - 1) +"' class=\"page_number_btn "+ ((pageTemp != 1) ? "":"off") +"\">&lt;</a>";
		
		int blockCount = 1;
		while(blockCount <= blockPage && pageTemp <= totalPages) {
			if(pageTemp == pageNum) {
				pagingStr += "<a href='"+ reqUrl + "?pageNum=" + pageTemp +"' class=\"page_number_btn on\" >" + pageTemp +"</a>";
			}
			else {
				pagingStr += "<a href='"+ reqUrl + "?pageNum=" + pageTemp +"' class=\"page_number_btn\" >" + pageTemp +"</a>";
			}
			
			pageTemp++;
			blockCount++;
		}
		
		pagingStr += "<a href='"+ reqUrl +"?pageNum="+ pageTemp +"' class=\"page_number_btn "+ ((pageTemp <= totalPages) ? "":"off") +"\">&gt;</a>";
		pagingStr += "<a href='"+ reqUrl +"?pageNum="+ totalPages +"' class=\"page_number_btn "+ ((pageTemp + blockPage <= totalPages) ? "":"off") +"\">&gt;&gt;&gt;</a>";
		
		return pagingStr;
	}
}
