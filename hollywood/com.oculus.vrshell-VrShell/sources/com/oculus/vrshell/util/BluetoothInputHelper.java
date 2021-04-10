package com.oculus.vrshell.util;

import android.view.InputDevice;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.recyclerview.widget.ItemTouchHelper;

public class BluetoothInputHelper {
    private static final int[] AXIS_AXIS = {15, 16, 0, 1, 12, 13, 11, 14};
    private static final int[] AXIS_NEGATIVE_BUTTON = {21, 19, 202, ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, 206, 204, 206, 204};
    private static final int[] AXIS_POSITIVE_BUTTON = {22, 20, 203, 201, 207, 205, 207, 205};
    private final int[] axisState = new int[8];
    private final NativeInputSink nativeInputSink;

    public interface NativeInputSink {
        void forwardJoypadAxisEvent(float f, float f2, float f3, float f4);

        void forwardKeyEvent(int i, int i2, int i3, boolean z, boolean z2);

        void forwardTouchEvent(int i, float f, float f2, int i2, float f3, float f4, String str, int i3);
    }

    public BluetoothInputHelper(NativeInputSink nativeInputSink2) {
        this.nativeInputSink = nativeInputSink2;
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 16) == 0 || motionEvent.getAction() != 2) {
            if (motionEvent.getToolType(0) == 3) {
                this.nativeInputSink.forwardTouchEvent(motionEvent.getAction(), motionEvent.getAxisValue(27, 0), motionEvent.getAxisValue(28, 0), motionEvent.getButtonState(), motionEvent.getAxisValue(10), motionEvent.getAxisValue(9), getSanitizedDeviceName(motionEvent), motionEvent.getToolType(0));
            }
            return false;
        }
        float axisValue = motionEvent.getAxisValue(0);
        float axisValue2 = motionEvent.getAxisValue(1);
        float axisValue3 = motionEvent.getAxisValue(12);
        float axisValue4 = motionEvent.getAxisValue(13);
        if (axisValue3 == 0.0f && axisValue4 == 0.0f) {
            axisValue3 = motionEvent.getAxisValue(11);
            axisValue4 = motionEvent.getAxisValue(14);
        }
        this.nativeInputSink.forwardJoypadAxisEvent(axisValue, axisValue2, axisValue3, axisValue4);
        for (int i = 0; i < 8; i++) {
            this.axisState[i] = sendButtonsForNewAxisState(motionEvent.getAxisValue(AXIS_AXIS[i]), AXIS_NEGATIVE_BUTTON[i], AXIS_POSITIVE_BUTTON[i], this.axisState[i]);
        }
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        boolean z = action == 0;
        if (action != 0 && action != 1) {
            return false;
        }
        this.nativeInputSink.forwardKeyEvent(keyCode, keyEvent.getRepeatCount(), KeyEvent.normalizeMetaState(keyEvent.getMetaState()), z, keyEvent.getSource() == 1281 || keyEvent.getSource() == 16777489);
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.nativeInputSink.forwardTouchEvent(motionEvent.getAction(), motionEvent.getAxisValue(27, 0), motionEvent.getAxisValue(28, 0), motionEvent.getButtonState(), motionEvent.getAxisValue(10), motionEvent.getAxisValue(9), getSanitizedDeviceName(motionEvent), motionEvent.getToolType(0));
        return true;
    }

    private int sendButtonsForNewAxisState(float f, int i, int i2, int i3) {
        int i4 = f < -0.5f ? -1 : f > 0.5f ? 1 : 0;
        if (i4 != i3) {
            if (i3 == -1) {
                this.nativeInputSink.forwardKeyEvent(i, 0, 0, false, true);
            } else if (i3 == 1) {
                this.nativeInputSink.forwardKeyEvent(i2, 0, 0, false, true);
            }
            if (i4 == -1) {
                this.nativeInputSink.forwardKeyEvent(i, 0, 0, true, true);
            } else if (i4 == 1) {
                this.nativeInputSink.forwardKeyEvent(i2, 0, 0, true, true);
            }
        }
        return i4;
    }

    private static String getSanitizedDeviceName(InputEvent inputEvent) {
        String name;
        InputDevice device = inputEvent.getDevice();
        return (device == null || (name = device.getName()) == null) ? "<unknown>" : name;
    }
}
