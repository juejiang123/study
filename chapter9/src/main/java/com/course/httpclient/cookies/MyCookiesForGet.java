package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class MyCookiesForGet {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("host");

    }

    @Test
    public void testGetUri() throws IOException {
        String result;
        String uri = this.url +bundle.getString("startcookie");
        HttpGet get = new HttpGet(uri);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
//        System.out.println(response);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        this.store = client.getCookieStore();
        List<Cookie> cookieList= store.getCookies();
        for(Cookie cookie:cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = "+name+";cookie value = "+value);
        }

    }

    @Test(dependsOnMethods = {"testGetUri"})
    public void testGetWithCookie() throws IOException {
        String result;
        String uri = this.url +bundle.getString("setwithcookies");
        HttpGet get = new HttpGet(uri);
        DefaultHttpClient client = new DefaultHttpClient();
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("状态码是"+statusCode);
        result = EntityUtils.toString(response.getEntity());
        System.out.println(result);


    }

}
