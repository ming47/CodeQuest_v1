package Common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
}