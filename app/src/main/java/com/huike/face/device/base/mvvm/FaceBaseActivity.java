package com.huike.face.device.base.mvvm;

import android.databinding.ViewDataBinding;

import com.huike.face.common.base.BaseActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.service.base
 * @ClassName: PlanningBaseActivity
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/1 16:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/1 16:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class FaceBaseActivity<M extends FaceBaseModel, V extends ViewDataBinding,
        VM extends FaceBaseViewModel<M>> extends BaseActivity<M, V, VM> implements FaceBaseContact {
    @Override
    public void startLoadingActivity() {
        if (isFinishing()) {
            return;
        }
//        new MaterialDialog.Builder(this)
//                .title(R.string.point)
//                .content(R.string.pleaseLogInFirst)
//                .positiveText(R.string.determine)
//                .negativeText(R.string.cancel)
//                .onPositive((dialog, which) -> startActivity(new Intent(this, LoadingActivity.class)))
//                .build()
//                .show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
