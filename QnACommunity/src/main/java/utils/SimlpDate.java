package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface SimlpDate {
	public default String getSimlpDate(String date) {
		String[] unitStr = new String[] {"초", "분", "시", "일", "개월", "년"};
		int[] unitInt = new int[] {60, 60, 24, 30, 12};
		int uni = 0;
		
		try {
			Date now = new Date();
			Date postDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		
			long diff = (now.getTime() - postDate.getTime()) / 1000;
			
			for(uni = 0; uni < unitInt.length && diff >= unitInt[uni]; uni++) {
				diff /= unitInt[uni];
			}
			
			return diff + unitStr[uni] + " 전";
		
		} catch (ParseException e) {
			System.out.println("날짜를 변환하는 도중 오류가 발생했습니다");
		}
		
		return "???";
	}
}
