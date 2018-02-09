package com.webkit.recharge.bean.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HH on 2018/1/23.
 */
public enum PackageType {

    GENERAL(100, "普通"),
    SPECIAL(200, "本地特色");

    private int code;
    private String name;

    PackageType(int code, String name) {
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
                value = GENERAL.getName();
                break;
            case 200:
                value = SPECIAL.getName();
                break;
            default:
                break;
        }
        return value;
    }


}
