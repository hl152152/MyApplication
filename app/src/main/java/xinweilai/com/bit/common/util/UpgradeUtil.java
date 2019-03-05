package xinweilai.com.bit.common.util;

import android.app.Activity;
import android.support.v7.app.AlertDialog;

import com.huche.myapplication2.R;
import com.huche.myapplication2.common.api.ApiFactory;
import com.huche.myapplication2.common.api.RxSchedulers;


import io.reactivex.disposables.Disposable;

/**
 * Created by lvluogang on 2017/12/27.
 */

public class UpgradeUtil {

    private static String CHECKREQUIRED = "checkRequired";

    private static void showDialog(final String url, final Activity context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("版本更新提示");
        builder.setMessage("发现新版本,是否升级");
        int code = 0;
        try {
            code = CommonUtil.getVersionCode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final int finalCode = code;
        builder.setPositiveButton("是", (dialog, which) -> {
            VersionUpdateConfig.getInstance()//获取配置实例
                    .setContext(context)//设置上下文
                    .setDownLoadURL(url)//设置文件下载链接
                    .setNewVersion(String.valueOf(finalCode))//设置即将下载的APK的版本号,避免重复下载
//                        .setFileSavePath(getExternalCacheDir())//设置文件保存路径（可不设置）
                    .setNotificationIconRes(R.mipmap.ic_launcher)//设置通知图标
                    .setNotificationSmallIconRes(R.mipmap.ic_launcher)//设置通知小图标
                    .setNotificationTitle(context.getResources().getString(R.string.app_name))//设置通知标题
                    .startDownLoad();//开始下载
        });
        builder.setNegativeButton("否", (dialog, which) -> {

        });
        builder.show();
    }

    public static Disposable upgradeCheck(final Activity context) {
        String data = SharedPreferencesUtils.getShareData(CHECKREQUIRED);
        long time = 0;
        try {
            time = Long.parseLong(data);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            time = 0;
        }
        long subtraction = System.currentTimeMillis() - time;
        if (subtraction > 8 * 60 * 60 * 1000) {
            return ApiFactory.getInstance()
                    .apkVersion()
                    .compose(RxSchedulers.<VersionUpdate>io_main())
                    .subscribe(update -> {
//                            checkRequired = false;
                        SharedPreferencesUtils.putShareData(CHECKREQUIRED, String.valueOf(System.currentTimeMillis()));
                        String appVersion = update.getAndroidAppVer();
                        int code = 0;
                        try {
                            code = CommonUtil.getVersionCode();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        int version;
                        try {
                            version = Integer.parseInt(appVersion);
                        } catch (NumberFormatException e) {
                            version = 0;
                        }

                        if (version > code) {
                            String url = update.getAndroidAppDownloadUrl();
                            showDialog(url, context);
                        }

                    }, throwable -> {

                    });
        } else return null;
    }
}
