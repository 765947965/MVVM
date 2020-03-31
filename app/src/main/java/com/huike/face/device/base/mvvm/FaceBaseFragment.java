package com.huike.face.device.base.mvvm;

import android.app.Activity;
import android.databinding.ViewDataBinding;

import com.huike.face.common.base.BaseFragment;


/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.service.base.mvvm
 * @ClassName: PlanningBaseFragment
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/8 16:37
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/8 16:37
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class FaceBaseFragment<M extends FaceBaseModel, V extends ViewDataBinding,
        VM extends FaceBaseViewModel<M>> extends BaseFragment<M, V, VM> implements FaceBaseContact {

    @Override
    public void startLoadingActivity() {
        Activity activity = getActivity();
        if (activity instanceof FaceBaseActivity) {
            ((FaceBaseActivity) activity).startLoadingActivity();
        }
    }
}
