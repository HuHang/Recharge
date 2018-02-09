package com.webkit.recharge.bean.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by HH on 2018/1/22.
 */
public class SumUtil {
    public static String getNumber(Double number){
        String str = "0.00";
        BigDecimal bigDecimal = new BigDecimal(new Double(number).toString());
        str = bigDecimal.toString();
        return str;
    }

    public static String getNumber(Double number, String format){
        String str = "0.00";
        DecimalFormat decimalFormat   = new DecimalFormat(format);
        str = decimalFormat.format(number);
        return str;
    }
}
