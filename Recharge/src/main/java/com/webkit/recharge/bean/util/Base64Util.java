package com.webkit.recharge.bean.util;

import java.util.Base64;

/**
 * Created by HH on 2018/1/25.
 */
public class Base64Util {
    public static String base64Encoder(String string) throws Exception{
        final Base64.Encoder encoder = Base64.getEncoder();
        String base64String = "";
        try {
            final byte[] bytes = string.getBytes("UTF-8");
            base64String = encoder.encodeToString(bytes);
        }catch (Exception e){
            e.printStackTrace();
            base64String = "base64 编码错误！";
        }
        return base64String;
    }

    public static byte[] base64Encoder(byte[] bytes) throws Exception{
        final Base64.Encoder encoder = Base64.getEncoder();
        byte[] base64Bytes = new byte[0];
        try {
            base64Bytes = encoder.encode(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return base64Bytes;
    }

    public static String base64Decoder(String string) throws Exception{
        final Base64.Decoder decoder = Base64.getDecoder();
        String decoderString = "";
        try {
            decoderString = new String(decoder.decode(string),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
            decoderString = "base64 解码错误！";
        }
        return decoderString;
    }
}
