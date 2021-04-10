package com.oculus.vrshell.panels;

import android.os.SystemClock;

public final class InputFrame {
    public float mBattery;
    public long mButtons;
    public float mCursorX;
    public float mCursorY;
    public long mFlags;
    public long mTimeMillis;
    public float mTouchX;
    public float mTouchY;

    public float getBattery() {
        return this.mBattery;
    }

    public long getButtons() {
        return this.mButtons;
    }

    public float getCursorX() {
        return this.mCursorX;
    }

    public float getCursorY() {
        return this.mCursorY;
    }

    public long getFlags() {
        return this.mFlags;
    }

    public long getTimeMillis() {
        return this.mTimeMillis;
    }

    public float getTouchX() {
        return this.mTouchX;
    }

    public float getTouchY() {
        return this.mTouchY;
    }

    public boolean isButtonRelease(InputFrame inputFrame, long j) {
        if ((this.mButtons & j) != 0 || (j & inputFrame.mButtons) == 0) {
            return false;
        }
        return true;
    }

    public boolean isCursorInside() {
        long j = this.mFlags;
        if ((j & PanelFlags.GAZE_VALID.getFlagValue()) == 0 || (j & PanelFlags.GAZE_INSIDE.getFlagValue()) == 0) {
            return false;
        }
        return true;
    }

    public boolean isRemoteConnected() {
        if ((this.mFlags & PanelFlags.REMOTE_CONNECTED.getFlagValue()) != 0) {
            return true;
        }
        return false;
    }

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
        set(inputFrame.mCursorX, inputFrame.mCursorY, inputFrame.mButtons, inputFrame.mFlags, inputFrame.mTouchX, inputFrame.mTouchY, inputFrame.mBattery);
    }
}
