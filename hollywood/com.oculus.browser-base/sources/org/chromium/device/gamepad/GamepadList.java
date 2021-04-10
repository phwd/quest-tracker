package org.chromium.device.gamepad;

import J.N;
import android.hardware.input.InputManager;
import android.view.InputDevice;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GamepadList {

    /* renamed from: a  reason: collision with root package name */
    public final Object f10954a = new Object();
    public final AU[] b = new AU[4];
    public InputManager c;
    public int d;
    public boolean e;
    public InputManager.InputDeviceListener f = new BU(this);

    public GamepadList(BU bu) {
    }

    public static void setGamepadAPIActive(boolean z) {
        GamepadList gamepadList = CU.f7812a;
        synchronized (gamepadList.f10954a) {
            gamepadList.e = z;
            if (z) {
                for (int i = 0; i < 4; i++) {
                    AU au = gamepadList.b[i];
                    if (au != null) {
                        Arrays.fill(au.e, 0.0f);
                        Arrays.fill(au.h, 0.0f);
                        Arrays.fill(au.f, 0.0f);
                        Arrays.fill(au.g, 0.0f);
                    }
                }
            }
        }
    }

    public static void updateGamepadData(long j) {
        GamepadList gamepadList = CU.f7812a;
        synchronized (gamepadList.f10954a) {
            for (int i = 0; i < 4; i++) {
                AU au = gamepadList.b[i];
                if (au != null) {
                    au.k.k(au.e, au.f, au.h, au.g);
                    NU nu = au.k;
                    Objects.requireNonNull(nu);
                    N.MOkngxPY(gamepadList, j, i, !(nu instanceof KU), true, au.i, au.d, au.e, au.f, au.k.j());
                } else {
                    N.MOkngxPY(gamepadList, j, i, false, false, null, 0, null, null, 0);
                }
            }
        }
    }

    public final AU a(int i) {
        for (int i2 = 0; i2 < 4; i2++) {
            AU au = this.b[i2];
            if (au != null && au.b == i) {
                return au;
            }
        }
        return null;
    }

    public final void b() {
    }

    public final boolean c(InputDevice inputDevice) {
        int i = 0;
        while (true) {
            if (i >= 4) {
                i = -1;
                break;
            } else if (this.b[i] == null) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            return false;
        }
        this.b[i] = new AU(i, inputDevice);
        return true;
    }
}
