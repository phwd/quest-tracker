package defpackage;

import android.os.StrictMode;
import java.io.Closeable;

/* renamed from: P21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class P21 implements Closeable {
    public final StrictMode.ThreadPolicy F;
    public final StrictMode.VmPolicy G;

    public P21(StrictMode.ThreadPolicy threadPolicy) {
        this.F = threadPolicy;
        this.G = null;
    }

    public static P21 Y() {
        StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
        StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX);
        return new P21(vmPolicy);
    }

    public static P21 f0() {
        return new P21(StrictMode.allowThreadDiskReads());
    }

    public static P21 g0() {
        return new P21(StrictMode.allowThreadDiskWrites());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        StrictMode.ThreadPolicy threadPolicy = this.F;
        if (threadPolicy != null) {
            StrictMode.setThreadPolicy(threadPolicy);
        }
        StrictMode.VmPolicy vmPolicy = this.G;
        if (vmPolicy != null) {
            StrictMode.setVmPolicy(vmPolicy);
        }
    }

    public P21(StrictMode.VmPolicy vmPolicy) {
        this.F = null;
        this.G = vmPolicy;
    }
}
