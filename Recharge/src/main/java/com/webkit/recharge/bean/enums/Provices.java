package com.webkit.recharge.bean.enums;

/**
 * Created by HH on 2018/1/23.
 */
public enum Provices {

    ALL(0, "全国"),
    BEIJING(11, "北京"),
    TIANJIN(12, "天津"),
    HEBEI(13, "河北"),
    SHAN_1_XI(14, "山西"),
    NEIMENGGU(15, "内蒙古"),
    LIAONING(21, "辽宁"),
    JILIN(22, "吉林"),
    HEILONGJIANG(23, "黑龙江"),
    SHANGHAI(31, "上海"),
    JIANGSU(32, "江苏"),
    ZHEJIANG(33, "浙江"),
    ANHUI(34, "安徽"),
    FUJIAN(35, "福建"),
    JAINGXI(36, "江西"),
    SHANDONG(37, "山东"),
    HENAN(41, "河南"),
    HUBEI(42, "湖北"),
    HUNAN(43, "湖南"),
    GUAGNDONG(44, "广东"),
    GUANGXI(45, "广西"),
    HAINAN(46, "海南"),
    CHONGQING(50, "重庆"),
    SICHUAN(51, "四川"),
    GUIZHOU(52, "贵州"),
    YUNNAN(53, "云南"),
    XIZANG(54, "西藏"),
    SHAN_3_XI(61, "陕西"),
    GANSU(62, "甘肃"),
    QINGHAI(63, "青海"),
    NIGNXIA(64, "宁夏"),
    XINGJIANG(65, "新疆"),
    OTHER(500, "其他");

    private int code;
    private String name;

    Provices(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static int getCode(String name){
        int code = 0;
        switch (name){
            case "全国":
                code = ALL.getCode();
                break;
            case "北京":
                code = BEIJING.getCode();
                break;
            case "天津":
                code = TIANJIN.getCode();
                break;
            case "河北":
                code = HEBEI.getCode();
                break;
            case "山西":
                code = SHAN_1_XI.getCode();
                break;
            case "内蒙古":
                code = NEIMENGGU.getCode();
                break;
            case "辽宁":
                code = LIAONING.getCode();
                break;
            case "吉林":
                code = JILIN.getCode();
                break;
            case "黑龙江":
                code = HEILONGJIANG.getCode();
                break;
            case "上海":
                code = SHANGHAI.getCode();
                break;
            case "江苏":
                code = JIANGSU.getCode();
                break;
            case "浙江":
                code = ZHEJIANG.getCode();
                break;
            case "安徽":
                code = ANHUI.getCode();
                break;
            case "福建":
                code = FUJIAN.getCode();
                break;
            case "江西":
                code = JAINGXI.getCode();
                break;
            case "山东":
                code = SHANDONG.getCode();
                break;
            case "河南":
                code = HENAN.getCode();
                break;
            case "湖北":
                code = HUBEI.getCode();
                break;
            case "湖南":
                code = HUNAN.getCode();
                break;
            case "广东":
                code = GUAGNDONG.getCode();
                break;
            case "广西":
                code = GUANGXI.getCode();
                break;
            case "海南":
                code = HAINAN.getCode();
                break;
            case "重庆":
                code = CHONGQING.getCode();
                break;
            case "四川":
                code = SICHUAN.getCode();
                break;
            case "贵州":
                code = GUIZHOU.getCode();
                break;
            case "云南":
                code = YUNNAN.getCode();
                break;
            case "西藏":
                code = XIZANG.getCode();
                break;
            case "陕西":
                code = SHAN_3_XI.getCode();
                break;
            case "甘肃":
                code = GANSU.getCode();
                break;
            case "青海":
                code = QINGHAI.getCode();
                break;
            case "宁夏":
                code = NIGNXIA.getCode();
                break;
            case "新疆":
                code = XINGJIANG.getCode();
                break;
            default:
                code = OTHER.getCode();
                break;

        }
        return code;
    }

    public static String getDescription(Integer code) {
        String value = "";
        switch (code == null ? 0 : code) {
            case 0:
                value = ALL.getName();
                break;
            case 11:
                value = BEIJING.getName();
                break;
            case 12:
                value = TIANJIN.getName();
                break;
            case 13:
                value = HEBEI.getName();
                break;
            case 14:
                value = SHAN_1_XI.getName();
                break;
            case 15:
                value = NEIMENGGU.getName();
                break;
            case 21:
                value = LIAONING.getName();
                break;
            case 22:
                value = JILIN.getName();
                break;
            case 23:
                value = HEILONGJIANG.getName();
                break;
            case 31:
                value = SHANGHAI.getName();
                break;
            case 32:
                value = JIANGSU.getName();
                break;
            case 33:
                value = ZHEJIANG.getName();
                break;
            case 34:
                value = ANHUI.getName();
                break;
            case 35:
                value = FUJIAN.getName();
                break;
            case 36:
                value = JAINGXI.getName();
                break;
            case 37:
                value = SHANDONG.getName();
                break;
            case 41:
                value = HENAN.getName();
                break;
            case 42:
                value = HUBEI.getName();
                break;
            case 43:
                value = HUNAN.getName();
                break;
            case 44:
                value = GUAGNDONG.getName();
                break;
            case 45:
                value = GUANGXI.getName();
                break;
            case 46:
                value = HAINAN.getName();
                break;
            case 50:
                value = CHONGQING.getName();
                break;
            case 51:
                value = SICHUAN.getName();
                break;
            case 52:
                value = GUIZHOU.getName();
                break;
            case 53:
                value = YUNNAN.getName();
                break;
            case 54:
                value = XIZANG.getName();
                break;
            case 61:
                value = SHAN_3_XI.getName();
                break;
            case 62:
                value = GANSU.getName();
                break;
            case 63:
                value = QINGHAI.getName();
                break;
            case 64:
                value = NIGNXIA.getName();
                break;
            case 65:
                value = XINGJIANG.getName();
                break;
            default:
                value = OTHER.getName();
                break;
        }
        return value;
    }
}
