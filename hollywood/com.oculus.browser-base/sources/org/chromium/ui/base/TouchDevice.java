package org.chromium.ui.base;

import android.view.InputDevice;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TouchDevice {
    public static boolean a(int i, int i2) {
        return (i & i2) == i2;
    }

    public static int[] availablePointerAndHoverTypes() {
        int[] iArr = {0, 0};
        for (int i : InputDevice.getDeviceIds()) {
            InputDevice inputDevice = null;
            try {
                inputDevice = InputDevice.getDevice(i);
            } catch (RuntimeException unused) {
            }
            if (inputDevice != null) {
                int sources = inputDevice.getSources();
                if (a(sources, 8194) || a(sources, 16386) || a(sources, 1048584) || a(sources, 65540)) {
                    iArr[0] = iArr[0] | 4;
                } else if (a(sources, 4098)) {
                    iArr[0] = iArr[0] | 2;
                }
                if (a(sources, 8194) || a(sources, 1048584) || a(sources, 65540)) {
                    iArr[1] = iArr[1] | 2;
                }
            }
        }
        if (iArr[0] == 0) {
            iArr[0] = 1;
        }
        if (iArr[1] == 0) {
            iArr[1] = 1;
        }
        return iArr;
    }

    public static int maxTouchPoints() {
        if (ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.jazzhand")) {
            return 5;
        }
        if (!ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.distinct") && !ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch")) {
            return ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen") ? 1 : 0;
        }
        return 2;
    }
}
