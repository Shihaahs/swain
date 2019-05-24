package com.swain.core.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class DateUtil {

    public static final Long THREE_HOUR = 3 * 60 * 60 * 1000L;

    public static final Long ONE_HOUR = 60 * 60 * 1000L;

    public static final Long ONE_MINUTES = 60 * 1000L;

    public static final Long THIRTY_SECOND = 30 * 1000L;

    public static final Long ONE_DAY = 24 * 60 * 60 * 1000L;

    static final String[] WEEK_NAMES = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public static final String NORMAL_DATE = "yyyy-MM-dd";

    public static final String NORMAL_TIME = "HH:mm:ss";

    public static final String NORMAL_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String ZONE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    /**
     * 数据库中默认时间
     */
    public static final String DEFAULT_DATE_STR = "1990-01-01 00:00:00";

    /**
     * 返回指定时间格式的字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String parseToString(Date date, String pattern) {

        if (null == date || StringUtils.isEmpty(pattern)) {
            return StringUtils.EMPTY;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 返回带时区的时间格式字符串
     *
     * @param date
     * @return
     */
    public static String parseToZoneString(Date date) {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern(ZONE_PATTERN);
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        return dtf2.format(instant.atZone(zoneId));
    }

    /**
     * String转Date
     *
     * @param pattern
     * @param source
     * @return
     */
    public static Date convertToDate(String source, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(source);
        } catch (Exception e) {
            log.error("str转换date失败，不能把["+ source+"]转换成[" + pattern + "] - " + e.getMessage());
        }
        return null;
    }

    /**
     * 国际时区字符串 转 Date('yyyy-MM-dd')
     */
    public static Date convertToZoneDate(String source) {
        Date zonedDateTime = convertToDate(source, ZONE_PATTERN);
        Assert.notNull(zonedDateTime,source + "转换成" + ZONE_PATTERN + "异常");
        return formatToNormalDate(zonedDateTime);
    }



    /**
     * 国际时区Date 转 Date('yyyy-MM-dd')
     */
    public static Date formatToNormalDate(Date zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }


}
