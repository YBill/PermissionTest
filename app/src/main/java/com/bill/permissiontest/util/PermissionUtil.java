package com.bill.permissiontest.util;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Bill on 2019/12/5.
 * Describe ：
 */
public class PermissionUtil {

    /**
     * 通过showAppSettingsDialog方法引导用户去设置页手动设置权限，返回App后在onActivityResult中的requestCode
     */
    public static final int SETTINGS_REQ_CODE = 0x00A0;

    /**
     * 检查权限
     *
     * @param context
     * @param perms   权限列表
     * @return true：已经有perms权限
     */
    public static boolean checkPermission(Activity context, String... perms) {
        return EasyPermissions.hasPermissions(context, perms);
    }

    /**
     * 申请权限
     *
     * @param activity    在Activity中申请权限
     * @param rationale   权限弹窗上的提示语。告诉用户，这个权限用途
     * @param requestCode 这次请求权限的唯一标示，code
     * @param perms       权限列表
     */
    public static void requestPermission(Activity activity, String rationale, int requestCode, String[] perms) {
        EasyPermissions.requestPermissions(activity, rationale, requestCode, perms);
    }

    /**
     * 申请权限
     *
     * @param fragment    在Fragment中申请权限
     * @param rationale   权限弹窗上的提示语。告诉用户，这个权限用途
     * @param requestCode 这次请求权限的唯一标示，code
     * @param perms       权限列表
     */
    public static void requestPermission(Fragment fragment, String rationale, int requestCode, String[] perms) {
        EasyPermissions.requestPermissions(fragment, rationale, requestCode, perms);
    }

    /**
     * 在Activity或Fragment中重写onRequestPermissionsResult方法，并将参数都传到这里
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @param receivers    实现了EasyPermissions.PermissionCallbacks接口的类传this
     */
    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, Object... receivers) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, receivers);
    }

    /**
     * 检查用户是否勾选了禁止后不再弹窗框，并拒绝了权限申请
     *
     * @param activity
     * @param deniedPermissions
     * @return true：用户拒绝了权限申请
     */
    public static boolean somePermissionPermanentlyDenied(Activity activity, List<String> deniedPermissions) {
        return EasyPermissions.somePermissionPermanentlyDenied(activity, deniedPermissions);
    }

    /**
     * 弹出弹窗引导用户去App设置页手动设置权限
     *
     * @param activity
     */
    public static void showAppSettingsDialog(Activity activity) {
        new AppSettingsDialog.Builder(activity)
                .setRequestCode(SETTINGS_REQ_CODE)
                .build()
                .show();
    }

    /**
     * 弹出弹窗引导用户去App设置页手动设置权限
     *
     * @param activity
     * @param title
     * @param message
     */
    public static void showAppSettingsDialog(Activity activity, String title, String message) {
        new AppSettingsDialog.Builder(activity)
                .setRequestCode(SETTINGS_REQ_CODE)
                .setTitle(title)
                .setRationale(message)
                .build()
                .show();
    }


    /**
     * 弹出弹窗引导用户去App设置页手动设置权限
     *
     * @param activity
     * @param title      title
     * @param message    弹窗内容
     * @param confirmBtn 确认按钮文案
     * @param cancelBtn  取消按钮文案
     */
    public static void showAppSettingsDialog(Activity activity, String title, String message,
                                             String confirmBtn, String cancelBtn) {
        new AppSettingsDialog.Builder(activity)
                .setRequestCode(SETTINGS_REQ_CODE)
                .setTitle(title)
                .setRationale(message)
                .setPositiveButton(confirmBtn)
                .setNegativeButton(cancelBtn)
                .build()
                .show();
    }

}