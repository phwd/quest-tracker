package defpackage;

import android.os.Debug;
import java.util.concurrent.TimeUnit;

/* renamed from: H31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H31 implements AutoCloseable {
    public final String F;
    public final long G = Debug.threadCpuTimeNanos();

    public H31(String str) {
        this.F = str;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        long threadCpuTimeNanos = Debug.threadCpuTimeNanos();
        long j = this.G;
        long j2 = -1;
        if (!(j == -1 || threadCpuTimeNanos == -1)) {
            j2 = TimeUnit.NANOSECONDS.toMicros(threadCpuTimeNanos - j);
        }
        if (j2 >= 0) {
            AbstractC3364kK0.f(this.F, j2, 100, I31.f8197a, 100);
        }
    }
}
