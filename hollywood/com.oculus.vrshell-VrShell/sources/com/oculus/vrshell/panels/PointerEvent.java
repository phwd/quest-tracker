package com.oculus.vrshell.panels;

import android.os.SystemClock;
import android.view.MotionEvent;
import java.util.Stack;

/* access modifiers changed from: package-private */
public final class PointerEvent {
    private static final int POOL_SIZE = 25;
    private static final Stack<PointerEvent> pool = new Stack<>();
    private MotionEvent mMotionEvent;
    private final MotionEvent.PointerCoords[] mPointerCoords = {new MotionEvent.PointerCoords()};
    private final MotionEvent.PointerProperties[] mPointerProperties = {new MotionEvent.PointerProperties()};
    private boolean mRecycled;

    private PointerEvent() {
    }

    public static PointerEvent obtain(PointerConfig pointerConfig, long j, int i, float f, float f2, Float f3, Float f4) {
        PointerEvent pointerEvent;
        synchronized (pool) {
            if (pool.empty()) {
                pointerEvent = new PointerEvent();
            } else {
                pointerEvent = pool.pop();
                pointerEvent.mRecycled = false;
            }
        }
        MotionEvent.PointerProperties pointerProperties = pointerEvent.mPointerProperties[0];
        pointerProperties.id = 0;
        pointerProperties.toolType = pointerConfig.getToolType();
        MotionEvent.PointerCoords pointerCoords = pointerEvent.mPointerCoords[0];
        pointerCoords.x = f;
        pointerCoords.y = f2;
        if (f3 != null) {
            pointerCoords.setAxisValue(10, f3.floatValue());
        }
        if (f4 != null) {
            pointerCoords.setAxisValue(9, f4.floatValue());
        }
        pointerEvent.mMotionEvent = MotionEvent.obtain(j, SystemClock.uptimeMillis(), i, 1, pointerEvent.mPointerProperties, pointerEvent.mPointerCoords, 0, pointerConfig.getButtonState(), 1.0f, 1.0f, 0, 0, pointerConfig.getSource(), 0);
        return pointerEvent;
    }

    public static PointerEvent obtain(PointerConfig pointerConfig, long j, int i, float f, float f2) {
        return obtain(pointerConfig, j, i, f, f2, null, null);
    }

    public void recycle() {
        this.mMotionEvent.recycle();
        this.mMotionEvent = null;
        this.mPointerCoords[0].clear();
        this.mPointerProperties[0].clear();
        this.mRecycled = true;
        synchronized (pool) {
            if (pool.size() < 25) {
                pool.push(this);
            }
        }
    }

    public MotionEvent getMotionEvent() {
        return this.mMotionEvent;
    }

    /* access modifiers changed from: package-private */
    public static class PointerConfig {
        static final PointerConfig FINGER = new PointerConfig(1, 0, 2);
        static final PointerConfig MOUSE_SECONDARY_BUTTON = new PointerConfig(3, 2, 8194);
        private final int mButtonState;
        private final int mSource;
        private final int mToolType;

        private PointerConfig(int i, int i2, int i3) {
            this.mToolType = i;
            this.mButtonState = i2;
            this.mSource = i3;
        }

        public int getToolType() {
            return this.mToolType;
        }

        public int getButtonState() {
            return this.mButtonState;
        }

        public int getSource() {
            return this.mSource;
        }
    }
}
