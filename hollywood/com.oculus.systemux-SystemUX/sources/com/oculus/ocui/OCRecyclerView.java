package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.ocui.R;
import com.oculus.ocui.logging.OCRecyclerViewLogger;
import java.lang.reflect.Field;

public class OCRecyclerView extends RecyclerView {
    public static final int SCROLL_TOUCH_SLOP_DP = 40;
    private static final String TAG = "OCRecyclerView";
    public static final int THUMBSTICK_SCROLL_INERTIA_FACTOR = 5500;
    private OCRecyclerViewLogger mLogger;
    private int mPreviousScrollState = 0;
    private boolean mScrollBarAlwaysVisible;

    public OCRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ocRecyclerViewStyle);
        RecyclerView.ItemAnimator itemAnimator = getItemAnimator();
        itemAnimator.setAddDuration(0);
        itemAnimator.setChangeDuration(0);
        itemAnimator.setMoveDuration(0);
        itemAnimator.setRemoveDuration(0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCRecyclerView, R.attr.ocRecyclerViewStyle, 0);
        this.mScrollBarAlwaysVisible = obtainStyledAttributes.getBoolean(R.styleable.OCRecyclerView_scrollBarAlwaysVisible, false);
        obtainStyledAttributes.recycle();
        if (this.mScrollBarAlwaysVisible) {
            setVerticalScrollBarEnabled(true);
        } else {
            setVerticalScrollBarEnabled(false);
        }
        try {
            Field declaredField = RecyclerView.class.getDeclaredField("mTouchSlop");
            declaredField.setAccessible(true);
            declaredField.set(this, 40);
        } catch (Exception e) {
            Log.e(TAG, "Unable to set recycler view touch slop.", e);
        }
    }

    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        if (this.mScrollBarAlwaysVisible) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            setVerticalScrollBarEnabled(true);
            return true;
        } else if (actionMasked != 10) {
            return false;
        } else {
            setVerticalScrollBarEnabled(false);
            return true;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (motionEvent.getSource() == 2 && motionEvent.getAction() == 8 && getScrollState() != 1) {
            stopScroll();
            fling(0, -((int) (motionEvent.getAxisValue(9) * 5500.0f)));
        }
        if (!(this.mLogger == null || getScrollState() == this.mPreviousScrollState)) {
            if (getScrollState() == 0) {
                this.mLogger.onScrollEnd();
            } else {
                this.mLogger.onScrollStart();
            }
            this.mPreviousScrollState = getScrollState();
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public void setLogger(OCRecyclerViewLogger oCRecyclerViewLogger) {
        this.mLogger = oCRecyclerViewLogger;
    }
}
