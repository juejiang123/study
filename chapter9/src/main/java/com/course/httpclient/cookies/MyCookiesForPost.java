package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static jdk.internal.org.objectweb.asm.Type.getType;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    // 用来存储cookie信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("host");

    }

    @Test
    public void getCookieUri() throws IOException {
        //从配置文件中拼接测试的URL
        String result;
        String uri = this.url + bundle.getString("startcookie");
        //测试逻辑代码书写
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(uri);
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        //获得cookie信息
        this.store = client.getCookieStore();

    }

    @Test
    public void getCookieUri1() throws IOException {
        String uri = this.url + bundle.getString("startcookie");
        HttpGet get = new HttpGet(uri);
        DefaultHttpClient client = new DefaultHttpClient();
        client.execute(get);
        this.store = client.getCookieStore();
//        System.out.println(this.store);

    }

    @Test(dependsOnMethods = "getCookieUri1")
    public void postQuery() throws IOException {
        //拼接URL参数
        String result;
        String uri = this.url + bundle.getString("startpost");
        //声明一个方法和一个client对象
        HttpPost post = new HttpPost(uri);
        DefaultHttpClient client = new DefaultHttpClient();
        //json参数
        JSONObject param = new JSONObject();
        param.put("name", "huhansan");
        param.put("sex", "male");
        param.put("age", "35");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookie
        client.setCookieStore(this.store);
        //设置请求头信息
        post.setHeader("content-type","application/json");
        //执行请求，获得响应
        HttpResponse response = client.execute(post);

        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        JSONObject resultJson = new JSONObject(result);
        String success = (String) resultJson.get("name");
        String status = (String) resultJson.get("age");
        Assert.assertEquals(success,"huanghe");
        Assert.assertEquals(status,"14");

        int StatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(StatusCode,200);


    }
}