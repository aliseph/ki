package com.ingta.framework.util.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-10-24 上午10:50:36 说明：
 */
public class StringToDateConverter implements Converter<String, Date> {

    private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");

    @Override
    public Date convert(String text) {
        if (!StringUtils.hasLength(text)) {
            // ①如果text为空 返回null
            return null;
        }
        try {
            if (text.indexOf(":") == -1 && text.length() == 10) {
                return this.dateFormat.parse(text);
            } else if (text.indexOf(":") == -1 && text.length() == 7) {
                return this.monthFormat.parse(text);
            } else if (text.indexOf(":") > 0 && text.length() == 19) {
                return this.datetimeFormat.parse(text);
            } else {
                throw new IllegalArgumentException("Could not parse date, date format is error ");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Could not parse date: "
                    + e.getMessage(), e);
        }
    }
}
