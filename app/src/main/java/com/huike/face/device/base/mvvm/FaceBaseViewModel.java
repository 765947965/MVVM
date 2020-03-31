package com.huike.face.device.base.mvvm;

import android.app.Application;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.huike.face.common.base.BaseContract;
import com.huike.face.common.base.BaseViewModel;
import com.huike.face.common.util.NetUtil;
import com.huike.face.device.BR;
import com.huike.face.device.R;
import com.huike.face.device.base.MyApplication;
import com.huike.face.device.base.net.BaseResponse;
import com.huike.face.device.base.net.NetCallBack;
import com.huike.face.device.base.util.UserUtil;


/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.service.base
 * @ClassName: PlanningBaseViewModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/1 16:49
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/1 16:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FaceBaseViewModel<M extends FaceBaseModel> extends BaseViewModel<M> {
    private String activityTitle;
    private View.OnClickListener commonTileClick = this::onTitleClick;

    public View.OnClickListener getCommonTileClick() {
        return commonTileClick;
    }

    protected void onTitleClick(View view) {
        onBackPressed();
    }

    @Bindable
    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
        notifyPropertyChanged(BR.activityTitle);
    }

    public void setActivityTitle(int resId) {
        setActivityTitle(getStringRes(resId));
    }

    public FaceBaseViewModel(@NonNull Application application) {
        super(application);
    }


    public abstract class FaceNetCallBack<D> implements NetCallBack<D> {
        private String message;
        private boolean needLoad;

        public FaceNetCallBack(int resId) {
            this(MyApplication.getApplication().getString(resId));
        }

        public FaceNetCallBack(String message) {
            this(message, true);
        }

        public FaceNetCallBack(int resId, boolean needLoad) {
            this(MyApplication.getApplication().getString(resId), needLoad);
        }

        public FaceNetCallBack(String message, boolean needLoad) {
            this.message = message;
            this.needLoad = needLoad;
        }

        @Override
        public void onStart() {
            if (!NetUtil.isNetworkConnected(getApplication())) {
                String message = getApplication().getResources().getString(R.string.networkUnavailable);
                showToast(message);
                onRequestNotWork();
                throw new IllegalArgumentException(message);
            }
            // 判断是否需要登录
            FaceBaseContact baseContract = (FaceBaseContact) getBaseContract();
            if (needLoad && TextUtils.isEmpty(UserUtil.getToken())) {
                if (baseContract != null) {
                    baseContract.startLoadingActivity();
                    String message = getApplication().getResources().getString(R.string.userNotLoggedIn);
                    showToast(message);
                    throw new IllegalArgumentException(message);
                }
            }
            if (!TextUtils.isEmpty(message)) {
                if (baseContract != null) {
                    baseContract.showLoading(message);
                }
            }
        }

        @Override
        public void onRequestSuccess(BaseResponse<D> baseResponse) {
            BaseContract baseContract = getBaseContract();
            if (baseContract != null) {
                baseContract.dismissLoading();
            }
        }

        @Override
        public void onRequestFailure(Throwable e) {
            if (e != null && getApplication().getResources().getString(R.string.userNotLoggedIn).equals(e.getMessage())) {
                // 未登录错误会弹出登录弹窗，故不再提示错误
                return;
            }
            BaseContract baseContract = getBaseContract();
            if (baseContract instanceof FaceBaseContact) {
                baseContract.dismissLoading();
                if (!TextUtils.isEmpty(message)) {
                    baseContract.showDialog(getApplication().getResources().getString(R.string.point),
                            e == null ? getApplication().getResources().getString(R.string.unknownMistake) : e.getMessage());
                }
            }
        }

        @Override
        public void onRequestNotWork() {
            if (!TextUtils.isEmpty(message)) {
                BaseContract baseContract = getBaseContract();
                if (baseContract != null) {
                    String messageTips = getApplication().getResources().getString(R.string.networkUnavailable);
                    baseContract.dismissLoading();
                    baseContract.showDialog(getApplication().getResources().getString(R.string.point), messageTips);
                }
            }
        }
    }
}
