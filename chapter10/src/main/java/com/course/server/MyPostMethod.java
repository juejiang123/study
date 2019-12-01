package com.course.server;

import com.course.Bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/v1",description = "这是我全部的post请求")
//@RequestMapping("/v1")
public class MyPostMethod {

    private static Cookie cookie;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value="登录接口，成功后获取Cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value="userName",required = true) String userName,
                        @RequestParam(value="Password",required = true) String Password
                        ){
        if(userName.equals("zhangsan")&&Password.equals("123456")){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "恭喜你登录成功";
        }
        return "用户名或者密码错误";
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u){

        Cookie[] cookie = request.getCookies();
        for(Cookie c : cookie){
            if (c.getName().equals("login")
                    && c.getValue().equals("true")
                    && u.getUserName().equals("zhangsan")
                    && u.getPassword().equals("123456")) {
                User user = new User();
                user.setName("lisi");
                user.setAge("18");
                user.setSex("man");
                user.setUserName("zhange");
                user.setPassword("65");
                return user.toString();
            }
        }
        return "参数不合法";


    }


}
