package defpackage;

import android.hardware.Camera;

/* renamed from: bm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1894bm implements Runnable {
    public final SurfaceHolder$CallbackC2065cm F;
    public final Camera G;

    public RunnableC1894bm(SurfaceHolder$CallbackC2065cm cmVar, Camera camera) {
        this.F = cmVar;
        this.G = camera;
    }

    public void run() {
        SurfaceHolder$CallbackC2065cm cmVar = this.F;
        cmVar.f9629J = this.G;
        cmVar.a();
    }
}
