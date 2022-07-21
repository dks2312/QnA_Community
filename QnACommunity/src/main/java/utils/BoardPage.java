package utils;

import java.util.Map;
import java.util.Map.Entry;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl, Map<String, Object> map) {
		String pagingStr = "";
		
		StringBuilder addStrBuilder = new StringBuilder();
		for(Entry<String, Object> jogen :map.entrySet()) 
			addStrBuilder.append("&"+ jogen.getKey() +"="+ jogen.getValue());
		
		String addStr = addStrBuilder.toString();
		
		int totalPages = (int)(Math.ceil((double) totalCount / pageSize));
		
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		
		pagingStr += "<a href='"+ pageURL(reqUrl, 1, addStr) 			+"' class="+ pageClass(pageTemp != 1) +">&lt;&lt;&lt;</a>";
		pagingStr += "<a href='"+ pageURL(reqUrl, pageTemp - 1, addStr) +"' class="+ pageClass(pageTemp != 1) +">&lt;</a>";
		
		int blockCount = 1;
		while(blockCount <= blockPage && pageTemp <= totalPages) {
			if(pageTemp == pageNum) {
				pagingStr += "<a href='"+ pageURL(reqUrl, pageTemp, addStr) +"' class=\"page_number_btn on\" >" + pageTemp +"</a>";
			}
			else {
				pagingStr += "<a href='"+ pageURL(reqUrl, pageTemp, addStr) +"' class=\"page_number_btn\" >" + pageTemp +"</a>";
			}
			
			pageTemp++;
			blockCount++;
		}
				
		pagingStr += "<a href='"+ pageURL(reqUrl, pageTemp, addStr) 	+"' class="+ pageClass(pageTemp <= totalPages) +">&gt;</a>";
		pagingStr += "<a href='"+ pageURL(reqUrl, totalPages, addStr) 	+"' class="+ pageClass(pageTemp + blockPage <= totalPages) +">&gt;&gt;&gt;</a>";
		return pagingStr;
	}
	
	private static String pageURL(String reqUrl, int pageNum, String jogen) {
		return reqUrl +"?pageNum="+ pageNum + jogen;
	}
	
	private static String pageClass(boolean isOff) {
		return "\"page_number_btn "+ (isOff ? "":"off") +"\"";
	}
}
