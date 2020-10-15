package com.demo.securitylogin.util;

import java.util.Random;
import java.util.UUID;

public class SerialNumberUtil {

    /**
     * UUID
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成0~1 a-z A-Z 随机码
     * @param type 1数字 2字母 3数字+字母
     * @param length 长度
     * @param exChars 排除字符
     * @return
     */
    public static String getRandomCode(int type, int length, String exChars) {
        int i = 0;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        switch (type) {
            case 1:
                while (i < length) {
                    int t = random.nextInt(123);
                    if ((t >= 48 && t <= 57) && (exChars == null || exChars.indexOf((char) t) < 0)) {
                        sb.append((char) t);
                        i++;
                    }
                }
                break;
            case 2:
                while (i < length) {
                    int t = random.nextInt(123);
                    if ((t >= 97 || (t >= 65 && t <= 90)) && (exChars == null || exChars.indexOf((char) t) < 0)) {
                        sb.append((char) t);
                        i++;
                    }
                }
                break;
            case 3:
                while (i < length) {
                    int t = random.nextInt(123);
                    if ((t >= 97 || (t >= 65 && t <= 90) || (t >= 48 && t <= 57)) && (exChars == null || exChars.indexOf((char) t) < 0)) {
                        sb.append((char) t);
                        i++;
                    }
                }
                break;
        }
        return sb.toString();
    }


}
