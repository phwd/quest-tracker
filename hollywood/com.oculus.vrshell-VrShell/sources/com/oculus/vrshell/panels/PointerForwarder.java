package com.oculus.vrshell.panels;

import android.os.SystemClock;
import android.view.View;
import com.oculus.vrshell.panels.PointerEvent;

/* access modifiers changed from: package-private */
public final class PointerForwarder {
    private long mLastHoverEnterTimeMillis = 0;
    private long mLastTouchDownTimeMillis = 0;
    private final View mViewTarget;
    private final AndroidPanelWindowManager mWindowManagerTarget;

    private static boolean isHoverEvent(int i) {
        return i == 9 || i == 7 || i == 10;
    }

    private static boolean isScrollEvent(int i) {
        return i == 8;
    }

    private static boolean isTouchEvent(int i) {
        return i == 0 || i == 2 || i == 1 || i == 3;
    }

    public PointerForwarder(View view, AndroidPanelWindowManager androidPanelWindowManager) {
        this.mViewTarget = view;
        this.mWindowManagerTarget = androidPanelWindowManager;
    }

    public void pointerPressed(float f, float f2) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 0, f, f2);
    }

    public void pointerDragged(float f, float f2) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 2, f, f2);
    }

    public void pointerReleased(float f, float f2) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 1, f, f2);
    }

    public void touchScroll(float f, float f2, float f3, float f4) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 8, f, f2, Float.valueOf(f3), Float.valueOf(f4));
    }

    public void hoverEnter(float f, float f2) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 9, f, f2);
    }

    public void hoverExit(float f, float f2) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 10, f, f2);
    }

    public void hoverMove(float f, float f2) {
        sendPointerEvent(PointerEvent.PointerConfig.FINGER, 7, f, f2);
    }

    private void sendPointerEvent(PointerEvent.PointerConfig pointerConfig, int i, float f, float f2, Float f3, Float f4) {
        long j;
        boolean z = true;
        boolean z2 = i == 0;
        if (i != 8) {
            z = false;
        }
        if (z || z2) {
            this.mLastTouchDownTimeMillis = SystemClock.uptimeMillis();
        } else if (i == 9) {
            this.mLastHoverEnterTimeMillis = SystemClock.uptimeMillis();
        }
        if (isTouchEvent(i) || isScrollEvent(i)) {
            j = this.mLastTouchDownTimeMillis;
        } else if (isHoverEvent(i)) {
            j = this.mLastHoverEnterTimeMillis;
        } else {
            throw new IllegalArgumentException("Invalid action type: " + i);
        }
        PointerEvent obtain = PointerEvent.obtain(pointerConfig, j, i, f, f2, f3, f4);
        if (isHoverEvent(i) || isScrollEvent(i)) {
            if (!this.mWindowManagerTarget.dispatchGenericMotionEvent(obtain.getMotionEvent())) {
                this.mViewTarget.dispatchGenericMotionEvent(obtain.getMotionEvent());
            }
        } else if (!this.mWindowManagerTarget.dispatchTouchEvent(obtain.getMotionEvent())) {
            this.mViewTarget.dispatchTouchEvent(obtain.getMotionEvent());
        }
        obtain.recycle();
    }

    private void sendPointerEvent(PointerEvent.PointerConfig pointerConfig, int i, float f, float f2) {
        sendPointerEvent(pointerConfig, i, f, f2, null, null);
    }
}
