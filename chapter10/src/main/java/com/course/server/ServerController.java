package com.course.server;

import com.course.utils.JdkSignatureUtil;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class ServerController {
    /**
     * 公钥
     */
    private final static String PUBLIC_KEY="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJz1kfGpz7dGFZCUY/kXbvYBXZEd5Xg+S8SRRD+p2iGCeQlKJ+Fycuboe7hIr8jhyTEKpaOFN8wW5/QNXdOzDnMCAwEAAQ==";

    public String server(@RequestBody Map<String,Object> param) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        //从参数中取出签名字符串并删除，因为sign不参与字符串拼接
        String sign = (String)param.remove("sign");
        //对签名字符串进行URL解码
        String decodeSign = URLDecoder.decode(sign, "utf-8");
        //将签名的参数内容按照参数名的字典顺序排序，并拼接为字符串
        StringBuilder sb = new StringBuilder();
        param.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(entry->
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&"));
        String paramStr = sb.toString().substring(0, sb.length() - 1);
        boolean result = JdkSignatureUtil.verifySignature(PUBLIC_KEY, decodeSign, paramStr);
        if(result){
            return "签名验证成功";
        }
        return "签名验证失败，非法请求";

    }
}
