package xinweilai.com.bit.common.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dunqi.gpm.chaotian.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xinweilai.com.bit.R;

/**
 * Created by Administrator on 2017/11/11.
 */

public class ComTitleBar extends FrameLayout {
    @BindView(R

            .id.title_tv)
    TextView titleTv;
    @BindView(R.id.right_tv)
    public TextView rightTv;
    @BindView(R.id.back_iv)
    ImageView back_iv;

    public ComTitleBar(@NonNull Context context) {
        this(context, null);
    }

    public ComTitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.title_bar, this);
        ButterKnife.bind(this, view);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.title_bar);
        String title = typedArray.getString(R.styleable.title_bar_title);
        titleTv.setText(title);
        String right_text = typedArray.getString(R.styleable.title_bar_right_text);
        rightTv.setText(right_text);

        int title_size = typedArray.getInt(R.styleable.title_bar_title_size, 0);
        if (title_size != 0) titleTv.setTextSize(title_size);

        int color = typedArray.getColor(R.styleable.title_bar_right_text_color, -1);
        if (color > -1) {
            rightTv.setTextColor(color);
        }
        typedArray.recycle();
    }


    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        if (getContext() instanceof Activity) {
            ((Activity) getContext()).onBackPressed();
        }
    }

    public void setTitle(String title) {
        titleTv.setText(title);
    }

    public void setBackGone() {
        back_iv.setVisibility(GONE);
    }

    public void setBackListener(OnClickListener listener) {
        back_iv.setOnClickListener(listener);
    }

    public void setOnClickListenerOfRight(OnClickListener listener) {
        rightTv.setOnClickListener(listener);
    }

    public void setRightText(String text) {
        rightTv.setText(text);
    }

    public void setRightText(String text, OnClickListener listener) {
        rightTv.setText(text);
        rightTv.setOnClickListener(listener);
    }
}
