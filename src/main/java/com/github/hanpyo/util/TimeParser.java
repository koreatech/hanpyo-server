package com.github.hanpyo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TimeParser {
    private static Map<String, Integer> days = new HashMap<String, Integer>() {{
        put("월", 540);
        put("화", 1980);
        put("수", 3420);
        put("목", 4860);
        put("금", 6300);
        put("토", 7740);
        put("일", 9180);
    }};

    private static Map<String, Integer> alphas = new HashMap<String, Integer>() {{
        put("A", 0);
        put("B", 30);
    }};

    public static String parseTimeString(String time) {
        if (time == null) return "";

        List<String> timeList = new ArrayList<>();

        String prevDay = "";
        for (String tm : time.split(",")) {
            if (tm.length() != 8) tm = prevDay + tm;
            prevDay = tm.substring(0, 1);

            Integer dayTime = days.get(tm.substring(0, 1));
            Integer sTime = (Integer.parseInt(tm.substring(1, 3)) - 1) * 60 + alphas.get(tm.substring(3, 4));
            Integer eTime = (Integer.parseInt(tm.substring(5, 7)) - 1) * 60 + alphas.get(tm.substring(7, 8)) + 30;

            Integer startTime = dayTime + sTime;
            Integer endTime = dayTime + eTime;

            timeList.add("{\"start\" : "+startTime+", \"end\" : "+endTime+"}");
        }

        return timeList.toString();
    }
}
