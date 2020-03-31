package com.huike.face.device.base.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @ProjectName: planning
 * @Package: com.xwl.yaru.planning.base.util
 * @ClassName: SHA1
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/3/27 8:09
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/27 8:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SHA1 {

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (byte aByte : bytes) {
            buf.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[aByte & 0x0f]);
        }
        return buf.toString();
    }

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
