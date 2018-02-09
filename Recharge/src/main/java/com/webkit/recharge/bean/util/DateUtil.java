package com.webkit.recharge.bean.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HH on 2018/1/22.
 */
public class DateUtil {
    public static String getDate(Date date, String formatStr){
        String str = "";
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        str = format.format(date);
        return str;
    }
}
