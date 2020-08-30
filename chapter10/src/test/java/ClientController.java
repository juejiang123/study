import com.course.utils.JdkSignatureUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ClientController {
    //私钥
    private final static String PRIVATE_KEY = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAnPWR8anPt0YVkJRj+Rdu9gFdkR3leD5LxJFEP6naIYJ5CUon4XJy5uh7uEivyOHJMQqlo4U3zBbn9A1d07MOcwIDAQABAkAZhys0ddztvv1U5X2ZDsGiSziPmKAwvVkPYF0MSbDLkC6ZF6SDosPnBOQGY1YXF88eknAxEmuhWHnRAb05QnUJAiEA03SgC954tZS4OLyRf/4ArNdOBYurKQPbCapDgwAesYUCIQC+BhF3FBGyIlnh7D1iN+uNm1atga5go3widi7dqRnFlwIgEuQldEoA4MAToUX/fb7Ukpx9pPMwbG6iv/9NHsQA+f0CIALRfDjT519I+yRKqK5oPeofv61bGwb75b9tGUzYUTWJAiBLKFNjABVQWu5zYD+IGx3ooJyAFn7rwFGL8Y3h1V1ITg==";

    public static String sender() throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        Map<String,Object> requestParam = new HashMap<>(16);
        requestParam.put("userName","xiaoming");
        requestParam.put("phone","15867644123");
        requestParam.put("address","beijing");
        requestParam.put("status",2);

        //将需要签名的参数内容按照参数名的字典顺序进行排序，并拼接字符串
        StringBuilder sb = new StringBuilder();
        requestParam.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(entry ->
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&"));
        String paramStr = sb.toString().substring(0, sb.length() - 1);
        System.out.println("paramStr:"+paramStr);
        //使用私钥生成签名字符串
        String sign = JdkSignatureUtil.executeSignature(PRIVATE_KEY, paramStr);
        //对签名字符串进行URL编码
        String urlEncodeSign = URLEncoder.encode(sign, "UTF-8");
        //请求参数中需带上签名字符串
        requestParam.put("sign",urlEncodeSign);
        return postJson("http://localhost:8888/test",requestParam);

    }

    public static <T> String postJson(String url, T param){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<T> httpEntity = new HttpEntity<>(param,headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,httpEntity,String.class);
        return responseEntity.getBody();

    }

    public static void main(String[] args) {
        try {
            System.out.println(sender());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
