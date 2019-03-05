package xinweilai.com.bit.common.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;
import com.huche.myapplication2.KongApp;
import java.io.Closeable;
import java.io.IOException;


/**
 * Created by Administrator on 2017/11/11.
 */

public class CommonUtil {
    private static Toast mToast;

    public static Context getContext() {
        return KongApp.getContext();
    }

    public static void showSingleToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.show();
    }

//    public static String getDeviceId() {
////        DeviceUuidFactory deviceUuidFactory = new DeviceUuidFactory(CommonUtil.getContext());
////        UUID uuid = deviceUuidFactory.getUuid();
////        String valueOf = String.valueOf(uuid);
////        String replace = valueOf.replace("-", "_");
//
//       String device_id = JPushInterface.getRegistrationID(getContext());//100d855909154cf4784
//        if (!TextUtils.isEmpty(device_id)) {
//            return device_id;
//        }
//        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//            TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
//            //IMEI（imei）
//            device_id = telephonyManager.getDeviceId();
//            //序列号（sn）
//            if (TextUtils.isEmpty(device_id)) {
//                device_id = telephonyManager.getSimSerialNumber();
//            }
//        } else {
//            device_id = JPushInterface.getRegistrationID(getContext());
//        }
//        return device_id;
//
//    }

    public static int getStatusHeight() {
        int result = 0;
        int resourceId = getContext().getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 模拟删除键
     */
    public static void deleteText(EditText mEdit) {
        int keyCode = KeyEvent.KEYCODE_DEL;
        KeyEvent keyEventDown = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
        KeyEvent keyEventUp = new KeyEvent(KeyEvent.ACTION_UP, keyCode);
        mEdit.onKeyDown(keyCode, keyEventDown);
        mEdit.onKeyUp(keyCode, keyEventUp);
    }

    //复制到前剪切板
    public static void copyToPhone(Context context, String summary, String url) {
        ClipData myClip = ClipData.newPlainText(summary, url);
        ClipboardManager myClipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        myClipboard.setPrimaryClip(myClip);
    }

    /**
     * 关闭流
     *
     * @param stream
     */
    public static void closeSteam(Closeable stream) {
        if (stream == null) {
            return;
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getColor(int color) {
        return ContextCompat.getColor(getContext(), color);

    }

    /**
     * dip转为PX
     */
    public static int dip2px(float dipValue) {
        float fontScale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 需要权限 就返回true
     *
     * @return
     */
    public static boolean checkVersion() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static int getVersionCode() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = getContext().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
//        String version = packInfo.versionName;
        int code = packInfo.versionCode;
        return code;
    }

    public static void initWebViewParameter(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDisplayZoomControls(true);

        // 设置WebView属性，能够执行JavaScript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(false);
        // 为图片添加放大缩小功能
        //webview.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
    }
}
