package xinweilai.com.bit.common.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.huche.myapplication2.KongApp;
import com.huche.myapplication2.R;
import com.huche.myapplication2.common.util.CommonUtil;
import com.huche.myapplication2.common.view.StateView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import xinweilai.com.bit.BitApp;

/**
 * Created by 空 on 2017/7/18 0018.
 * <p>
 * 命名就是注释
 */

public abstract class BaseActivity extends PermissionActivity {

    private CompositeDisposable disposables;// 管理Destroy取消订阅者
    private AppCompatActivity activity;

    public int page = 1;
    private LinearLayout parent;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        disposables = new CompositeDisposable();

        activity = this;

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BitApp.addActivity(this);

        setTheme(R.style.NoStatsBar);

        setContentView(R.layout.activity_base);

        parent = (LinearLayout) findViewById(R.id.parent);
        parent.setBackgroundColor(CommonUtil.getColor(R.color.system_bg));

        if (getStateView() != null) {
            parent.addView(getStateView());
        }

        LayoutInflater inflater = getLayoutInflater();
        View child = inflater.inflate(getLayoutId(), parent, false);

        parent.addView(child);

        bind = ButterKnife.bind(this);
        initUiAndListener();
        refreshData(savedInstanceState);
    }

    public void t(String msg) {
        CommonUtil.showSingleToast(msg);
    }

    public void setBackground(int color) {
        parent.setBackgroundColor(CommonUtil.getColor(color));
    }

    public View getStateView() {
        return new StateView(activity);
    }


    protected abstract void refreshData(Bundle savedInstanceState);

    protected abstract void initUiAndListener();

    @LayoutRes
    protected abstract int getLayoutId();

    public AppCompatActivity getActivity() {
        return activity;
    }

    public void addDisposables(Disposable d) {
        if (d != null)
            disposables.add(d);
    }

    public void removeDisposables(Disposable d) {
        disposables.remove(d);
    }

    //可能会统计界面
//    public abstract String getActivityTitle();

    //用于做沉浸式  默认的颜色，如果单个界面不一样需要修改
//    public int getThemeColor() {
//        return ContextCompat.getColor(getActivity(), R.color.color_ff7a8f);
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.dispose();
        bind.unbind();
        BitApp.removeActivity(activity);
    }
}
