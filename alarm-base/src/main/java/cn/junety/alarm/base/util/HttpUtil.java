package cn.junety.alarm.base.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
 
import java.io.IOException;
 
public class HttpUtil {
  /**
   * get请求
   * @param url
   * @return
   * @throws ParseException
   * @throws IOException
   *
   * @author vision
   */
  public static JSONObject doGet(String url) throws ParseException, IOException {
    CloseableHttpClient client = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(url);
    JSONObject jsonObject = null;
    HttpResponse httpResponse = client.execute(httpGet);
    HttpEntity entity = httpResponse.getEntity();
    if(entity != null){
      String result = EntityUtils.toString(entity,"UTF-8");
      jsonObject = JSONObject.parseObject(result);
    }
    httpGet.releaseConnection();
    client.close();
    return jsonObject;
  }
  /**
   * POST请求
   * @param url
   * @param outStr
   * @return
   * @throws ParseException
   * @throws IOException
   */
  public static JSONObject doPost(String url, String outStr) throws ParseException, IOException {
    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost httpost = new HttpPost(url);

    httpost.setEntity(new StringEntity(outStr,"UTF-8"));
    HttpResponse response = client.execute(httpost);
    String result = EntityUtils.toString(response.getEntity(),"UTF-8");
    httpost.releaseConnection();
    client.close();
    return JSONObject.parseObject(result);
  }
}

