package org.chromium.ui.events.devices;

import J.N;
import android.hardware.input.InputManager;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class InputDeviceObserver implements InputManager.InputDeviceListener {

    /* renamed from: a  reason: collision with root package name */
    public static final InputDeviceObserver f11024a = new InputDeviceObserver();
    public InputManager b;
    public int c;

    public static void addObserver() {
        InputDeviceObserver inputDeviceObserver = f11024a;
        int i = inputDeviceObserver.c;
        inputDeviceObserver.c = i + 1;
        if (i == 0) {
            InputManager inputManager = (InputManager) ContextUtils.getApplicationContext().getSystemService("input");
            inputDeviceObserver.b = inputManager;
            inputManager.registerInputDeviceListener(inputDeviceObserver, null);
        }
    }

    public static void removeObserver() {
        InputDeviceObserver inputDeviceObserver = f11024a;
        int i = inputDeviceObserver.c - 1;
        inputDeviceObserver.c = i;
        if (i == 0) {
            inputDeviceObserver.b.unregisterInputDeviceListener(inputDeviceObserver);
            inputDeviceObserver.b = null;
        }
    }

    public void onInputDeviceAdded(int i) {
        N.MGCvz8lp(this);
    }

    public void onInputDeviceChanged(int i) {
        N.MGCvz8lp(this);
    }

    public void onInputDeviceRemoved(int i) {
        N.MGCvz8lp(this);
    }
}
