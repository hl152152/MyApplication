package xinweilai.com.bit.common.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;




/**
 * Created by 空 on 2017/7/7 0007.
 */

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    private int orientation = VERTICAL;
    private int decoration = 10;


    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    public static final int NULL = 2;

    public RecyclerItemDecoration() {

    }

    public RecyclerItemDecoration(int orientation) {
        this.orientation = orientation;
    }

    /**
     * dp
     *
     * @param decoration
     * @param orientation
     */
    public RecyclerItemDecoration(int decoration, int orientation) {
        this.decoration = decoration;
        this.orientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int px = CommonUtil.dip2px(decoration);
        switch (orientation) {
            case VERTICAL:
                outRect.set(0, 0, 0, px);
                break;
            case HORIZONTAL:
                outRect.set(px, 0, px, 0);
                break;
            case NULL:
                outRect.set(px, px, px, px);
                break;
            default:
        }


    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
}
