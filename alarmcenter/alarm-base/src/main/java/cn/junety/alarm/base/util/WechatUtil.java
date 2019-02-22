package cn.junety.alarm.base.util;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import cn.junety.alarm.base.entity.AccessToken;
 
import java.io.IOException;
 
public class WechatUtil {

  public static String   WX_APP_ID = "wx2b44d79da6a46efe";
  public static String   WX_APP_SECRET = "8c42dc03a29139f765de94feb75ae787";
  public static String   WX_TEMPLATE_ID = "LsEJZoMCrli5-YV7JN4cmQbNRMtOFe4FYmkgNU9EIhI";
  public static String[] WX_TEMPLATE_KEY = new String[]{"first", "remark", "keyword1", "keyword4", "keyword2", "keyword5", "keyword3"};
  public static String   WX_ACCESS_TOKEN_JSON_FILE = "/data/access_token.json";

  public static AccessToken getAccessTokenFromFile() {
    try {
      InputStream is = new FileInputStream(WX_ACCESS_TOKEN_JSON_FILE);
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      String line = reader.readLine(); // 读取第一行
      if (null != line) {
        return JSON.parseObject(line, new TypeReference<AccessToken>() {});
      }
      reader.close();
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return null;
  }

  public static String getWxopenidByCode(String code) {
    if (null == code) return null;

    String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSRC&code=CODE&grant_type=authorization_code";
    url = url.replace("CODE", code).
      replace("APPID",  WechatUtil.WX_APP_ID).
      replace("APPSRC", WechatUtil.WX_APP_SECRET);

    try {
      JSONObject ret = HttpUtil.doGet(url);
      return (String )ret.get("openid");
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }   
}

