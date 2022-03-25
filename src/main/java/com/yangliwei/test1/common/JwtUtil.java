package com.yangliwei.test1.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 *  jwt工具类
 * @author yangliwei
 */
public class JwtUtil {
    public static final String JWT_KEY = "Yangliwei123";
    public static final Long JWT_TTL = 60*60*1000L;

    /**
     *  获取jwt中的uuid
     * @return uuid
     */
    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    /**
     *  生成jwt
     * @param subject 主题
     * @return  jwt字符串
     */
    public static String createJwt(String subject){
        return getJwtBuilder(subject, null, getUuid()).compact();
    }

    /**
     *  生成jwt
     * @param subject 主题
     * @param ttlMillis 过期时间
     * @return jwt字符串
     */
    public static String createJwt(String subject,Long ttlMillis) {
        String uuid = getUuid();
        return getJwtBuilder(subject, ttlMillis, uuid).compact();
    }

    /**
     * 获取jwt builder
     * @param subject 主题
     * @param ttlMillis 过期时间
     * @param uuid uuid
     * @return jwt builder
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
       SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
       SecretKey secretKey = generalKey();
       long nowMillis = System.currentTimeMillis();
       Date now = new Date(nowMillis);
       if (ttlMillis == null) {
           ttlMillis = JWT_TTL;
       }
       Date exp = new Date(nowMillis + ttlMillis);
       return Jwts.builder()
               // 签发者
               .setIssuer("yangliwei")
               // jwt的唯一标识
               .setId(uuid)
               // 签发时间
               .setIssuedAt(now)
               // 主题
               .setSubject(subject)
               // 签名算法以及密匙
               .signWith(signatureAlgorithm, secretKey)
               // 过期时间
               .setExpiration(exp);
    }

    /**
     * 生成加密后base64秘钥 secretKey
     * @return secretKey
     */
    public static SecretKey generalKey(){
        byte[] decode = Base64.getDecoder().decode(JWT_KEY);
        return new SecretKeySpec(decode, 0, decode.length, "AES");
    }

    /**
     * 解析jwt
     * @param jwt jwt
     * @return Claims
     */
    public static Claims parseJwt(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) throws Exception {
        String jwt = createJwt("1234");
        System.out.println(jwt);
        Claims claims = parseJwt("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ5YW5nbGl3ZWkiLCJqdGkiOiJkOGJjYWEzMTQxODE0MzU0YmZmNWJiNGFhNjNkZmFkOSIsImlhdCI6MTY0ODE3OTg1MSwic3ViIjoiMTIzNCIsImV4cCI6MTY0ODE4MzQ1MX0.SdDwsGSxF34L9QMESm0HrSeCU6BjAwNMeD0r0t4kcEI");
        System.out.println(claims.getSubject());
    }


}
