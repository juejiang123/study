package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserTest {

    @Test(dependsOnGroups = "loginTrue",description = "更新user")
    public void updateUser() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();//???
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase",1);
        System.out.println(updateUserInfoCase);
        System.out.println(TestConfig.updateUserInfoUrl);

        int result = getResult(updateUserInfoCase);

        User user = session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);



    }

    @Test(dependsOnGroups = "loginTrue",description = "删除user数据")
    public void deleteUser() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();//???
        UpdateUserInfoCase updateUserInfoCase = session.selectOne("updateUserInfoCase",2);
        System.out.println(updateUserInfoCase);
        System.out.println(TestConfig.updateUserInfoUrl);

        int result = getResult(updateUserInfoCase);

        User user = session.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);

    }

    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        post.setHeader("content-type","application/json");
        JSONObject param = new JSONObject();
        param.put("id",updateUserInfoCase.getUserId());
        param.put("userName",updateUserInfoCase.getUserName());
        param.put("age",updateUserInfoCase.getAge());
        param.put("permission",updateUserInfoCase.getPermission());
        param.put("sex",updateUserInfoCase.getSex());
        param.put("isDelete",updateUserInfoCase.getIsDelete());
        StringEntity entity =new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return Integer.parseInt(result);
    }
}
