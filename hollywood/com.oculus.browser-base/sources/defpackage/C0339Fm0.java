package defpackage;

import J.N;
import android.os.SystemClock;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: Fm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0339Fm0 implements AbstractC2930hp1 {

    /* renamed from: a  reason: collision with root package name */
    public final Map f8039a = Collections.synchronizedMap(new HashMap());

    @Override // defpackage.AbstractC2930hp1
    public void a(String str, boolean z) {
        long f = f(str);
        g(str, f, N.MtKTTHie(str, f, z));
    }

    @Override // defpackage.AbstractC2930hp1
    public void b(String str, long j) {
        N.MTDsfZGe(str, SystemClock.elapsedRealtime() - j);
    }

    @Override // defpackage.AbstractC2930hp1
    public void c(String str, int i, int i2, int i3, int i4) {
        long f = f(str);
        g(str, f, N.MILRV9Ch(str, f, i, i2, i3, i4));
    }

    @Override // defpackage.AbstractC2930hp1
    public void d(String str, int i) {
        long f = f(str);
        g(str, f, N.Mk1ai9mx(str, f, i));
    }

    @Override // defpackage.AbstractC2930hp1
    public void e(String str, int i, int i2, int i3, int i4) {
        long f = f(str);
        g(str, f, N.M$oMD214(str, f, i, i2, i3, i4));
    }

    public final long f(String str) {
        Long l = (Long) this.f8039a.get(str);
        if (l == null) {
            return 0;
        }
        return l.longValue();
    }

    public final void g(String str, long j, long j2) {
        if (j != j2) {
            this.f8039a.put(str, Long.valueOf(j2));
        }
    }
}
