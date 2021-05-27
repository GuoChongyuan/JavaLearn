package dates;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTest {
    public static void main(String[] args) {
        System.out.println( "本机默认时区：" + ZoneId.systemDefault()) ;

        //系统默认时区的当前时间
        LocalDateTime localDateTime = LocalDateTime.now() ;
        System.out.println("本地当前时间：" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSS") .format(localDateTime));

        //根据本地时间计算指定时区的当地时间
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Chongqing")) ;
        System.out.println(zonedDateTime.getOffset() + "--> " +DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSS") .format(zonedDateTime));

        ZonedDateTime zonedDateTime1 = localDateTime.atZone(ZoneId.of("Europe/Paris")) ;
        System.out.println("Europe/Paris" + zonedDateTime.getOffset() + "--> " +DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSS") .format(zonedDateTime1));

        //给时间添加上指定的时区
        ZonedDateTime zonedDate = ZonedDateTime.of(localDateTime, ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDate.getOffset() + "--> " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSS") .format(zonedDate));

        ZonedDateTime hkZonedDate = ZonedDateTime.of(localDateTime, ZoneId.of("Europe/Paris"));
        System.out.println("Europe/Paris" + hkZonedDate.getOffset() + "--> " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSS") .format(hkZonedDate));

        ZonedDateTime gmtZonedDate = ZonedDateTime.of(localDateTime, ZoneId.of("GMT"));
        System.out.println(gmtZonedDate.getOffset() + "--> " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSS") .format(gmtZonedDate));

        //将ZonedDateTime转换为LocalDateTime
        LocalDateTime gmt2LocalDateTime = LocalDateTime.from(gmtZonedDate) ;
        System.out.println("本地当前时间：" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSS") .format(gmt2LocalDateTime));

        //计算两个带有时区时间的相差时间（时差）
        long hour = ChronoUnit.HOURS.between(zonedDate,gmtZonedDate) ;
        System.out.println("间隔小时：" + hour);

        //指定偏移量后的时间
        Instant instant = localDateTime.toInstant(ZoneOffset.of("+12")) ;
        System.out.println("指定偏移量后的时间:" + DateTimeFormatter.ISO_INSTANT.format(instant));
    }
}
