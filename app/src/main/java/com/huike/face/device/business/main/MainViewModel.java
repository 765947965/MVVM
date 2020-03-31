package com.huike.face.device.business.main;

import android.app.Application;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.huike.face.device.BR;
import com.huike.face.device.base.mvvm.FaceBaseViewModel;

/**
 * @ProjectName: HuikeFace
 * @Package: com.huike.face.device.business.main
 * @ClassName: MainViewModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/3/31 10:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/31 10:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MainViewModel extends FaceBaseViewModel<MainModel> {
    private String inputValue;

    @Bindable
    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
        notifyPropertyChanged(BR.inputValue);
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
