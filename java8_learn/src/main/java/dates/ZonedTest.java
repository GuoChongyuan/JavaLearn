package dates;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedTest {
    public static void main(String[] args) {
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZonedDateTime date2 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Europe/Paris]");
        System.out.println("date2: " + date2);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }
}
