package Common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


public class TimeUtil {

    public static String getElapsedTime(Timestamp writeDate) {
        long currentTime = System.currentTimeMillis(); // 현재 시간
        long writeTime = writeDate.getTime(); // 작성 시간
        long diff = currentTime - writeTime; // 시간 차이 (밀리초 단위)

        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        long hours = TimeUnit.MILLISECONDS.toHours(diff);

        if (minutes < 1) {
            return "방금 전";
        } else if (minutes < 5) {
            return "5분 이내";
        } else if (hours < 1) {
            return "1시간 이내";
        } else {
            return new SimpleDateFormat("yyyy-MM-dd").format(writeDate); // 날짜 형식으로 반환
        }
    }
    
    public static String getFileDate() {
    	return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
    
    public static Timestamp toTimestamp(String date) {
    	LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    	
    	return Timestamp.valueOf(localDateTime);
    }
    
    public static String toString(long millisec) {
    	int hours = 0;
    	int minutes = 0;
    	int sec = 0;
    	int milli = (int) millisec;
    	
    	if (millisec >= 1000) {    		
    		sec = (int) (millisec / 1000);
    		milli = (int) millisec % 1000;
    	}
    	
    	System.out.println(sec + " " + milli);
    	
    	if (sec >= 60) {
    		minutes = (int) (sec / 60);
    		sec = sec % 60;
    	}
    	
    	if (minutes >= 60) {
    		hours = (int) (minutes / 60);
    		minutes = minutes % 60;
    	}
    	
    	String smillisec = String.valueOf(milli);
    	if (milli < 100) {
    		smillisec = "0" + String.valueOf(milli);
    		
    		if (milli < 10) {
    			smillisec = "00" + String.valueOf(milli);
    		}
    	}
    	
    	return String.format("%d시간 %d분 %d.%s초", hours, minutes, sec, smillisec);
    }
}