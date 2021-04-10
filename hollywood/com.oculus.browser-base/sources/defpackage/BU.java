package defpackage;

import android.hardware.input.InputManager;
import android.view.InputDevice;
import java.util.Objects;
import org.chromium.device.gamepad.GamepadList;

/* renamed from: BU  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BU implements InputManager.InputDeviceListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GamepadList f7739a;

    public BU(GamepadList gamepadList) {
        this.f7739a = gamepadList;
    }

    public void onInputDeviceAdded(int i) {
        GamepadList gamepadList = this.f7739a;
        Objects.requireNonNull(gamepadList);
        InputDevice device = InputDevice.getDevice(i);
        boolean z = false;
        if (device != null && (device.getSources() & 16777232) == 16777232) {
            z = true;
        }
        if (z) {
            synchronized (gamepadList.f10954a) {
                gamepadList.c(device);
            }
        }
    }

    public void onInputDeviceChanged(int i) {
        this.f7739a.b();
    }

    public void onInputDeviceRemoved(int i) {
        GamepadList gamepadList = this.f7739a;
        synchronized (gamepadList.f10954a) {
            AU a2 = gamepadList.a(i);
            if (a2 != null) {
                gamepadList.b[a2.c] = null;
            }
        }
    }
}
