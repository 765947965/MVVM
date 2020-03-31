package com.huike.face.device.base.util;

import com.tencent.mmkv.MMKV;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.appuser.base.util
 * @ClassName: UserUtil
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/14 11:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/14 11:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UserUtil {
    private static final String USER_MAIN_KEY = "UserMain";
    private static final String TOKEN = "token";
//    private static UserMain userMain;
    private static String token;

//    public static UserMain getUserMain() {
//        if (userMain == null) {
//            synchronized (UserUtil.class) {
//                if (userMain == null) {
//                    MMKV kv = MMKV.defaultMMKV();
//                    String value = kv.decodeString(USER_MAIN_KEY);
//                    if (!TextUtils.isEmpty(value)) {
//                        try {
//                            userMain = new Gson().fromJson(value, UserMain.class);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//        return userMain;
//    }

    public static String getToken() {
        if (token == null) {
            synchronized (UserUtil.class) {
                if (token == null) {
                    MMKV kv = MMKV.defaultMMKV();
                    token = kv.decodeString(TOKEN, "");
                }
            }
        }
        return token;
    }

//    public static void setUserMain(UserMain userMain) {
//        String value = "";
//        if (userMain != null) {
//            value = new Gson().toJson(userMain);
//            MobclickAgent.onProfileSignIn(userMain.getId().toString());
//        } else {
//            MobclickAgent.onProfileSignOff();
//        }
//        MMKV kv = MMKV.defaultMMKV();
//        kv.encode(USER_MAIN_KEY, value);
//        UserUtil.userMain = userMain;
//    }

    public static void setToken(String token) {
        if (token == null) {
            token = "";
        }
        MMKV kv = MMKV.defaultMMKV();
        kv.encode(TOKEN, token);
        UserUtil.token = token;
    }
}
