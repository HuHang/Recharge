package com.webkit.recharge.bean.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HH on 2018/1/24.
 */
public enum OrderState {

    TEST(100, "测试"),
    CREATE(200, "订单新建"),
    INHAND(300, "处理中"),
    PAYING(301, "支付中"),
    RECHARGEING(302, "充值中"),
    UNKNOWN(400, "未知"),
    FAILURE(500, "失败"),
    FAILURE_PAY(501, "支付失败"),
    FAILURE_RECHARGE(502, "充值失败"),
    FAILURE_AETIFICAL(510, "手动失败"),
    SUCCESS(600, "成功"),
    SUCCESS_AETIFICAL(610, "手动成功");

    private int code;
    private String name;

    OrderState(int code, String name) {
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
                value = TEST.getName();
                break;
            case 200:
                value = CREATE.getName();
                break;
            case 300:
                value = INHAND.getName();
                break;
            case 301:
                value = PAYING.getName();
                break;
            case 302:
                value = RECHARGEING.getName();
                break;
            case 400:
                value = UNKNOWN.getName();
                break;
            case 500:
                value = FAILURE.getName();
                break;
            case 501:
                value = FAILURE_PAY.getName();
                break;
            case 502:
                value = FAILURE_RECHARGE.getName();
                break;
            case 510:
                value = FAILURE_AETIFICAL.getName();
                break;
            case 600:
                value = SUCCESS.getName();
                break;
            case 610:
                value = SUCCESS_AETIFICAL.getName();
                break;
            default:
                value = "不接受的状态值";
                break;
        }
        return value;
    }
}
