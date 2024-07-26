package com.example.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class SaltUtils {
    private static final int SALT_LENGTH = 16;// 盐长度

    /**
     * 生成一个新的盐字符串
     * 
     * @return 新的盐字符串
     * 
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt_byte = new byte[SALT_LENGTH];
        random.nextBytes(salt_byte);
        String salt = Base64.getEncoder().encodeToString(salt_byte);
        return salt;
    }

    /**
     * 检测输入的密码+盐字符串的hash值和期望的hash值是否符合。
     * 
     * @param password 明文密码
     * @param salt     盐字符串
     * @param hash     期望的hash值
     * @return true 符合期望的hash值
     *         false 不符合
     */
    public static String hashedPassword(String password, String salt) {
        String hashedPassword = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String inputString = password + salt;
            byte[] hashBytes = digest.digest(inputString.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            hashedPassword = hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }
}
