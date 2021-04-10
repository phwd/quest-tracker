package com.oculus.vrshell.panels;

import android.os.SystemClock;
import android.view.View;
import androidx.annotation.Nullable;
import com.oculus.vrshell.panels.PointerEvent;

/* access modifiers changed from: package-private */
public final class PointerForwarder {
    private long mLastHoverEnterTimeMillis = 0;
    private long mLastTouchDownTimeMillis = 0;
    private final View mViewTarget;
    private final AndroidPanelWindowManager mWindowManagerTarget;

    public PointerForwarder(View viewTarget, AndroidPanelWindowManager windowManagerTarget) {
        this.mViewTarget = viewTarget;
        this.mWindowManagerTarget = windowManagerTarget;
    }

    public void pointerPressed(float x, float y) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 0, x, y);
    }

    public void pointerDragged(float x, float y) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 2, x, y);
    }

    public void pointerReleased(float x, float y) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 1, x, y);
    }

    public void touchScroll(float x, float y, float hscroll, float vscroll) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 8, x, y, Float.valueOf(hscroll), Float.valueOf(vscroll));
    }

    public void hoverEnter(float x, float y) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 9, x, y);
    }

    public void hoverExit(float x, float y) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 10, x, y);
    }

    public void hoverMove(float x, float y) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 7, x, y);
    }

    private void sendPointerEvent(PointerEvent.PointerConfig pointerConfig, int action, float x, float y, @Nullable Float hscroll, @Nullable Float vscroll) {
        long lastEventDownTime;
        boolean z = true;
        boolean z2 = action == 0;
        if (action != 8) {
            z = false;
        }
        if (z || z2) {
            this.mLastTouchDownTimeMillis = SystemClock.uptimeMillis();
        } else if (action == 9) {
            this.mLastHoverEnterTimeMillis = SystemClock.uptimeMillis();
        }
        if (isTouchEvent(action) || isScrollEvent(action)) {
            lastEventDownTime = this.mLastTouchDownTimeMillis;
        } else if (isHoverEvent(action)) {
            lastEventDownTime = this.mLastHoverEnterTimeMillis;
        } else {
            throw new IllegalArgumentException("Invalid action type: " + action);
        }
        PointerEvent event = PointerEvent.obtain(pointerConfig, lastEventDownTime, action, x, y, hscroll, vscroll);
        if (isHoverEvent(action) || isScrollEvent(action)) {
            if (!this.mWindowManagerTarget.dispatchGenericMotionEvent(event.getMotionEvent())) {
                this.mViewTarget.dispatchGenericMotionEvent(event.getMotionEvent());
            }
        } else if (!this.mWindowManagerTarget.dispatchTouchEvent(event.getMotionEvent())) {
            this.mViewTarget.dispatchTouchEvent(event.getMotionEvent());
        }
        event.recycle();
    }

    private void sendPointerEvent(PointerEvent.PointerConfig pointerConfig, int action, float x, float y) {
        sendPointerEvent(pointerConfig, action, x, y, null, null);
    }

    private static boolean isTouchEvent(int action) {
        if (action == 0 || action == 2 || action == 1 || action == 3) {
            return true;
        }
        return false;
    }

    private static boolean isHoverEvent(int action) {
        if (action == 9 || action == 7 || action == 10) {
            return true;
        }
        return false;
    }

    private static boolean isScrollEvent(int action) {
        return action == 8;
    }
}
