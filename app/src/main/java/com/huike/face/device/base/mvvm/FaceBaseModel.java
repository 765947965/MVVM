package com.huike.face.device.base.mvvm;

import com.google.gson.JsonObject;
import com.huike.face.common.base.BaseModel;
import com.huike.face.device.base.net.BaseResponse;
import com.huike.face.device.base.net.NetCallBack;
import com.huike.face.device.base.util.BaseResponseFormat;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @ProjectName: GcService
 * @Package: com.smart.gc.service.base
 * @ClassName: PlanningBaseModel
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2019/11/1 16:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/1 16:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FaceBaseModel extends BaseModel {
    protected <D> void joinNet(Observable<JsonObject> observable, NetCallBack<D> netCallBack) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    private Disposable d = null;

                    @Override
                    public void onSubscribe(Disposable d) {
                        this.d = d;
                        compositeDisposable.add(d);
                        if (netCallBack != null) {
                            try {
                                netCallBack.onStart();
                            } catch (Exception e) {
                                onError(e);
                            }
                        }
                    }

                    @Override
                    public void onNext(JsonObject resultBean) {
                        if (netCallBack != null) {
                            Type mType = getCastClass(netCallBack);
                            BaseResponse<D> baseResponse = BaseResponseFormat.getFormatBean(resultBean, mType);
                            if (baseResponse == null || baseResponse.getCode() != 200) {
//                                if (baseResponse != null && baseResponse.getCode() == 401) {
//                                    // Token失效
//                                    UserUtil.setToken(null);
//                                    UserUtil.setUserMain(null);
//                                    netCallBack.onRequestFailure(new IllegalArgumentException("token is fail"));
//                                } else {
                                    netCallBack.onRequestFailure(new IllegalArgumentException("请求失败:" + (baseResponse == null ? -1000 : (baseResponse.getCode() + ":" + baseResponse.getMsg()))));
//                                }
                            } else {
                                netCallBack.onRequestSuccess(baseResponse);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        d.dispose();
                        compositeDisposable.remove(d);
                        if (netCallBack != null) {
                            netCallBack.onRequestFailure(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                        d.dispose();
                        compositeDisposable.remove(d);
                    }
                });
    }

    private Type getInterfaceCastClass(Object object) {
        Type superType = object.getClass().getGenericInterfaces()[0];
        if (superType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) superType;
            Type[] types = pt.getActualTypeArguments();
            return types[0];
        }
        return null;
    }

    private Type getCastClass(Object object) {
        Type superType = object.getClass().getGenericSuperclass();
        if (superType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) superType;
            Type[] types = pt.getActualTypeArguments();
            return types[0];
        }
        return getInterfaceCastClass(object);
    }
}
