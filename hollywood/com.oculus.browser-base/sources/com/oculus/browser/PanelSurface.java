package com.oculus.browser;

import android.view.Surface;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PanelSurface {

    /* renamed from: a  reason: collision with root package name */
    public long f9706a;
    public Surface b;

    public PanelSurface(Surface surface, WindowAndroid windowAndroid, String str) {
        this.b = surface;
        this.f9706a = nativeInit(windowAndroid, str);
        AbstractC1220Ua0.d("PanelSurface", "ctor", new Object[0]);
    }

    public void a() {
        AbstractC1220Ua0.d("PanelSurface", "onForeground", new Object[0]);
        nativeSetSurface(this.f9706a, this.b);
    }

    public final long getNativePointer() {
        return this.f9706a;
    }

    public final native void nativeDestroy(long j);

    public final native long nativeInit(WindowAndroid windowAndroid, String str);

    public final native void nativeSetSurface(long j, Surface surface);
}
