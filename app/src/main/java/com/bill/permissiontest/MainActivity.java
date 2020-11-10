package com.bill.permissiontest;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bill.permissiontest.util.Logger;
import com.bill.permissiontest.util.PermissionUtil;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleClick(View view) {
        String[] perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        if (PermissionUtil.checkPermission(this, perms)) {
            Logger.d("有权限");
        } else {
            Logger.e("没有权限");
            PermissionUtil.requestPermission(this, "申请本地读写权限", 1, perms);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtil.SETTINGS_REQ_CODE) {
            String[] perms = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            if (PermissionUtil.checkPermission(this, perms)) {
                Logger.d("已经有权限了");
            } else {
                Logger.e("还是没有权限");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //将请求结果传递EasyPermission库处理
        PermissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Logger.d("用户授权成功");
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Logger.e("用户拒绝授权");
        if (PermissionUtil.somePermissionPermanentlyDenied(this, perms)) {
            PermissionUtil.showAppSettingsDialog(this, "权限申请",
                    "这是App运行所需要的必要权限，请在App设置里手动给予权限");
        }
    }

}
