package defpackage;

import android.graphics.Rect;
import android.hardware.camera2.CameraDevice;

/* renamed from: tt1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4990tt1 implements Runnable {
    public final /* synthetic */ C5330vt1 F;

    public RunnableC4990tt1(C5330vt1 vt1, AbstractC3283jt1 jt1) {
        this.F = vt1;
    }

    public void run() {
        CameraDevice cameraDevice = this.F.i;
        if (cameraDevice != null) {
            cameraDevice.close();
            this.F.f(3);
            this.F.s = new Rect();
        }
    }
}
