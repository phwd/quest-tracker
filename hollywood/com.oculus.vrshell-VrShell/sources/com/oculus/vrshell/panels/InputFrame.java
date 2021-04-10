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

    public void set(float f, float f2, long j, long j2, float f3, float f4, float f5) {
        this.mCursorX = f;
        this.mCursorY = f2;
        this.mButtons = j;
        this.mFlags = j2;
        this.mTouchX = f3;
        this.mTouchY = f4;
        this.mBattery = f5;
        this.mTimeMillis = SystemClock.uptimeMillis();
    }

    public void set(InputFrame inputFrame) {
        set(inputFrame.getCursorX(), inputFrame.getCursorY(), inputFrame.getButtons(), inputFrame.getFlags(), inputFrame.getTouchX(), inputFrame.getTouchY(), inputFrame.getBattery());
    }

    public boolean isButtonRelease(InputFrame inputFrame, long j) {
        return (this.mButtons & j) == 0 && (inputFrame.getButtons() & j) != 0;
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
