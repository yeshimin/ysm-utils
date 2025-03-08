package com.yeshimin.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YsmUtils {

    /**
     * LocalDateTime转Date
     */
    public static Date date(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            throw new IllegalArgumentException("localDateTime cannot be null");
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 按日期范围生成每一个日期
     */
    public static List<LocalDate> dates(LocalDate beginDate, LocalDate endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("beginDate and endDate cannot be null");
        }
        return Stream.iterate(beginDate, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(beginDate, endDate.plusDays(1)))
                .collect(Collectors.toList());
    }

    /**
     * 对半拆分map，奇数的话，前面的map中多一个
     * 比如：{a=1, b=2, c=3, d=4, e=5}，拆分为：{a=1, b=2, c=3}，{d=4, e=5}
     */
    public static <K, V> List<Map<K, V>> splitMap(Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            return Arrays.asList(new LinkedHashMap<>(), new LinkedHashMap<>());
        }

        Map<K, V> map1 = new LinkedHashMap<>();
        Map<K, V> map2 = new LinkedHashMap<>();

        int mid = (map.size() + 1) / 2;
        int count = 0;

        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (count < mid) {
                map1.put(entry.getKey(), entry.getValue());
            } else {
                map2.put(entry.getKey(), entry.getValue());
            }
            count++;
        }

        return Arrays.asList(map1, map2);
    }

    /**
     * 对半拆分集合，奇数的话，前面的集合中多一个
     * 比如：[1, 2, 3, 4, 5]，拆分为：[1, 2, 3]，[4, 5]
     */
    public static <T> List<Collection<T>> splitCollection(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return Arrays.asList(new ArrayList<>(), new ArrayList<>());
        }

        List<T> list1 = new ArrayList<>();
        List<T> list2 = new ArrayList<>();

        int mid = (collection.size() + 1) / 2;
        int count = 0;

        for (T item : collection) {
            if (count < mid) {
                list1.add(item);
            } else {
                list2.add(item);
            }
            count++;
        }

        return Arrays.asList(list1, list2);
    }
}