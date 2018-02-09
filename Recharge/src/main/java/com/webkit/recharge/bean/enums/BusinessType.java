package com.webkit.recharge.bean.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HH on 2018/1/23.
 */
public enum BusinessType {

    CALLFARE(100, "话费"),
    DATAFLOW(200, "流量"),
    OTHER(300, "其他");

    private int code;
    private String name;

    BusinessType(int code, String name) {
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
            case 100:
                value = CALLFARE.getName();
                break;
            case 200:
                value = DATAFLOW.getName();
                break;
            case 300:
                value = OTHER.getName();
            default:
                break;
        }
        return value;
    }
}
