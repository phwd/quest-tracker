package com.oculus.ocui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;
import com.oculus.common.ocui.R;

public final class OCToggle extends Switch {
    private OCEventHandler mEventHandler;
    private View.OnClickListener mOnClickListener;
    private View.OnHoverListener mOnHoverListener;

    public OCToggle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.ocui.$$Lambda$OCToggle$D697Rpk5oR6ssgXrsqt63j6gsJA */

            public final void onClick(View view) {
                OCToggle.this.lambda$new$18$OCToggle(view);
            }
        });
        super.setOnHoverListener(new View.OnHoverListener() {
            /* class com.oculus.ocui.$$Lambda$OCToggle$fsbJOLxbSPvCq7txLMd8oH9Rfw */

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return OCToggle.this.lambda$new$19$OCToggle(view, motionEvent);
            }
        });
        setShowText(false);
        setBackground(null);
        setThumbResource(R.drawable.octoggle_thumb);
        setTrackResource(R.drawable.octoggle_track);
    }

    public /* synthetic */ void lambda$new$18$OCToggle(View view) {
        OCEventHandler oCEventHandler = this.mEventHandler;
        if (oCEventHandler != null) {
            oCEventHandler.onButtonClick();
        }
        View.OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public /* synthetic */ boolean lambda$new$19$OCToggle(View view, MotionEvent motionEvent) {
        if (this.mEventHandler != null && motionEvent.getAction() == 9) {
            this.mEventHandler.onButtonEnter();
        }
        View.OnHoverListener onHoverListener = this.mOnHoverListener;
        if (onHoverListener != null) {
            return onHoverListener.onHover(view, motionEvent);
        }
        return false;
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnHoverListener(View.OnHoverListener onHoverListener) {
        this.mOnHoverListener = onHoverListener;
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
    }
}
