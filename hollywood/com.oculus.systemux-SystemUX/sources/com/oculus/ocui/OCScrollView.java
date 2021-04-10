package com.oculus.ocui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import com.oculus.common.ocui.R;

public class OCScrollView extends ScrollView {
    private boolean mScrollable = true;

    public OCScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ocRecyclerViewStyle);
    }

    public void disableScroll() {
        this.mScrollable = false;
    }

    public void enableScroll() {
        this.mScrollable = true;
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 8) {
            return this.mScrollable && super.onGenericMotionEvent(motionEvent);
        }
        return super.onGenericMotionEvent(motionEvent);
    }
}
