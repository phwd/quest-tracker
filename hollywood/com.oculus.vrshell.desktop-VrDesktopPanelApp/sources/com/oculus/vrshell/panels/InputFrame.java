package com.oculus.vrshell.panels;

import android.os.SystemClock;

public final class InputFrame {
    private float mBattery;
    private long mButtons;
    private float mCursorX;
    private float mCursorY;
    private long mFlags;
    private long mTimeMillis;
    private float mTouchX;
    private float mTouchY;

    public void set(float cursorX, float cursorY, long buttons, long flags, float touchX, float touchY, float battery) {
        this.mCursorX = cursorX;
        this.mCursorY = cursorY;
        this.mButtons = buttons;
        this.mFlags = flags;
        this.mTouchX = touchX;
        this.mTouchY = touchY;
        this.mBattery = battery;
        this.mTimeMillis = SystemClock.uptimeMillis();
    }

    public void set(InputFrame other) {
        set(other.getCursorX(), other.getCursorY(), other.getButtons(), other.getFlags(), other.getTouchX(), other.getTouchY(), other.getBattery());
    }

    public boolean isButtonRelease(InputFrame priorInputFrame, long buttonFlags) {
        return (this.mButtons & buttonFlags) == 0 && (priorInputFrame.getButtons() & buttonFlags) != 0;
    }

    public boolean isRemoteConnected() {
        return (this.mFlags & PanelFlags.REMOTE_CONNECTED.getFlagValue()) != 0;
    }

    public boolean isCursorInside() {
        return ((this.mFlags & PanelFlags.GAZE_VALID.getFlagValue()) == 0 || (this.mFlags & PanelFlags.GAZE_INSIDE.getFlagValue()) == 0) ? false : true;
    }

    public float getCursorX() {
        return this.mCursorX;
    }

    public float getCursorY() {
        return this.mCursorY;
    }

    public long getButtons() {
        return this.mButtons;
    }

    public long getFlags() {
        return this.mFlags;
    }

    public float getTouchX() {
        return this.mTouchX;
    }

    public float getTouchY() {
        return this.mTouchY;
    }

    public float getBattery() {
        return this.mBattery;
    }

    public long getTimeMillis() {
        return this.mTimeMillis;
    }
}
