package xinweilai.com.bit.common.base;

import android.support.annotation.Nullable;



import java.util.List;

/**
 * Created by 空 on 2017/11/17 0017.
 */

public abstract class RecyclerAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder> {

    public RecyclerAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

}
