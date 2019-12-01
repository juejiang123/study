package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我的全部get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getcookie",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个可以获取到cookies",httpMethod = "GET" )
    public String getCookies(HttpServletResponse response){

        //HttpServletRequest 装请求信息的类
        //HttpServletResponse  装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";
    }

    /**
     * 要求客户端携带cookie访问
     * 这是一个需要携带cookies信息才能访问的get请求
     * @return
     */

    @RequestMapping(value = "/set/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookie",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
//        Cookie[] cookies = {};
        if(Objects.isNull(cookies)){
            return "必须携带cookies来访问";

        }
        for (Cookie cookie : cookies){
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "这是一个带cookie的请求";
            }

        }
        return "必须携带cookies来访问";
    }

    /**
     * 开发一个需要携带参数才能访问的get请求。
     * 第一种实现方式 URL：key=value&key=value
     * 我们来模拟获取商品列表
     * */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",300);
        return myList;
    }

    /**
     * 第二种需要携带参数访问的get请求
     * URL：ip:port/get/with/param/10/20
     */

    @RequestMapping(value = "/get/with/other/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数的第二种方法",httpMethod = "GET")
    public Map myGetList(@PathVariable Integer start,
                         @PathVariable Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋子",200);
        myList.put("扇子",300);
        myList.put("柜子",100);
        return myList;

    }
}
