package defpackage;

import android.os.SystemClock;
import java.io.Closeable;

/* renamed from: Ei1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0270Ei1 implements Closeable {
    public static C0270Ei1 F;
    public static long G;
    public final long H = SystemClock.uptimeMillis();

    public C0270Ei1() {
        if (F == null) {
            F = this;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (F == this) {
            F = null;
            G = (SystemClock.uptimeMillis() - this.H) + G;
        }
    }
}
