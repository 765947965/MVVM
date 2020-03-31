package com.huike.face.device.base;

import android.app.Application;
import android.content.Context;

import com.tencent.mmkv.MMKV;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.service
 * @ClassName: MyApplication
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/7 16:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/7 16:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyApplication extends Application {
    private static Context application;
    //private static BoxStore boxStore;

    public static Context getApplication() {
        return application;
    }

//    public static BoxStore getBoxStore() {
//        return boxStore;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        MMKV.initialize(this);
        initObjectBox();
        initUm();
    }


    private void initObjectBox() {
//        //第一次没运行之前，MyObjectBox默认会有报错提示，可以忽略。创建实体类， make之后报错就会不提示
//        boxStore = MyObjectBox.builder().androidContext(this).build();
//        if (BuildConfig.DEBUG) {//开启浏览器访问ObjectBox
//            new AndroidObjectBrowser(boxStore).start(this);
//        }
    }

    private void initUm() {
//        UMConfigure.init(this, "5e7d727c978eea06fd7fbfa3", BuildConfig.FLAVOR, UMConfigure.DEVICE_TYPE_PHONE, null);
//        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
//        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
    }


}
