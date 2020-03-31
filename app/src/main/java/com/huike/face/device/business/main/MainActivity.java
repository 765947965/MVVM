package com.huike.face.device.business.main;

import com.huike.face.device.BR;
import com.huike.face.device.R;
import com.huike.face.device.base.mvvm.FaceBaseActivity;
import com.huike.face.device.databinding.MainActivityBinding;

public class MainActivity extends FaceBaseActivity<MainModel, MainActivityBinding, MainViewModel> {


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getViewModelId() {
        return BR.mainViewModel;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity;
    }
}
