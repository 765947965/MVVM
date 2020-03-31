package com.huike.face.device.business.screen;

import com.huike.face.device.base.mvvm.FaceBaseModel;
import com.tencent.mmkv.MMKV;

/**
 * @ProjectName: HuikeFace
 * @Package: com.huike.face.device.business.screen
 * @ClassName: ScreenModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/3/31 10:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/31 10:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ScreenModel extends FaceBaseModel {
    private static String ACTIVATION_KEY = "activation";

    boolean getActivation(){
        return MMKV.defaultMMKV().decodeBool(ACTIVATION_KEY, false);
    }

    void setActivation(boolean value){
        MMKV.defaultMMKV().encode(ACTIVATION_KEY, value);
    }
}
