package com.huike.face.device.business.screen;

import android.app.Application;
import android.support.annotation.NonNull;

import com.huike.face.common.base.BaseContract;
import com.huike.face.device.base.mvvm.FaceBaseViewModel;

/**
 * @ProjectName: HuikeFace
 * @Package: com.huike.face.device.business.screen
 * @ClassName: ScreenViewModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/3/31 10:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/31 10:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ScreenViewModel extends FaceBaseViewModel<ScreenModel> {
    public ScreenViewModel(@NonNull Application application) {
        super(application);
    }

    void play() {
        if (model != null) {
            if (model.getActivation()) {
                startMainActivity();
            } else {
            }
        }
    }

    private void startMainActivity() {
        BaseContract baseContract = getBaseContract();
        if (baseContract instanceof ScreenContract) {
            ((ScreenContract) baseContract).startMainActivity();
        }
    }
}
