package com.zhangyibin.aifunction;

/**
 * 类:BaiduFanyi
 * 作用：消息翻译
 */

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.lang.Math;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BaiduFanyi {

    private static Connection connection = null;
    private static Document document = null;
    private static final String appid = "";
    private static final String key = "";

    public static String getBaiduFanyi(String text) {

        int intMath = 0;
        String md5 = "";
        String returnData = "";
        try {
            intMath = (int) (Math.random() * 100);
            md5 = setMD5(appid + text + String.valueOf(intMath) + key);

            connection = Jsoup.connect("http://api.fanyi.baidu.com/api/trans/vip/translate");
            connection.header("Content-Type", "application/json");
            connection.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            connection.data("q", text);
            connection.data("from", "auto");
            connection.data("to", "auto");
            connection.data("appid", appid);
            connection.data("salt", String.valueOf(intMath));
            connection.data("sign", md5);
            connection.ignoreContentType(true);
            connection.userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
            connection.timeout(5000);

            document = connection.post();
            String strDocJosn = document.text();

            JSONObject jsonObject = JSONObject.fromObject(strDocJosn);
            JSONArray jsonArray = jsonObject.getJSONArray("trans_result");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                returnData = jsonObject1.getString("dst");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnData;

    }



    private static String setMD5(String strTxt) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(strTxt.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }


    public static void main(String[] args) throws Exception {

        System.out.println(BaiduFanyi.getBaiduFanyi("You aren't following anybody.\n" +
                "earn more about being social on GitHub"));
//        System.out.println(BaiduFanyi.getBaiduFanyi("can be downloadable"));
    }


}