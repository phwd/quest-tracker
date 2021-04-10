package com.oculus.panelapp.anytimeui.v2.tablet.notifications;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.constraintlayout.widget.ConstraintLayout;

public class NotificationsConstraintLayout extends ConstraintLayout {
    private IHoverStateChangedListener mHoverListener;
    private HoverState mLastState = HoverState.NONE;

    public enum HoverState {
        NONE,
        HOVERING,
        EXIT_HOVER,
        ENTER_HOVER
    }

    public interface IHoverStateChangedListener {
        boolean onHoverChangedListener(HoverState hoverState);
    }

    public NotificationsConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        return dispatchToListener(motionEvent);
    }

    public void addHoverStateListener(IHoverStateChangedListener iHoverStateChangedListener) {
        this.mHoverListener = iHoverStateChangedListener;
    }

    private boolean dispatchToListener(MotionEvent motionEvent) {
        if (this.mHoverListener == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action != 9) {
                if (action != 10 || !this.mLastState.equals(HoverState.HOVERING)) {
                    return false;
                }
                this.mLastState = HoverState.EXIT_HOVER;
                return this.mHoverListener.onHoverChangedListener(HoverState.EXIT_HOVER);
            } else if (!this.mLastState.equals(HoverState.NONE) && !this.mLastState.equals(HoverState.EXIT_HOVER)) {
                return false;
            } else {
                this.mLastState = HoverState.ENTER_HOVER;
                return this.mHoverListener.onHoverChangedListener(HoverState.ENTER_HOVER);
            }
        } else if (!this.mLastState.equals(HoverState.ENTER_HOVER) && !this.mLastState.equals(HoverState.HOVERING)) {
            return false;
        } else {
            this.mLastState = HoverState.HOVERING;
            return this.mHoverListener.onHoverChangedListener(HoverState.HOVERING);
        }
    }
}
