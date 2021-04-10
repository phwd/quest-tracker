package com.oculus.vrshell.panels;

import android.os.SystemClock;
import android.view.MotionEvent;
import java.util.Stack;

public final class PointerEvent {
    public static final int POOL_SIZE = 25;
    public static final Stack<PointerEvent> pool = new Stack<>();
    public MotionEvent mMotionEvent;
    public final MotionEvent.PointerCoords[] mPointerCoords = {new MotionEvent.PointerCoords()};
    public final MotionEvent.PointerProperties[] mPointerProperties = {new MotionEvent.PointerProperties()};
    public boolean mRecycled;

    public static class PointerConfig {
        public static final PointerConfig FINGER = new PointerConfig(1, 0, 2);
        public static final PointerConfig MOUSE_SECONDARY_BUTTON = new PointerConfig(3, 2, 8194);
        public final int mButtonState;
        public final int mSource;
        public final int mToolType;

        public int getButtonState() {
            return this.mButtonState;
        }

        public int getSource() {
            return this.mSource;
        }

        public int getToolType() {
            return this.mToolType;
        }

        public PointerConfig(int i, int i2, int i3) {
            this.mToolType = i;
            this.mButtonState = i2;
            this.mSource = i3;
        }
    }

    public MotionEvent getMotionEvent() {
        return this.mMotionEvent;
    }

    public void recycle() {
        this.mMotionEvent.recycle();
        this.mMotionEvent = null;
        this.mPointerCoords[0].clear();
        this.mPointerProperties[0].clear();
        this.mRecycled = true;
        Stack<PointerEvent> stack = pool;
        synchronized (stack) {
            if (stack.size() < 25) {
                stack.push(this);
            }
        }
    }

    public static PointerEvent obtain(PointerConfig pointerConfig, long j, int i, float f, float f2) {
        return obtain(pointerConfig, j, i, f, f2, null, null);
    }

    public static PointerEvent obtain(PointerConfig pointerConfig, long j, int i, float f, float f2, Float f3, Float f4) {
        PointerEvent pop;
        Stack<PointerEvent> stack = pool;
        synchronized (stack) {
            if (stack.empty()) {
                pop = new PointerEvent();
            } else {
                pop = stack.pop();
                pop.mRecycled = false;
            }
        }
        MotionEvent.PointerProperties pointerProperties = pop.mPointerProperties[0];
        pointerProperties.id = 0;
        pointerProperties.toolType = pointerConfig.mToolType;
        MotionEvent.PointerCoords pointerCoords = pop.mPointerCoords[0];
        pointerCoords.x = f;
        pointerCoords.y = f2;
        if (f3 != null) {
            pointerCoords.setAxisValue(10, f3.floatValue());
        }
        if (f4 != null) {
            pointerCoords.setAxisValue(9, f4.floatValue());
        }
        pop.mMotionEvent = MotionEvent.obtain(j, SystemClock.uptimeMillis(), i, 1, pop.mPointerProperties, pop.mPointerCoords, 0, pointerConfig.mButtonState, 1.0f, 1.0f, 0, 0, pointerConfig.mSource, 0);
        return pop;
    }
}
