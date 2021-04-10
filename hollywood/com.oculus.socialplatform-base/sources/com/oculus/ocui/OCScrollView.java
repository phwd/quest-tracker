package com.oculus.ocui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import com.oculus.socialplatform.R;

public class OCScrollView extends ScrollView {
    public boolean mScrollable = true;

    public void disableScroll() {
        this.mScrollable = false;
    }

    public void enableScroll() {
        this.mScrollable = true;
    }

    public OCScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ocRecyclerViewStyle);
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 8) {
            return super.onGenericMotionEvent(motionEvent);
        }
        if (!this.mScrollable || !super.onGenericMotionEvent(motionEvent)) {
            return false;
        }
        return true;
    }
}
