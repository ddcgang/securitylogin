package com.demo.securitylogin.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {
    /**
     * 过期时间5分钟
     */
    private static final long EXPIRE_TIME = 30 * 1000;
    /**
     * jwt 密钥
     */
    private static final String SECRET = "JWT_SECRET_20202010";

    /**
     * 生成签名，五分钟后过期
     *
     * @param userId
     * @return
     */
    public static String sign(String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    // 将 user id 保存到 token 里面
                    .withAudience(userId)
                    // 五分钟后token过期
                    .withExpiresAt(date)
                    // token 的密钥
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    public static String signTest() {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            Map<String, Object> map = new HashMap<>();
            map.put("user", "admin456");
            map.put("password", "123456789");
            String token = JWT.create()
                    .withIssuer("发布者")   //发布者
                    .withSubject("主题")    //主题
                    .withAudience("AudienceUser")     //观众，相当于接受者
                    .withIssuedAt(new Date())   // 生成签名的时间
                    .withExpiresAt(date)    // 生成签名的有效期,分钟
                    .withClaim("data", JSON.toJSONString(map)) //自定义字段存数据
                    .withNotBefore(new Date())  //生效时间
                    .withJWTId(UUID.randomUUID().toString())    //编号
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据token获取userId
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            DecodedJWT decodedJWT = JWT.decode(token);
            if (decodedJWT.getExpiresAt().compareTo(new Date()) > 0) {
                return decodedJWT.getAudience().get(0);
            } else {
                return "TimeOut";
            }
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static boolean checkSign(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    // .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("token 无效，请重新获取");
        }
    }
}
