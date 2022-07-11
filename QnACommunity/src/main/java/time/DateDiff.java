package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// 그냥 긁어온 코드라서 몰루..
public class DateDiff {
	public int GetDifferenceOfDate(int nYear1, int nMonth1, int nDate1, int nYear2, int nMonth2, int nDate2) {
		Calendar cal = Calendar.getInstance();
		int nTotalDate1 = 0, nTotalDate2 = 0, nDiffOfYear = 0, nDiffOfDay = 0;

		if (nYear1 > nYear2) {
			for (int i = nYear2; i < nYear1; i++) {
				cal.set(i, 12, 0);
				nDiffOfYear += cal.get(Calendar.DAY_OF_YEAR);
			}
			nTotalDate1 += nDiffOfYear;
		} else if (nYear1 < nYear2) {
			for (int i = nYear1; i < nYear2; i++) {
				cal.set(i, 12, 0);
				nDiffOfYear += cal.get(Calendar.DAY_OF_YEAR);
			}
			nTotalDate2 += nDiffOfYear;
		}

		cal.set(nYear1, nMonth1 - 1, nDate1);
		nDiffOfDay = cal.get(Calendar.DAY_OF_YEAR);
		nTotalDate1 += nDiffOfDay;

		cal.set(nYear2, nMonth2 - 1, nDate2);
		nDiffOfDay = cal.get(Calendar.DAY_OF_YEAR);
		nTotalDate2 += nDiffOfDay;

		return nTotalDate1 - nTotalDate2;
	}

	public static void main(String args[]) {
		Calendar cal = Calendar.getInstance();
		System.out.println("" + new DateDiff().GetDifferenceOfDate(2022, 7, 9, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE)));
		try {
			Calendar.getInstance().setTime((new SimpleDateFormat("yyyy-MM-dd")).parse("2022-10-26"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar nowTime = Calendar.getInstance();
//		
//		Date format1 = new SimpleDateFormat("yyyy/MM/dd").format(nowTime.getTime());
//        Date format2 = new SimpleDateFormat("yyyy/MM/dd").parse("2022-10-26");
//		
//		try {
//			targetTime.setTime((new SimpleDateFormat("yyyy-MM-dd")).parse("2022-10-26"));
//			long studentTime = (format1.getTime() - format2.getTime()) / 1000 / (24*60*60);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
