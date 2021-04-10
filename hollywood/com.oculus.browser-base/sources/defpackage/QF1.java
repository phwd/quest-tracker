package defpackage;

import android.os.Handler;
import android.os.Looper;
import java.util.Locale;

/* renamed from: QF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class QF1 {

    /* renamed from: a  reason: collision with root package name */
    public static final NF1 f8749a = new NF1("RequestTracker");
    public static final Object b = new Object();
    public long c;
    public final Handler d = new HandlerC2841hG1(Looper.getMainLooper());
    public long e = -1;
    public RF1 f;
    public Runnable g;

    public QF1(long j) {
        this.c = j;
    }

    public final boolean a(long j) {
        boolean z;
        synchronized (b) {
            long j2 = this.e;
            z = j2 != -1 && j2 == j;
        }
        return z;
    }

    public final void b(int i, Object obj, String str) {
        NF1 nf1 = f8749a;
        Object[] objArr = new Object[0];
        if (nf1.d()) {
            nf1.c(str, objArr);
        }
        Object obj2 = b;
        synchronized (obj2) {
            RF1 rf1 = this.f;
            if (rf1 != null) {
                rf1.b(this.e, i, obj);
            }
            this.e = -1;
            this.f = null;
            synchronized (obj2) {
                Runnable runnable = this.g;
                if (runnable != null) {
                    this.d.removeCallbacks(runnable);
                    this.g = null;
                }
            }
        }
    }

    public final void c(long j, RF1 rf1) {
        RF1 rf12;
        long j2;
        Object obj = b;
        synchronized (obj) {
            rf12 = this.f;
            j2 = this.e;
            this.e = j;
            this.f = rf1;
        }
        if (rf12 != null) {
            rf12.a(j2);
        }
        synchronized (obj) {
            Runnable runnable = this.g;
            if (runnable != null) {
                this.d.removeCallbacks(runnable);
            }
            TF1 tf1 = new TF1(this);
            this.g = tf1;
            this.d.postDelayed(tf1, this.c);
        }
    }

    public final boolean d(int i) {
        synchronized (b) {
            long j = this.e;
            if (j == -1) {
                return false;
            }
            b(i, null, String.format(Locale.ROOT, "clearing request %d", Long.valueOf(j)));
            return true;
        }
    }

    public final boolean e(long j, int i, Object obj) {
        synchronized (b) {
            long j2 = this.e;
            if (j2 == -1 || j2 != j) {
                return false;
            }
            b(i, obj, String.format(Locale.ROOT, "request %d completed", Long.valueOf(j)));
            return true;
        }
    }

    public final boolean f() {
        boolean z;
        synchronized (b) {
            z = this.e != -1;
        }
        return z;
    }
}
