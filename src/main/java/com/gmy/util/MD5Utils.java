package com.gmy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Kevin Guo
 * @package com.gmy.util
 * @date 2020/8/17 08:59
 * @description
 */
public class MD5Utils {
    /**
     * @description:
     * @author: Kevin Guo
     * @date: 2020/8/17 09:19
     * @param: null
     * @return: {@link }
     */
    public static String code(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            //16位加密
//            return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(code("123"));
    }
}
