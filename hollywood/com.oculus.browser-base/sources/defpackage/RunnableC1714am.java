package defpackage;

import android.app.admin.DevicePolicyManager;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;

/* renamed from: am  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1714am implements Runnable {
    public final SurfaceHolder$CallbackC2065cm F;

    public RunnableC1714am(SurfaceHolder$CallbackC2065cm cmVar) {
        this.F = cmVar;
    }

    public void run() {
        SurfaceHolder$CallbackC2065cm cmVar = this.F;
        int i = cmVar.I;
        Camera camera = null;
        try {
            camera = Camera.open(i);
        } catch (RuntimeException unused) {
            int i2 = 1002;
            if (i == -1) {
                i2 = 1000;
            } else if (((DevicePolicyManager) cmVar.F.getSystemService("device_policy")).getCameraDisabled(null)) {
                i2 = 1001;
            }
            cmVar.H.onError(i2, null);
        }
        new Handler(Looper.getMainLooper()).post(new RunnableC1894bm(cmVar, camera));
    }
}
