package xinweilai.com.bit.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.huche.myapplication2.R;
import com.huche.myapplication2.common.view.StateView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 空 on 2017/7/18 0018.
 * <p>
 * 目前最完善的
 */

public abstract class BaseFragment extends Fragment {


    private CompositeDisposable disposables;// 管理Destroy取消订阅者者
    private View rootView;
    public Context context;
    public int page = 1;
    private Unbinder bind;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_parent, container, false);
            LinearLayout parent = findViewById(R.id.parent);
            View mainView = inflater.inflate(getLayoutId(), parent, false);
            if (hasStateView()) {
                if (getStateView() != null) {
                    parent.addView(getStateView());
                }
            }
            parent.addView(mainView);
            bind = ButterKnife.bind(this, rootView);
            initUiAndListener();
            refreshData(savedInstanceState);

//        } else {
//            refreshData(savedInstanceState);

        }

        return rootView;
    }

    public boolean hasStateView() {
        return false;
    }

    public View getStateView() {
        return new StateView(context);
    }

    public <T extends View> T findViewById(int resId) {
        return (T) rootView.findViewById(resId);
    }

    protected abstract void refreshData(Bundle savedInstanceState);

    protected abstract void initUiAndListener();

    @LayoutRes
    protected abstract int getLayoutId();

    //可能会统计界面
//    public abstract String getFragmentTitle();

    //用于做沉浸式  默认的颜色，如果单个界面不一样需要修改
//    public int getThemeColor() {
//        return ContextCompat.getColor(getActivity(), R.color.color_ff7a8f);
//    }

    public void addDisposables(Disposable d) {
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }
        disposables.add(d);
    }

    public void removeDisposables(Disposable d) {
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }
        disposables.remove(d);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();

        if (disposables == null) {
            disposables = new CompositeDisposable();
        }
        disposables.dispose();
    }

}
