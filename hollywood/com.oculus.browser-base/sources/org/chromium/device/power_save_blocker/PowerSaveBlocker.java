package org.chromium.device.power_save_blocker;

import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PowerSaveBlocker {

    /* renamed from: a  reason: collision with root package name */
    public static WeakHashMap f10957a = new WeakHashMap();
    public WeakReference b;

    public static PowerSaveBlocker create() {
        return new PowerSaveBlocker();
    }

    public final void applyBlock(View view) {
        this.b = new WeakReference(view);
        Integer num = (Integer) f10957a.get(view);
        if (num == null) {
            f10957a.put(view, 1);
        } else {
            f10957a.put(view, Integer.valueOf(num.intValue() + 1));
        }
        if (num == null || num.intValue() == 0) {
            view.setKeepScreenOn(true);
        }
    }

    public final void removeBlock() {
        WeakReference weakReference = this.b;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            this.b = null;
            if (view != null) {
                Integer num = (Integer) f10957a.get(view);
                f10957a.put(view, Integer.valueOf(num.intValue() - 1));
                if (num.intValue() == 1) {
                    view.setKeepScreenOn(false);
                }
            }
        }
    }
}
