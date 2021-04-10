package com.oculus.vrshell.panels;

import android.os.SystemClock;
import android.view.MotionEvent;
import androidx.annotation.Nullable;
import androidx.core.util.Pools;

/* access modifiers changed from: package-private */
public final class PointerEvent {
    private static final Pools.Pool<PointerEvent> POOL = new Pools.SynchronizedPool(25);
    private static final int POOL_SIZE = 25;
    private MotionEvent mMotionEvent;
    private final MotionEvent.PointerCoords[] mPointerCoords = {new MotionEvent.PointerCoords()};
    private final MotionEvent.PointerProperties[] mPointerProperties = {new MotionEvent.PointerProperties()};
    private boolean mRecycled;

    private PointerEvent() {
    }

    public static PointerEvent obtain(PointerConfig pointerConfig, long sequenceStartTime, int action, float x, float y, @Nullable Float hscroll, @Nullable Float vscroll) {
        PointerEvent event = (PointerEvent) POOL.acquire();
        if (event == null) {
            event = new PointerEvent();
        } else {
            event.mRecycled = false;
        }
        MotionEvent.PointerProperties pointerProps = event.mPointerProperties[0];
        pointerProps.id = 0;
        pointerProps.toolType = pointerConfig.getToolType();
        MotionEvent.PointerCoords pointerCoords = event.mPointerCoords[0];
        pointerCoords.x = x;
        pointerCoords.y = y;
        if (hscroll != null) {
            pointerCoords.setAxisValue(10, hscroll.floatValue());
        }
        if (vscroll != null) {
            pointerCoords.setAxisValue(9, vscroll.floatValue());
        }
        event.mMotionEvent = MotionEvent.obtain(sequenceStartTime, SystemClock.uptimeMillis(), action, 1, event.mPointerProperties, event.mPointerCoords, 0, pointerConfig.getButtonState(), 1.0f, 1.0f, 0, 0, pointerConfig.getSource(), 0);
        return event;
    }

    public static PointerEvent obtain(PointerConfig pointerConfig, long sequenceStartTime, int action, float x, float y) {
        return obtain(pointerConfig, sequenceStartTime, action, x, y, null, null);
    }

    public void recycle() {
        this.mMotionEvent.recycle();
        this.mMotionEvent = null;
        this.mPointerCoords[0].clear();
        this.mPointerProperties[0].clear();
        this.mRecycled = true;
        POOL.release(this);
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

        private PointerConfig(int toolType, int buttonState, int source) {
            this.mToolType = toolType;
            this.mButtonState = buttonState;
            this.mSource = source;
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
