package com.oculus.ocui;

import X.AnonymousClass1Al;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.ocui.logging.OCRecyclerViewLogger;
import com.oculus.socialplatform.R;
import java.lang.reflect.Field;

public class OCRecyclerView extends RecyclerView {
    public static final int SCROLL_TOUCH_SLOP_DP = 40;
    public static final String TAG = "OCRecyclerView";
    public static final int THUMBSTICK_SCROLL_INERTIA_FACTOR = 5500;
    public OCRecyclerViewLogger mLogger;
    public int mPreviousScrollState = 0;
    public boolean mScrollBarAlwaysVisible;

    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        if (!this.mScrollBarAlwaysVisible) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 9) {
                setVerticalScrollBarEnabled(true);
                return true;
            } else if (actionMasked == 10) {
                setVerticalScrollBarEnabled(false);
                return true;
            }
        }
        return false;
    }

    public OCRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ocRecyclerViewStyle);
        AnonymousClass1Al r2 = this.mItemAnimator;
        r2.A00 = 0;
        r2.A01 = 0;
        r2.A02 = 0;
        r2.A03 = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.oculus.common.ocui.R.styleable.OCRecyclerView, R.attr.ocRecyclerViewStyle, 0);
        this.mScrollBarAlwaysVisible = obtainStyledAttributes.getBoolean(0, false);
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

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        int i;
        if (motionEvent.getSource() == 2 && motionEvent.getAction() == 8 && this.mScrollState != 1) {
            stopScroll();
            fling(0, -((int) (motionEvent.getAxisValue(9) * 5500.0f)));
        }
        OCRecyclerViewLogger oCRecyclerViewLogger = this.mLogger;
        if (!(oCRecyclerViewLogger == null || (i = this.mScrollState) == this.mPreviousScrollState)) {
            if (i == 0) {
                oCRecyclerViewLogger.onScrollEnd();
            } else {
                oCRecyclerViewLogger.onScrollStart();
            }
            this.mPreviousScrollState = this.mScrollState;
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public void setLogger(OCRecyclerViewLogger oCRecyclerViewLogger) {
        this.mLogger = oCRecyclerViewLogger;
    }
}
