package cn.junety.alarm.sender.util;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import cn.junety.alarm.sender.entity.AccessToken;
 
import java.io.IOException;
 
public class WechatUtil {

  public static String ACCESS_TOKEN_JSON_FILE = "/data/access_token.json";

  public static AccessToken getAccessTokenFromFile() {
    try {
      InputStream is = new FileInputStream(ACCESS_TOKEN_JSON_FILE);
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
}

