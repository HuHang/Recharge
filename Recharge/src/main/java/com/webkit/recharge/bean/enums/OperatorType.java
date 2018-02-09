package com.webkit.recharge.bean.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HH on 2018/1/24.
 */
public enum OperatorType {

    MOBILE(10086, "移动"),
    TELECOM(10000, "电信"),
    UNICOM(10010, "联通");

    private int code;
    private String name;

    OperatorType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static List<Map> getProperties() {
        List<Map> maps = new ArrayList<>();
        for (int i = 0; i < values().length; i++) {
            Map map = new HashMap();
            map.put("type", values()[i].getCode());
            map.put("typeDisplay", values()[i].getName());
            maps.add(map);
        }
        return maps;
    }

    public static String getDescription(Integer code) {
        String value = "";
        switch (code == null ? 0 : code) {
            case 10086:
                value = MOBILE.getName();
                break;
            case 10000:
                value = TELECOM.getName();
                break;
            case 10010:
                value = UNICOM.getName();
            default:
                break;
        }
        return value;
    }

    public static int getCode(String name) {
        int code = 0;
        switch (StringUtils.isBlank(name) ? "未知" : name) {
            case "移动":
                code = MOBILE.getCode();
                break;
            case "电信":
                code = TELECOM.getCode();
                break;
            case "联通":
                code = UNICOM.getCode();
            default:
                break;
        }
        return code;
    }
}
