package defpackage;

import android.os.Process;
import android.os.SystemClock;

/* renamed from: SJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class SJ {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8888a;
    public final boolean b;
    public final String c;
    public final int d = Process.myTid();
    public final long e = SystemClock.elapsedRealtimeNanos();
    public final long f = SystemClock.currentThreadTimeMillis();

    public SJ(String str, boolean z, boolean z2) {
        this.f8888a = z;
        this.b = z2;
        this.c = str;
    }
}
