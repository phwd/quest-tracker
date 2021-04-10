package defpackage;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* renamed from: ni0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ComponentCallbacks2C3933ni0 implements ComponentCallbacks2 {
    public static ComponentCallbacks2C3933ni0 F;
    public final String G;

    public ComponentCallbacks2C3933ni0(String str) {
        this.G = AbstractC2531fV.f("Android.MemoryPressureNotification.", str);
    }

    public final void a(int i) {
        AbstractC3364kK0.g(this.G, i, 9);
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
        a(8);
    }

    public void onTrimMemory(int i) {
        if (i == 5) {
            a(7);
        } else if (i == 10) {
            a(6);
        } else if (i == 15) {
            a(5);
        } else if (i == 20) {
            a(4);
        } else if (i == 40) {
            a(3);
        } else if (i == 60) {
            a(2);
        } else if (i != 80) {
            a(0);
        } else {
            a(1);
        }
    }
}
