package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {
    
    @Test(dependsOnGroups = "loginTrue",description = "获取用户数据")
    public void getUserInfo() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserinfoCase",2);
        System.out.println(getUserInfoCase);
        System.out.println(getUserInfoCase.toString());
        System.out.println(TestConfig.getUserInfoUrl);



        JSONArray resultjson = getJsonResult(getUserInfoCase);
//        String resultjson = getJsonResult(getUserInfoCase);
        System.out.println(resultjson);
        User user = session.selectOne(getUserInfoCase.getExpected(),getUserInfoCase);
        List userList = new ArrayList();
        userList.add(user);
        JSONArray jsonArray = new JSONArray(userList);
        JSONArray jsonArray1= new JSONArray(resultjson.getString(0));
        System.out.println(jsonArray1);
        Assert.assertEquals(jsonArray.toString(),jsonArray1.toString());
//        Assert.assertEquals(jsonArray.toString(),resultjson.toString());
    }

    private JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        post.setHeader("content-type","application/json");
        JSONObject param = new JSONObject();
        param.put("id",getUserInfoCase.getUserId());
        StringEntity entity =new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        // 把响应体中的字符串转换成json对象
        List resultList = Arrays.asList(result);
        JSONArray array = new JSONArray(resultList);

        return array;
    }
}
