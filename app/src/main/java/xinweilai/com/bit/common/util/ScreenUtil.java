package xinweilai.com.bit.common.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by 空 on 2017/12/28 0028.
 */

public class ScreenUtil {

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        return width;
    }


}
