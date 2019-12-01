package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
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

public class GetUserListTest {

    @Test(dependsOnGroups = "loginTrue",description ="获取用户列表")
    public void getUserList() throws IOException {

        SqlSession session = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase = session.selectOne("getUserListCase",1);
        System.out.println(getUserListCase);
        System.out.println(TestConfig.getUserListUrl);

        JSONArray resultjson = getResult(getUserListCase);
        List<User> userList = session.selectList(getUserListCase.getExpected(),getUserListCase);
        for(User user : userList){
            System.out.println("获取的user"+user.toString());
        }
        JSONArray userListJson = new JSONArray(userList);
        Assert.assertEquals(resultjson.length(),userListJson.length());

        for (int i=0;i<resultjson.length();i++){
            JSONObject expect = (JSONObject) userListJson.get(i);
            JSONObject actual = (JSONObject) resultjson.get(i);
            Assert.assertEquals(actual.toString(),expect.toString());
        }


    }

    private JSONArray getResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        post.setHeader("content-type","application/json");
        JSONObject param = new JSONObject();
//        param.put("id",getUserListCase.getId());
        param.put("userName",getUserListCase.getUserName());
        param.put("age",getUserListCase.getAge());
        param.put("sex",getUserListCase.getSex());
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
//        List resultjson = Arrays.asList(result);
        JSONArray array = new JSONArray(result);
        return array;

    }

}
