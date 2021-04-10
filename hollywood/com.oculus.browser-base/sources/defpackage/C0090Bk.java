package defpackage;

import android.os.Handler;
import android.os.SystemClock;

/* renamed from: Bk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0090Bk extends AbstractC2742gk {

    /* renamed from: J  reason: collision with root package name */
    public final C2399ej1 f7752J = new C2399ej1(new RunnableC5811yk(this));
    public final Handler K = new Handler();
    public final Q31 L;
    public long M;

    public C0090Bk(AbstractC0956Pq0 pq0) {
        super(3);
        this.L = pq0;
        ((C1078Rq0) pq0).l(new C5981zk(this));
        t();
    }

    public final void o() {
        if (!AbstractC1575Zv.e().g("disable-minimum-show-duration") && !this.K.hasMessages(0)) {
            long uptimeMillis = SystemClock.uptimeMillis() - this.M;
            if (uptimeMillis < 3000) {
                this.K.postDelayed(new RunnableC0029Ak(this, this.f7752J.a()), 3000 - uptimeMillis);
            }
        }
    }

    public void p(int i) {
        C2399ej1 ej1 = this.f7752J;
        boolean z = true;
        if (ej1.b.size() != 1 || !ej1.b.contains(Integer.valueOf(i))) {
            z = false;
        }
        if (z) {
            o();
        }
        this.f7752J.c(i);
    }

    public int q() {
        if (!this.f7752J.b()) {
            this.M = SystemClock.uptimeMillis();
        }
        return this.f7752J.a();
    }

    public int r(int i) {
        int q = q();
        this.f7752J.c(i);
        return q;
    }

    public void s() {
        if (!this.f7752J.b()) {
            this.M = SystemClock.uptimeMillis();
        }
        o();
    }

    public final void t() {
        int i;
        if (((Boolean) this.L.get()).booleanValue()) {
            i = 2;
        } else {
            i = this.f7752J.b() ? 1 : 3;
        }
        m(Integer.valueOf(i));
    }
}
