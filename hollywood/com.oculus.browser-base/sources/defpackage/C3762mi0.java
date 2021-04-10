package defpackage;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ThreadUtils;

/* renamed from: mi0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3762mi0 {

    /* renamed from: a  reason: collision with root package name */
    public static final C3762mi0 f10441a = new C3762mi0(60000);
    public int b = 0;
    public Integer c;
    public boolean d;
    public boolean e;
    public Q31 f = new C3078ii0();
    public AbstractC2737gi0 g = new C3249ji0();
    public final Runnable h = new RunnableC3420ki0(this);

    public C3762mi0(int i) {
    }

    public static int a(long j) {
        return (int) Math.min(TimeUnit.NANOSECONDS.toMicros(SystemClock.elapsedRealtimeNanos() - j), 2147483647L);
    }

    public static Integer b(int i) {
        if (i >= 80 || i == 15) {
            return 2;
        }
        return i >= 40 ? 1 : null;
    }

    public void c(int i) {
        Object obj = ThreadUtils.f10596a;
        if (this.d) {
            this.c = Integer.valueOf(i);
        } else {
            d(i);
        }
    }

    public final void d(int i) {
        ThreadUtils.e(this.h, (long) 60000);
        this.d = true;
        this.b = i;
        this.g.a(i);
    }
}
