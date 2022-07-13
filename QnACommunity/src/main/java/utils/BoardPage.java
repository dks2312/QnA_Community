package utils;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
		String pagingStr = "";
		
		int totalPages = (int)(Math.ceil((double) totalCount / pageSize));
		
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		
		if(pageTemp != 1) {
			pagingStr += "<a href='"+ reqUrl +"?pageNum="+ pageTemp +"' class=\"page_number_btn\">[첫 블록]</a>";
		}
		
		int blockCount = 1;
		while(blockCount <= blockPage && pageTemp <= totalPages) {
			if(pageTemp == pageNum) {
				pagingStr += "&nbsp;<a href='"+ reqUrl + "?pageNum=" + pageTemp +"' class=\"page_number_btn on\" >" + pageTemp +"</a>&nbsp;";
			}
			else {
				pagingStr += "&nbsp;<a href='"+ reqUrl + "?pageNum=" + pageTemp +"' class=\"page_number_btn\" >" + pageTemp +"</a>&nbsp;";
			}
			
			pageTemp++;
			blockCount++;
		}
		
		if(pageTemp <= totalPages) {
			pagingStr += "<a href='"+ reqUrl +"?pageNum="+ pageTemp +"' class=\"page_number_btn\">[마지막 블록]</a>";
		}
		
		return pagingStr;
	}
}
