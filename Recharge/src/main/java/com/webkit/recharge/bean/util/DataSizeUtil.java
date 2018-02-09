package com.webkit.recharge.bean.util;

import com.mchange.lang.IntegerUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by HH on 2018/1/23.
 */
public class DataSizeUtil {
    public static Integer getSizeString(String sizeString){
        Integer defaultValue = 1048576;
        String s = StringUtils.replaceEach(sizeString,new String[]{"m", "g"}, new String[]{"M", "G"});

        if (StringUtils.contains(s, "M")){
            return NumberUtils.toInt(StringUtils.substringBefore(s, "M"));
        }

        if (StringUtils.contains(s, "G")){
            return NumberUtils.toInt(StringUtils.substringBefore(s, "G")) * 1024;
        }
        return defaultValue;
//
//        if (StringUtils.containsIgnoreCase(sizeString, "G")){
//
//        }
    }
}
