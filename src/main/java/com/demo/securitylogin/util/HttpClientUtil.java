package com.demo.securitylogin.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

/**
 * @author YYGang
 * @date 2020/10/22
 * @desc Http请求
 */
@SuppressWarnings("deprecation")
public class HttpClientUtil {

    /**
     * @param url
     * @param reqType
     * @param mapParams
     * @param mapHeader
     * @param auth
     * @return
     */
    public static String httpRequest(String url, String reqType, Map<String, Object> mapParams, Map<String, String> mapHeader, String ...auth) {
        //接口返回结果
        String resResult = null;
        try {
            String parameters = "";
            for (String key : mapParams.keySet()) {
                parameters += key + "=" + URLEncoder.encode(mapParams.get(key).toString(), "UTF-8") + "&";
            }
            if (parameters.length() > 0) {
                parameters = parameters.substring(0, parameters.length() - 1);
            }
            //创建HttpClient连接对象
            DefaultHttpClient client = new DefaultHttpClient();
            HttpRequestBase method = null;
            StringEntity entity = null;
            HttpPost httpPost = null;
            switch (reqType) {
                case "get":
                    url += "?" + parameters;
                    method = new HttpGet(url);
                    break;
                case "post":
                    method = new HttpPost(url);
                    method.addHeader("Content-Type", "application/x-www-form-urlencoded");
                    httpPost = (HttpPost) method;
                    entity = new StringEntity(parameters);
                    httpPost.setEntity(entity);
                    break;
                case "postJson":
                    method = new HttpPost(url);
                    httpPost = (HttpPost) method;
                    method.addHeader("Content-Type", "application/json");
                    entity = new StringEntity(JSON.toJSONString(mapParams), "utf-8");
                    httpPost.setEntity(entity);
                    break;
                case "put":
                    method = new HttpPut(url);
                    HttpPut putMethod = (HttpPut) method;
                    entity = new StringEntity(parameters);
                    putMethod.setEntity(entity);
                    break;
                case "delete":
                    url += "?" + parameters;
                    method = new HttpDelete(url);
                    break;
            }
            if (!Objects.isNull(mapHeader)) {
                for (String s : mapHeader.keySet()) {
                    method.addHeader(s, mapHeader.get(s));
                }
            }

            method.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 6000);
            HttpClientContext context = null;
            if (!(auth == null || auth.length == 0)) {
                String username = auth[0];
                String password = auth[1];
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
                CredentialsProvider provider = new BasicCredentialsProvider();
                provider.setCredentials(AuthScope.ANY, credentials);
                context = HttpClientContext.create();
                context.setCredentialsProvider(provider);
            }
            HttpResponse response = client.execute(method, context);
            if (response.getStatusLine().getStatusCode() == 200) {
                resResult = EntityUtils.toString(response.getEntity());
            }
            client.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resResult;
    }
}