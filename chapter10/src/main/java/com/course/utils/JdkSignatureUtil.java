package com.course.utils;

import lombok.extern.log4j.Log4j;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA签名工具类
 */
@Log4j
public class JdkSignatureUtil {

    private final static String RSA = "RSA";
    private final static String MD5_WITH_RSA = "MD5withRSA";

    /**
     * 执行签名
     * @param rsaPrivateKey 私钥
     * @param src  参数内容
     * @return 签名后的内容，base64后的字符串
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static String executeSignature(String rsaPrivateKey,String src) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        byte[] decodePrivateKey = Base64.getDecoder().decode(rsaPrivateKey.replace("\r\n", ""));
        log.debug(decodePrivateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decodePrivateKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        log.debug(privateKey);
        Signature signature = Signature.getInstance(MD5_WITH_RSA);//用MD5生成内容摘要，再用RSA私钥加密，进而生成数字签名
        signature.initSign(privateKey);
        signature.update(src.getBytes());
        //生成签名
        byte[] result = signature.sign();
        //base64编码签名为字符串
        return Base64.getEncoder().encodeToString(result);

    }

    /**
     * 验签
     * @param rsaPublicKey 公钥
     * @param sign         签名
     * @param src          参数内容
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public static boolean verifySignature(String rsaPublicKey,String sign,String src) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        byte[] decodePublicKey = Base64.getDecoder().decode(rsaPublicKey.replace("\r\n", ""));
        log.debug(decodePublicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decodePublicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        log.debug(publicKey);
        Signature signature = Signature.getInstance(MD5_WITH_RSA);
        signature.initVerify(publicKey);
        signature.update(src.getBytes());
        //base64解码签名为字节数组
        byte[] decodeSign = Base64.getDecoder().decode(sign);
        //验证签名
        return signature.verify(decodeSign);

    }



}
