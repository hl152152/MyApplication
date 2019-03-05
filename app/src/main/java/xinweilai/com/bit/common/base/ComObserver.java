package xinweilai.com.bit.common.base;

import android.text.TextUtils;

import com.dunqi.gpm.chaotian.common.util.CommonUtil;

import io.reactivex.Observer;

/**
 * Created by Administrator on 2017/11/25.
 */

public abstract class ComObserver<T> implements Observer<Data<T>> {
    @Override
    public void onNext(Data<T> data) {
        if (!TextUtils.isEmpty(data.getResultMessage())) {
            CommonUtil.showSingleToast(data.getResultMessage());
        }
        if (data.isSuccess()) {
            onSuccess(data.getData());
        } else {
            onFaild(data.getResultCode());
        }
        onFinal();
    }

    @Override
    public void onError(Throwable e) {
        onFaild(-1);
        onFinal();
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFinal();

    void onFaild(int code) {

    }
}
