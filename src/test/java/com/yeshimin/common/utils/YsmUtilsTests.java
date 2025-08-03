package com.yeshimin.common.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YsmUtilsTests {

    @Test
    public void testDateByDateStr() {
        LocalDate date = YsmUtils.date("2025-03-14");
        System.out.println(date);
        assert "2025-03-14".equals(date.toString());

        date = YsmUtils.date("2025-03");
        System.out.println(date);
        assert "2025-03-01".equals(date.toString());

        date = YsmUtils.date("2025");
        System.out.println(date);
        assert "2025-01-01".equals(date.toString());

        // error
        try {
            YsmUtils.date("2025-03-14 12:34:56");
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    /**
     * test for monthBegin
     */
    @Test
    public void testMonthBegin() {
        LocalDate date = LocalDate.of(2025, 3, 14);
        LocalDate monthBegin = YsmUtils.monthBegin(date);
        System.out.println(monthBegin);
        assert monthBegin.getDayOfMonth() == 1;
    }

    /**
     * test for monthEnd
     */
    @Test
    public void testMonthEnd() {
        LocalDate date = LocalDate.of(2025, 3, 14);
        LocalDate monthEnd = YsmUtils.monthEnd(date);
        System.out.println(monthEnd);
        assert monthEnd.getDayOfMonth() == 31;
    }

    /**
     * test for minTime
     */
    @Test
    public void testMinTime() {
        LocalDate date = LocalDate.of(2025, 3, 14);
        LocalDateTime minTime = YsmUtils.minTime(date);
        System.out.println(minTime);
        assert minTime.getHour() == 0;
        assert minTime.getMinute() == 0;
        assert minTime.getSecond() == 0;
    }

    /**
     * test for maxTime
     */
    @Test
    public void testMaxTime() {
        LocalDate date = LocalDate.of(2025, 3, 14);
        LocalDateTime maxTime = YsmUtils.maxTime(date);
        System.out.println(maxTime);
        assert maxTime.getHour() == 23;
        assert maxTime.getMinute() == 59;
        assert maxTime.getSecond() == 59;
    }

    /**
     * test for `path`
     */
    @Test
    public void testPath() {
        String path = YsmUtils.path("a", "b", "c", " ", null);
        System.out.println("path: " + path);
        assert "a/b/c".equals(path);

        String path2 = YsmUtils.path("4/5/6", null, path);
        System.out.println("path2: " + path2);
        assert "4/5/6/a/b/c".equals(path2);
    }
}
