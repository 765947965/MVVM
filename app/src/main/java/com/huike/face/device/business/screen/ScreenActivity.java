package com.huike.face.device.business.screen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.huike.face.device.BR;
import com.huike.face.device.R;
import com.huike.face.device.base.mvvm.FaceBaseActivity;
import com.huike.face.device.business.main.MainActivity;
import com.huike.face.device.databinding.ScreenActivityBinding;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;

/**
 * @ProjectName: HuikeFace
 * @Package: com.huike.face.device.business.screen
 * @ClassName: ScreenActivity
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2020/3/31 8:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/31 8:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ScreenActivity extends FaceBaseActivity<ScreenModel, ScreenActivityBinding, ScreenViewModel>
        implements ScreenContract {
    private Disposable disposable;

    @Override
    protected void initView() {
        rxPermissions();
    }

    @Override
    protected void initData() {

    }

    /*** 检查权限 ***/
    private void rxPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissions();
        } else {
            play();
        }
    }

    /*** 请求权限 ***/
    private void permissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        dispose();
        disposable = rxPermissions
                .requestEachCombined(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                ).subscribe(permission -> {
                    if (permission.granted) {
                        play();
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        AlertDialog dialog = new AlertDialog.Builder(ScreenActivity.this)
                                .setTitle(R.string.point)
                                .setMessage(R.string.permissionPrompt)
                                .setNegativeButton(R.string.cancel, (dialog1, which) -> {
                                    dialog1.cancel();
                                    finish();
                                })
                                .setNeutralButton(R.string.determine, (dialog12, which) -> {
                                    dialog12.cancel();
                                    permissions();
                                }).create();
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                    } else {
                        AlertDialog dialog = new AlertDialog.Builder(ScreenActivity.this)
                                .setTitle(R.string.point)
                                .setMessage(R.string.denyPermission)
                                .setNegativeButton(R.string.cancel, (dialog13, which) -> {
                                    dialog13.cancel();
                                    finish();
                                })
                                .setNeutralButton(R.string.determine, (dialog14, which) -> {
                                    dialog14.cancel();
                                    startActivity(new Intent(Settings.ACTION_SETTINGS));
                                    finish();
                                }).create();
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispose();
    }

    @Override
    protected int getViewModelId() {
        return BR.screenViewModel;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.screen_activity;
    }

    private void dispose() {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }

    private void play() {
        if (viewModel != null) {
            viewModel.play();
        }
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
